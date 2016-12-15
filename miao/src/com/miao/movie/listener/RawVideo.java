package com.miao.movie.listener;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import org.springframework.beans.BeansException;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.miao.entity.Video;
import com.miao.entity.Videostate;
import com.miao.movie.service.MovieService;

public class RawVideo implements HttpSessionBindingListener {

	private Video video;

	@Override
	public void valueBound(HttpSessionBindingEvent event) {

		try {
			ServletContext sc = event.getSession().getServletContext();
			// 加载本地视频设置信息文件
			InputStream inputStream = this.getClass().getResourceAsStream("/videoConfig.properties");
			Properties videoConfig = new Properties();
			videoConfig.load(inputStream);

			// 获取movieService
			WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(sc);
			MovieService movieService = (MovieService) ctx.getBean("movieService");

			// 截图保存目的文件夹
			String realthumbnailDir = sc.getRealPath("/").replace('\\', '/') + "videothumbnail";
			File realthumbnailDirFile = new File(realthumbnailDir);
			checkAndCreate(realthumbnailDirFile);

			// 得到水印存放文件夹 和 文件名
			String[] watermarkstrlist = videoConfig.getProperty("watermark_url").split("/");
			String watermarkDir = "";
			String watermarkFile = watermarkstrlist[watermarkstrlist.length - 1];
			for (int i = 0; i < watermarkstrlist.length - 1; i++) {
				watermarkDir += watermarkstrlist[i] + "/";
			}
			String realwatermarkDir = sc.getRealPath("/").replace('\\', '/') + watermarkDir;
			File realwatermarkDirFile = new File(realwatermarkDir);
			checkAndCreate(realwatermarkDirFile);

			// 视频最终生成位置
			String realfileDir = sc.getRealPath("/").replace('\\', '/') + videoConfig.getProperty("folder_video");
			File realfileDirFile = new File(realfileDir);
			checkAndCreate(realfileDirFile);

			// 获得缩略图
			this.getThumbnailImg(sc, movieService, realthumbnailDir, video);
			// 视频转码
			this.transferVideo(sc, videoConfig, movieService, watermarkFile, realwatermarkDirFile, video);
			inputStream.close();
		} catch (BeansException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void valueUnbound(HttpSessionBindingEvent arg0) {

	}

	public Video getVideo() {
		return video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}

	public RawVideo(Video video) {
		super();
		this.video = video;
	}

	public void transferVideo(ServletContext sc, Properties videoConfig, MovieService movieService,
			String watermarkFile, File realwatermarkDirFile, Video video) throws IOException {

		// 设置视频uri和url
		String filePath = videoConfig.getProperty("folder_video") + "/" + video.getId() + "."
				+ videoConfig.getProperty("outfmt");
		video.setUrl(filePath);
		String realfilePath = sc.getRealPath("/").replace('\\', '/') + video.getUrl();

		// 转码前视频的地址
		String realfileoriginalPath = sc.getRealPath("/").replace('\\', '/') + video.getOriurl();
		// 拼接ffmpeg指令
		String videotranscodecommand = "cmd /c start ffmpeg -y -i ";
		videotranscodecommand += "\"" + realfileoriginalPath + "\" ";
		// 指定编码格式
		videotranscodecommand += "-vcodec " + videoConfig.getProperty("vcodec") + " ";
		// 指定比特率
		videotranscodecommand += "-b:v " + videoConfig.getProperty("bv") + " ";
		// 指定帧率
		videotranscodecommand += "-r " + videoConfig.getProperty("framerate") + " ";
		// 指定音频编码
		videotranscodecommand += "-acodec " + videoConfig.getProperty("acodec") + " ";
		// 指定比特率
		videotranscodecommand += "-b:a " + videoConfig.getProperty("ba") + " ";
		// 指定采样率
		videotranscodecommand += "-ar " + videoConfig.getProperty("ar") + " ";
		// 设置转换后的分辨率 w为宽度 h为高度
		videotranscodecommand += "-vf scale=w=" + videoConfig.getProperty("scale_w") + ":h="
				+ videoConfig.getProperty("scale_h");
		if (videoConfig.getProperty("keepaspectratio").equals("true")) {
			videotranscodecommand += ":" + "force_original_aspect_ratio=decrease,pad=w="
					+ videoConfig.getProperty("scale_w") + ":h=" + videoConfig.getProperty("scale_h")
					+ ":x=(ow-iw)/2:y=(oh-ih)/2";
		}
		videotranscodecommand += "[aa]";
		// 水印文件
		if (videoConfig.getProperty("watermarkuse").equals("true")) {
			videotranscodecommand += ";movie=";
			videotranscodecommand += watermarkFile;
			videotranscodecommand += "[bb];";
			videotranscodecommand += "[aa][bb]";
			videotranscodecommand += "overlay=x=" + videoConfig.getProperty("watermark_x") + ":y="
					+ videoConfig.getProperty("watermark_y") + " ";
		} else {
			videotranscodecommand += " ";
		}
		videotranscodecommand += "\"";
		// 输出文件夹
		videotranscodecommand += realfilePath;
		videotranscodecommand += "\"";

		System.out.println(videotranscodecommand);
		// 执行转码命令
		Process process = Runtime.getRuntime().exec(videotranscodecommand, null, realwatermarkDirFile);
		video.setVideostate((Videostate) movieService.ReadSingle("Videostate", "order", 4));
		movieService.update(video);
	}

	/**
	 * 检查文件是否存在，不存在就创建
	 * 
	 * @param realwatermarkDirFile
	 *            要检测的文件
	 */
	public void checkAndCreate(File realwatermarkDirFile) {
		if (!realwatermarkDirFile.exists() && !realwatermarkDirFile.isDirectory()) {
			System.out.println("Directory not exist. Create it.");
			System.out.println(realwatermarkDirFile);
			realwatermarkDirFile.mkdir();
		}
	}

	/**
	 * 生成视频缩略图，在list页面展示用
	 * 
	 * @param movieService
	 *            业务逻辑
	 * @param realthumbnailDir
	 *            存放缩略图的目标文件夹
	 * @param video
	 * @throws IOException
	 */
	public void getThumbnailImg(ServletContext sc, MovieService movieService, String realthumbnailDir, Video video)
			throws IOException {

	}

}
