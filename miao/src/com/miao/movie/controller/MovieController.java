package com.miao.movie.controller;

import java.io.File;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.miao.entity.Category;
import com.miao.entity.Configure;
import com.miao.entity.Video;
import com.miao.entity.Videostate;
import com.miao.movie.service.MovieService;

/**
 * 视频点播页面的跳转
 * @author Jupiter
 * 2016/12/11
 */
@Controller
@RequestMapping("/movie")
public class MovieController {

	@Resource
	private MovieService movieService;

	/**
	 * 进入点播list页面
	 * @return
	 */
	@RequestMapping("listUI")
	public ModelAndView listUI() {
		return new ModelAndView("videolist1", "resultvideo", movieService.ReadByProperty("Video", "islive", 0));
	}

	/**
	 * 进入id为*的视频页面
	 * @param id
	 * @return
	 */
	@RequestMapping("viewRoom/{id}")
	public ModelAndView viewRoom(@PathVariable Integer id) {
		return new ModelAndView("videocontent", "video", movieService.ReadByID("Video", id));
	}

	/**
	 * 编辑视频信息
	 * @param id
	 * @return
	 */
	@RequestMapping("editUI/{id}")
	public ModelAndView editUI(@PathVariable Integer id) {

		return id == 0 ? new ModelAndView("videoedit")
				: new ModelAndView("videoedit", "video", movieService.ReadByID("Video", id));
	}

	/**
	 * 更新相应视频信息
	 * @param file
	 * @param video
	 * @return
	 */
	@RequestMapping("update")
	public ModelAndView update(@RequestParam Integer id,@RequestParam String name,@RequestParam String intro) {
			Video preVideo = (Video) movieService.ReadByID("Video", id);
			preVideo.setEdittime(new Timestamp(new Date().getTime()));
			preVideo.setIntro(intro);
			preVideo.setName(name);
			movieService.update(preVideo);
		return new ModelAndView("redirect:/movie/listUI");
	}

	/**
	 * 删除相应视频
	 * @param file
	 * @param video
	 * @return
	 */
	@RequestMapping("delete/{id}")
	public String delete(@PathVariable Integer id, HttpServletRequest request) {
		Video video = (Video) movieService.ReadByID("Video", id);

		String thumbnailPath = video.getThumbnailurl(); // 获取视频缩略图的URL
		String path = video.getUrl(); // 获取处理后视频的URL
		String oripath = video.getOriurl(); // 获取上传视频的URL

		// 转换URL格式
		String thumbnailrealpath = request.getSession().getServletContext().getRealPath("/").replace('\\', '/')
				+ thumbnailPath;
		String realpath = request.getSession().getServletContext().getRealPath("/").replace('\\', '/') + path;
		String orirealpath = request.getSession().getServletContext().getRealPath("/").replace('\\', '/') + oripath;

		// 实例化到一个文件中依次进行删除操作
		File thumbnailfile = new File(thumbnailrealpath);
		File videofile = new File(realpath);
		File orivideofile = new File(orirealpath);

		if (thumbnailfile != null) {
			thumbnailfile.delete();
		}
		if (videofile != null) {
			videofile.delete();
		}
		if (orivideofile != null) {
			orivideofile.delete();
		}
		// 删除视频对象
		video.getCategory().getVideos().remove(video);
		video.getVideostate().getVideos().remove(video);
		movieService.deleteById(id);
		return "redirect:/movie/listUI";
	}
	
	/**
	 * 添加新视频
	 * @param file
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("add")
	public String add(HttpServletRequest request,@RequestParam(name = "file") CommonsMultipartFile file,@RequestParam String name,@RequestParam String intro) throws Exception{
		
		Video video = new Video();
		video.setIntro(intro);
		video.setName(name);
		if (!file.isEmpty()) {
			String newFileName = UUID.randomUUID().toString()+file.getOriginalFilename();
			
			//获取视频配置信息
			Configure folder_videoori_cfg=(Configure) movieService.ReadSingle("Configure", "name", "folder_videoori");
			Configure folder_thumbnail_cfg=(Configure) movieService.ReadSingle("Configure", "name", "folder_thumbnail");
			
			//获取上传视频的URL并保存
			String oriurl=folder_videoori_cfg.getVal()+"/"+newFileName;
			video.setOriurl(oriurl);
			
			//获取视频的类型并保存
			Category category=(Category) movieService.ReadSingle("Category", "id", 1);
			video.setCategory(category);
			
			//获取视频的状态信息并保存
			Videostate videostate=(Videostate) movieService.ReadSingle("Videostate", "order", 1);
			video.setVideostate(videostate);
			video.setIslive(0);
			
			//获取缩略图URL并保存
			String defaultthumbnail=folder_thumbnail_cfg.getVal()+"/default.jpg";
			video.setThumbnailurl(defaultthumbnail);
			
			//对视频进行保存操作
			movieService.save(video);
			
			//获取处理后视频的路径
			String realfileoriDir=request.getSession().getServletContext().getRealPath(folder_videoori_cfg.getVal()).replace('\\', '/');
			
			//判断如果不存在该路径则创建一个路径
			File realfileoriDirFile =new File(realfileoriDir);
			if(!realfileoriDirFile.exists()  && !realfileoriDirFile.isDirectory()){
				System.out.println("Directory not exist. Create it.");  
				System.out.println(realfileoriDirFile);  
				realfileoriDirFile.mkdir();
			}
			
			String realfileoriPath=realfileoriDir+"/"+newFileName;
			
			//获取目标文件并上传
			file.transferTo(new File(realfileoriPath));
//			File targetFile=new File(realfileoriPath);
//			upLoadFile(videofile,targetFile);

			//获取视频状态并保存
			videostate=(Videostate) movieService.ReadSingle("Videostate", "order", 2);
			video.setVideostate(videostate);
			
			//对视频进行更新操作
			movieService.update(video);
		}
		
		return "redirect:/movie/listUI";
	}

}
