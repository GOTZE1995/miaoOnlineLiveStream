package com.miao.room.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.miao.core.utils.Page;
import com.miao.entity.Room;
import com.miao.entity.User;
import com.miao.room.service.RoomService;
import com.miao.user.service.UserService;

/**
 * 直播间控制器
 * @author 宋禹龙
 * 2016/11/16
 */
@Controller
@RequestMapping("room")
public class RoomController {
	
	@Resource
	private RoomService roomService;

	@Resource
	private UserService userService;

	/**
	 * 直播间列表界面+查询功能
	 * @author 宋禹龙
	 * @param request
	 * @return jsp页面
	 * 2016/11/16
	 */
	@RequestMapping("/listUI")
	public String listUI(@RequestParam(required=false,defaultValue="1") Integer currentPage , HttpServletRequest request,
			String searchName){
		Page<Room> page ;
		//用户输入了搜索数据
		if (searchName !=null &&  !"".equals(searchName)) {
			List<Room> list = roomService.doSearch(searchName);
			page = roomService.createPage(list, currentPage, 8);
			request.setAttribute("searchName", searchName);
		}
		//用户没有输入搜索数据
		else{
			page = roomService.createPage(null, currentPage, 8);
		}
		request.setAttribute("page", page);
		request.setAttribute("roomList", roomService.findAll());
		return "WEB-INF/detail/listRoom";
	}

	/**
	 * 更新界面
	 * @author 宋禹龙
	 * @param id
	 * @param request
	 * @return jsp页面
	 * 2016/11/16
	 */
	@RequestMapping("/updateUI")
	public String updateUI(Integer id, HttpServletRequest request) {
		if (id != null) {
			request.setAttribute("roomInfo", roomService.findById(id));
		}
		return "WEB-INF/detail/updateRoom";
	}

	/**
	 * 更新操作
	 * @author 宋禹龙
	 * @param room
	 * @return
	 * 2016/11/16
	 */
	@RequestMapping("/update")
	public String update(Room room) {
		if (room != null && room.getId() != null) {
			roomService.update(room);
		}
		return "redirect:/room/listUI.do";
	}

	/**
	 * 添加界面
	 * @author 宋禹龙
	 * @return jsp页面
	 * 2016/11/16
	 */
	@RequestMapping("/addUI")
	public String addUI() {
		return "WEB-INF/detail/saveRoom";
	}

	/**
	 * 添加操作
	 * @author 宋禹龙
	 * @param room
	 * @return
	 * 2016/11/16
	 */
	@RequestMapping("/add")
	public String add(Room room) {
		if (room != null) {
			String userName = room.getUser().getUserName();

			// 设置直播间所属人
			if (userName != null) {
				// 先根据用户名查询
				List<User> list = userService.findAllUsersByName(userName);

				if (list != null && list.size() > 0) {
					room.setUser(list.get(0));
				}
			}

			// 默认直播间状态为有效
			room.setStatus(Room.ROOM_STATUS_VAILD);

			// 设置直播间创建时间
			room.setBeginDate(new Date());

			roomService.save(room);
		}
		return "redirect:/room/listUI.do";
	}

	/**
	 * 删除操作
	 * @author 宋禹龙
	 * @param id
	 * @return
	 * 2016/11/16
	 */
	@RequestMapping("/delete")
	public String delete(Integer id) {
		try {
			if (id != null){
				roomService.deleteById(id);
			}
		} catch (Exception e) {
			//删除失败，事务自动回滚，跳转到list页面
			return "redirect:/room/listUI.do";
		}
		return "redirect:/room/listUI.do";
	}
}
