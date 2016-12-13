package com.miao.core.utils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.miao.entity.Configure;
import com.miao.entity.Video;
import com.miao.entity.Videostate;
import com.miao.movie.service.MovieService;

public class VideoTranscoderThread extends Thread {
private ServletContext servletContext;
	
	public ServletContext getServletContext() {
		return servletContext;
	}

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	public VideoTranscoderThread(ServletContext servletContext) {
		super();
		this.servletContext = servletContext;
	}
	public void run() {
		try {
		int order=3;
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		MovieService movieService = (MovieService)ctx.getBean("movieService"); 
		//Load Configure
		Configure transcoder_vcodec_cfg=(Configure) movieService.ReadSingle("Configure", "name", "transcoder_vcodec");
		Configure transcoder_bv_cfg=(Configure) movieService.ReadSingle("Configure", "name", "transcoder_bv");
		Configure transcoder_framerate_cfg=(Configure) movieService.ReadSingle("Configure", "name", "transcoder_framerate");
		Configure transcoder_acodec_cfg=(Configure) movieService.ReadSingle("Configure", "name", "transcoder_acodec");
		Configure transcoder_ar_cfg=(Configure) movieService.ReadSingle("Configure", "name", "transcoder_ar");
		Configure transcoder_ba_cfg=(Configure) movieService.ReadSingle("Configure", "name", "transcoder_ba");
		Configure transcoder_scale_w_cfg=(Configure) movieService.ReadSingle("Configure", "name", "transcoder_scale_w");
		Configure transcoder_scale_h_cfg=(Configure) movieService.ReadSingle("Configure", "name", "transcoder_scale_h");
		Configure transcoder_watermarkuse_cfg=(Configure) movieService.ReadSingle("Configure", "name", "transcoder_watermarkuse");
		Configure transcoder_watermark_url_cfg=(Configure) movieService.ReadSingle("Configure", "name", "transcoder_watermark_url");
		Configure transcoder_watermark_x_cfg=(Configure) movieService.ReadSingle("Configure", "name", "transcoder_watermark_x");
		Configure transcoder_watermark_y_cfg=(Configure) movieService.ReadSingle("Configure", "name", "transcoder_watermark_y");
		Configure transcoder_keepaspectratio_cfg=(Configure) movieService.ReadSingle("Configure", "name", "transcoder_keepaspectratio");
		Configure transcoder_outfmt_cfg=(Configure) movieService.ReadSingle("Configure", "name", "transcoder_outfmt");
		Configure folder_video_cfg=(Configure) movieService.ReadSingle("Configure", "name", "folder_video");
		//Folder of Watermark
		String[] watermarkstrlist=transcoder_watermark_url_cfg.getVal().split("/");
		String watermarkDir="";
		String watermarkFile=watermarkstrlist[watermarkstrlist.length-1];
		for(int i=0;i<watermarkstrlist.length-1;i++){
			watermarkDir+=watermarkstrlist[i]+"/";
		}
		String realwatermarkDir=servletContext.getRealPath("/").replace('\\', '/')+watermarkDir;
		File realwatermarkDirFile= new File(realwatermarkDir);
		//Check
		if(!realwatermarkDirFile.exists()  && !realwatermarkDirFile.isDirectory()){
			System.out.println("Directory not exist. Create it.");  
			System.out.println(realwatermarkDirFile);  
			realwatermarkDirFile.mkdir();
		}
		
		String realfileDir=servletContext.getRealPath("/").replace('\\', '/')+folder_video_cfg.getVal();
		//Check
		File realfileDirFile =new File(realfileDir);
		if(!realfileDirFile.exists()  && !realfileDirFile.isDirectory()){
			System.out.println("Directory not exist. Create it."); 
			System.out.println(realfileDirFile);  
			realfileDirFile.mkdir();
		}

		do{
			List<Video> resultvideo=movieService.ReadByProperty("Video","videostate.order", order);
			Videostate nextvideostate=(Videostate) movieService.ReadSingle("Videostate","order", order+1);
				if(resultvideo!=null){
					for(Video video:resultvideo){
						//Transcode
						String filePath=folder_video_cfg.getVal()+"/"+video.getId()+"."+transcoder_outfmt_cfg.getVal();
						//System.out.println(filePath);
						video.setUrl(filePath);
						String realfilePath=servletContext.getRealPath("/").replace('\\', '/')+video.getUrl();
						
						String realfileoriginalPath=servletContext.getRealPath("/").replace('\\', '/')+video.getOriurl();
						String videotranscodecommand="cmd ";
						videotranscodecommand+="/c start ";
						//videotranscodecommand+="/c ";
						videotranscodecommand+="ffmpeg -y ";
						videotranscodecommand+="-i ";
						videotranscodecommand+="\""+realfileoriginalPath+"\" ";
						videotranscodecommand+="-vcodec "+transcoder_vcodec_cfg.getVal()+" ";
						videotranscodecommand+="-b:v "+transcoder_bv_cfg.getVal()+" ";
						videotranscodecommand+="-r "+transcoder_framerate_cfg.getVal()+" ";
						videotranscodecommand+="-acodec "+transcoder_acodec_cfg.getVal()+" ";
						videotranscodecommand+="-b:a "+transcoder_ba_cfg.getVal()+" ";
						videotranscodecommand+="-ar "+transcoder_ar_cfg.getVal()+" ";
						videotranscodecommand+="-vf ";
						videotranscodecommand+="scale=w="+transcoder_scale_w_cfg.getVal()+":h="+transcoder_scale_h_cfg.getVal();
						if(transcoder_keepaspectratio_cfg.getVal().equals("true")){
							videotranscodecommand+=":"+"force_original_aspect_ratio=decrease,pad=w="+
						transcoder_scale_w_cfg.getVal()+":h="+transcoder_scale_h_cfg.getVal()+":x=(ow-iw)/2:y=(oh-ih)/2";
						}
						videotranscodecommand+="[aa]";
						if(transcoder_watermarkuse_cfg.getVal().equals("true")){
							videotranscodecommand+=";movie=";
							videotranscodecommand+=watermarkFile;
							videotranscodecommand+="[bb];";
							videotranscodecommand+="[aa][bb]";
							videotranscodecommand+="overlay=x="+transcoder_watermark_x_cfg.getVal()+":y="+transcoder_watermark_y_cfg.getVal()+" ";
						}else{
							videotranscodecommand+=" ";
						}
						videotranscodecommand+="\"";
						videotranscodecommand+=realfilePath;
						videotranscodecommand+="\"";
						
						
						System.out.println(videotranscodecommand);
						Process process=Runtime.getRuntime().exec(videotranscodecommand,null,realwatermarkDirFile);
						//------------------------
						BufferedInputStream in = new BufferedInputStream(process.getInputStream()); 
						BufferedInputStream err = new BufferedInputStream(process.getErrorStream()); 
						BufferedReader inBr = new BufferedReader(new InputStreamReader(in));
						BufferedReader errBr = new BufferedReader(new InputStreamReader(err));
						String lineStr;
						while ((lineStr = inBr.readLine()) != null) { 
							System.out.println(lineStr);
						}
						while ((lineStr = errBr.readLine()) != null) { 
							System.out.println(lineStr);
						}
						
						if (process.waitFor() != 0) {  
							if (process.exitValue() == 1)  
								System.err.println("Failed!");  
						}  
						inBr.close();  
						in.close();  

						video.setVideostate(nextvideostate);
						movieService.update(video);
						//Rest--------------------------
						sleep(10 * 1000);
					}
				}
			sleep(10 * 1000);
		}while(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
