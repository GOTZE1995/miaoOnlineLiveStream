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

			//获取视频状态并保存
			videostate=(Videostate) movieService.ReadSingle("Videostate", "order", 2);
			video.setVideostate(videostate);
			
			//对视频进行更新操作
			movieService.update(video);
			
			session.setAttribute("RawVideo", new RawVideo(video));