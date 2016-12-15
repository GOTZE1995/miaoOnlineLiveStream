package com.miao.movie.controller;

import java.io.File;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.miao.core.utils.Page;
import com.miao.entity.Video;
import com.miao.movie.listener.RawVideo;
import com.miao.movie.service.MovieService;

/**
 * 视频点播页面的跳转
 * 
 * @author songyulong 2016/12/11
 */
@Controller
@RequestMapping("/movie")
public class MovieController {

	@Resource
	private MovieService movieService;

	/**
	 * 前台动态获取视频，并进行分页显示
	 * 
	 * @author 程菊飞
	 * @param request
	 * @param searchName
	 * @return
	 */
	@RequestMapping("findMovie")
	public ModelAndView findMovies(@RequestParam(required = false, defaultValue = "1") Integer currentPage,
			String searchName) {
		Page<Video> page;
		ModelAndView modelAndView = new ModelAndView("videolist1");
		// 用户进行了搜索
		if (searchName == null || searchName.equals("")) {
			// 用户没有输入搜索数据
			page = movieService.pageListMovie(null, currentPage, 12);
		} else {
			// 根据用户的输入信息进行搜索
			List<Video> list = movieService.searchMovies(searchName);
			page = movieService.pageListMovie(list, currentPage, 12);
			// 将searchName设置成当前搜索的名称
			modelAndView.addObject("searchName", searchName);
		}
		modelAndView.addObject("page",page);
		return modelAndView;
	}

	/**
	 * 进入id为*的视频页面
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("viewRoom/{id}")
	public ModelAndView viewRoom(@PathVariable Integer id,HttpSession session) {
		List<Video> resultvideo = movieService.ReadLimitedByOrder("Video", "edittime",5,"desc");
		session.setAttribute("resultvideo", resultvideo);
		return new ModelAndView("videocontent", "video", movieService.findById(id));
	}

	/**
	 * 编辑视频信息
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("editUI/{id}")
	public ModelAndView editUI(@PathVariable Integer id) {

		return id == 0 ? new ModelAndView("videoedit")
				: new ModelAndView("videoedit", "video", movieService.findById(id));
	}

	/**
	 * 更新相应视频信息
	 * 
	 * @param file
	 * @param video
	 * @return
	 */
	@RequestMapping("update")
	public ModelAndView update(@RequestParam Integer id, @RequestParam String name, @RequestParam String intro) {
		Video preVideo = movieService.findById(id);
		preVideo.setEdittime(new Timestamp(new Date().getTime()));
		preVideo.setIntro(intro);
		preVideo.setName(name);
		movieService.update(preVideo);
		return new ModelAndView("redirect:/movie/findMovie");
	}

	/**
	 * 删除相应视频
	 * 
	 * @param file
	 * @param video
	 * @return
	 */
	@RequestMapping("delete/{id}")
	public String delete(@PathVariable Integer id, HttpServletRequest request) {
		Video video = movieService.findById(id);

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
		movieService.deleteById(id);
		return "redirect:/movie/findMovie";
	}

	/**
	 * 添加新视频
	 * 
	 * @param file
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("add")
	public String add(HttpServletRequest request, HttpSession session,
			@RequestParam(name = "file") CommonsMultipartFile file, @RequestParam String name,
			@RequestParam String intro) throws Exception {

		Video video = new Video();
		video.setEdittime(new Timestamp(new Date().getTime()));
		video.setIntro(intro);
		video.setName(name);
		if (!file.isEmpty()) {
			String newFileName = UUID.randomUUID().toString() + file.getOriginalFilename();

			// 获取上传视频的URL并保存
			String oriurl = "videoori" + "/" + newFileName;
			video.setOriurl(oriurl);

			// 获取视频的状态信息并保存
			// Videostate videostate=(Videostate)
			// movieService.ReadSingle("Videostate", "order", 1);
			// video.setVideostate(videostate);
			// video.setIslive(0);

			// 获取缩略图URL并保存
			String defaultthumbnail = "videothumbnail/default.jpg";
			video.setThumbnailurl(defaultthumbnail);

			// 对视频进行保存操作
			movieService.save(video);

			// 获取处理后视频的路径
			String realfileoriDir = request.getSession().getServletContext().getRealPath("videoori").replace('\\', '/');

			// 判断如果不存在该路径则创建一个路径
			File realfileoriDirFile = new File(realfileoriDir);
			if (!realfileoriDirFile.exists() && !realfileoriDirFile.isDirectory()) {
				System.out.println("Directory not exist. Create it.");
				System.out.println(realfileoriDirFile);
				realfileoriDirFile.mkdir();
			}

			String realfileoriPath = realfileoriDir + "/" + newFileName;

			// 获取目标文件并上传
			file.transferTo(new File(realfileoriPath));

			// //获取视频状态并保存
			// videostate=(Videostate) movieService.ReadSingle("Videostate",
			// "order", 2);
			// video.setVideostate(videostate);

			video.setState(video.BEFORE_TRANS);

			// 对视频进行更新操作
			movieService.update(video);

			session.setAttribute("RawVideo", new RawVideo(video));
		}

		return "redirect:/movie/findMovie";
	}

}
