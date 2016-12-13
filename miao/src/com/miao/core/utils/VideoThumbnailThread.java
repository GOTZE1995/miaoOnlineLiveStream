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

public class VideoThumbnailThread extends Thread {
	private ServletContext servletContext;
	
	public ServletContext getServletContext() {
		return servletContext;
	}

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
	public VideoThumbnailThread(ServletContext servletContext) {
		super();
		this.servletContext = servletContext;
	}
	public void run() {
		try {
		int order=2;
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		MovieService movieService = (MovieService)ctx.getBean("movieService"); 
		
		Configure thumbnail_ss_cfg=(Configure) movieService.ReadSingle("Configure", "name", "thumbnail_ss");
		Configure folder_thumbnail_cfg=(Configure) movieService.ReadSingle("Configure", "name", "folder_thumbnail");

		String realthumbnailDir=servletContext.getRealPath("/").replace('\\', '/')+folder_thumbnail_cfg.getVal();
		//Check
		File realthumbnailDirFile =new File(realthumbnailDir);
		if(!realthumbnailDirFile.exists()  && !realthumbnailDirFile.isDirectory()){
			System.out.println("Directory not exist. Create it.");  
			System.out.println(realthumbnailDirFile);  
			realthumbnailDirFile.mkdir();
		}
		
		do{
			List<Video> resultvideo=movieService.ReadByProperty("Video","videostate.order", order);
			Videostate nextvideostate=(Videostate) movieService.ReadSingle("Videostate","order", order+1);
			
			Videostate nextvideostate2=(Videostate) movieService.ReadSingle("Videostate","order", order+2);
				if(resultvideo!=null){
					for(Video video:resultvideo){
						String realfileoriPath;
						if(video.getIslive()==0){
							realfileoriPath=servletContext.getRealPath("/").replace('\\', '/')+video.getOriurl();
							//System.out.println(realfileoriPath);
						}else{
							realfileoriPath=video.getUrl();
							String a[] = realfileoriPath.split(":");  
							//RTMP FIX: libRTMP URL
						    if(a[0].equals("rtmp")||a[0].equals("rtmpe")||a[0].equals("rtmpte")||a[0].equals("rtmps")){
						    	realfileoriPath=realfileoriPath+" live=1";
						    }
						}
						String realthumbnailPath=realthumbnailDir+"/"+video.getId()+".jpg";
						
						String videothumbnailcommand="cmd /c start ffmpeg -y -i "+"\""+realfileoriPath+"\""+
						" -ss "+thumbnail_ss_cfg.getVal()+" -s 220x110 -f image2 -vframes 1 "+"\""+realthumbnailPath+"\"";
						System.out.println(videothumbnailcommand);
						Process process=Runtime.getRuntime().exec(videothumbnailcommand);
						//------------------------
						BufferedInputStream in = new BufferedInputStream(process.getInputStream());  
						BufferedReader inBr = new BufferedReader(new InputStreamReader(in));  
						String lineStr;  
						while ((lineStr = inBr.readLine()) != null)  
								System.out.println(lineStr);
						if (process.waitFor() != 0) {  
							if (process.exitValue() == 1)  
								System.err.println("Failed!");  
						}  
						inBr.close();  
						in.close();  
						
						video.setThumbnailurl(folder_thumbnail_cfg.getVal()+"/"+video.getId()+".jpg");
						
						if(video.getIslive()==0){
							video.setVideostate(nextvideostate);
						}else{
							video.setVideostate(nextvideostate2);
						}
						
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
