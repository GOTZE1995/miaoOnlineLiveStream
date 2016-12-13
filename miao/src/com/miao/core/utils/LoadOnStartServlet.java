package com.miao.core.utils;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoadOnStartServlet extends HttpServlet {

	public LoadOnStartServlet() {
		super();
	}

	public void destroy() {
		super.destroy(); 
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	public void GetFFmpegInfo(){
		
		 try {
			String realdir=this.getServletContext().getRealPath("/").replace('\\', '/')+"test/";
			File realdirFile= new File(realdir);
			System.out.println(realdirFile);
			Process p ;
			p = Runtime.getRuntime().exec("cmd /c ffmpeg -version >"+realdir+"ffmpeg_version.txt",null,realdirFile);
			p = Runtime.getRuntime().exec("cmd /c ffmpeg -formats >"+realdir+"support_formats.txt",null,realdirFile);
			p = Runtime.getRuntime().exec("cmd /c ffmpeg -decoders >"+realdir+"support_decoders.txt",null,realdirFile);
			p = Runtime.getRuntime().exec("cmd /c ffmpeg -encoders >"+realdir+"support_encoders.txt",null,realdirFile);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	public void init() throws ServletException {

		ServletContext sc = this.getServletContext();  
		
		VideoThumbnailThread videoThumbnailThread=new VideoThumbnailThread(sc);
		videoThumbnailThread.start();

		VideoTranscoderThread videoConvertThread=new VideoTranscoderThread(sc);
		videoConvertThread.start();
	}
}
