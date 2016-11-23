package com.miao.room.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.miao.core.utils.Page;
import com.miao.entity.Role;
import com.miao.entity.Room;
import com.miao.entity.User;
import com.miao.room.service.RoomService;
import com.miao.user.service.UserService;

/**
 * 直播间控制器
 * 
 * @author 宋禹龙 2016/11/16
 */
@Controller
@RequestMapping("room")
public class RoomController {

	@Resource
	private RoomService roomService;

	@Resource
	private UserService userService;

	/**
	 * 后台直播间列表界面+查询功能
	 * 
	 * @param request
	 * @return jsp页面 2016/11/16
	 */
	@RequestMapping("/listUI")
	public String listUI(@RequestParam(required = false, defaultValue = "1") Integer currentPage,
			HttpServletRequest request, String searchName) {
		Page<Room> page;
		List<Room> list = null;
		if (searchName != null && !"".equals(searchName)) {
			// 根据搜索名查询
			list = roomService.doSearch(searchName);
			// 将serchName设置成当前搜索的名称
			request.setAttribute("searchName", searchName);
		}
		// 将list放到分页对象中，若没有数据，默认为第一页
		page = roomService.createPage(list, currentPage, 8);
		
		request.setAttribute("page", page);
		request.setAttribute("roomList", roomService.findAll());
		return "WEB-INF/detail/listRoom";
	}

	/**
	 * 后台更新界面
	 * 
	 * @param id
	 * @param request
	 * @return jsp页面 2016/11/16
	 */
	@RequestMapping("/updateUI")
	public String updateUI(Integer id, HttpServletRequest request) {
		if (id != null) {
			request.setAttribute("roomInfo", roomService.findById(id));
		}
		return "WEB-INF/detail/updateRoom";
	}

	/**
	 * 后台更新操作
	 * 
	 * @param room
	 * @return 2016/11/16
	 */
	@RequestMapping("/update")
	public String update(Room room) {
		if (room != null && room.getId() != null) {
			roomService.update(room);
		}
		return "redirect:/room/listUI.do";
	}

	/**
	 * 后台添加界面
	 * 
	 * @return jsp页面 2016/11/16
	 */
	@RequestMapping("/addUI")
	public String addUI() {
		return "WEB-INF/detail/saveRoom";
	}

	/**
	 * 后台添加操作
	 * 
	 * @param room
	 * @return 2016/11/16
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

			// 默认直播间状态为无效
			room.setStatus(Room.ROOM_STATUS_INVAILD);

			// 设置直播间创建时间
			room.setBeginDate(new Date());

			roomService.save(room);
		}
		return "redirect:/room/listUI.do";
	}

	/**
	 * 后台删除操作
	 * 
	 * @param id
	 * @return 2016/11/16
	 */
	@RequestMapping("/delete")
	public String delete(Integer id) {
		try {
			if (id != null) {
				roomService.deleteById(id);
			}
		} catch (Exception e) {
			// 删除失败，事务自动回滚，跳转到list页面
			return "redirect:/room/listUI.do";
		}
		return "redirect:/room/listUI.do";
	}

	/**
	 * 前台动态获取直播间列表
	 * 
	 * @author 孙兰云
	 * @param currentPage
	 * @param request
	 * @param searchName
	 * @return jsp页面 2016/11/22
	 */
	@RequestMapping("/findRoom")
	public String findRoom(@RequestParam(required = false, defaultValue = "1") Integer currentPage,
			HttpServletRequest request, String searchName) {
		Page<Room> page;
		// 用户输入了搜索数据
		if (searchName != null && !"".equals(searchName)) {
			List<Room> list = roomService.doSearch(searchName);
			page = roomService.createPage(list, currentPage, 12);
			request.setAttribute("searchName", searchName);
		}
		// 用户没有输入搜索数据
		else {
			page = roomService.createPage(null, currentPage, 12);
		}

		request.setAttribute("page", page);
		request.setAttribute("rooms", roomService.findAll());

		return "gallery";
	}

	/**
	 * 功能：注册直播间
	 * 
	 * @author 程菊飞
	 * @param room
	 * @param session
	 * @return
	 */
	@RequestMapping("/registMyRoom")
	public String registMyRoom(Room room, HttpSession session) {
		if (room != null) {
			String userName = room.getUser().getUserName();
			// 默认直播间状态为无效
			room.setStatus(Room.ROOM_STATUS_INVAILD);
			// 设置直播间创建时间
			room.setBeginDate(new Date());
			roomService.save(room);
			User user = (User) session.getAttribute("user");
			user.setRoom(room);
			session.setAttribute("user", user);
		}
		return "myinfo";
	}

	/**
	 * 功能：检查用户是否已经注册直播间
	 * 
	 * @author 程菊飞
	 * @param session
	 * @param response
	 */
	@RequestMapping("/checkRoom")
	public void checkRoom(HttpSession session, HttpServletResponse response) {
		try {
			String result = "true";
			// 检查该用户是否已经注册直播间
			User user = (User) session.getAttribute("user");
			Room room = user.getRoom();
			if (room != null) {
				result = "false";
			}
			// 向页面返回数据
			ServletOutputStream outputStream = response.getOutputStream();
			outputStream.write(result.getBytes());
			outputStream.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 功能：开始直播，将直播间的状态改为1
	 * 
	 * @author 程菊飞
	 * @param session
	 * @return 2016/11/22
	 */
	@RequestMapping("/updateRoomState")
	public String updateRoomStatus(HttpSession session) {
		User user = (User) session.getAttribute("user");
		Room room = user.getRoom();
		String status = room.getStatus();
		if (status == "0") {
			room.setStatus(Room.ROOM_STATUS_VAILD);
			roomService.update(room);
			session.setAttribute("user", user);
		} else {
			room.setStatus(Room.ROOM_STATUS_INVAILD);
			roomService.update(room);
			session.setAttribute("user", user);
		}
		return "myinfo";
	}

	/**
	 * 功能：修改直播间的信息
	 * 
	 * @author 程菊飞
	 * @param roomName
	 * @param memo
	 * @param session
	 * @return 2016/11/22
	 */
	@RequestMapping("/updateMyRoom")
	public String addRoom(@RequestParam("roomName") String roomName, @RequestParam("memo") String memo,
			HttpSession session) {
		User user = (User) session.getAttribute("user");
		Room room = user.getRoom();
		room.setRoomName(roomName);
		room.setMemo(memo);
		roomService.update(room);
		return "myTVinfo";
	}

	/**
	 * 功能：修改用户的联系方式
	 * 
	 * @author 程菊飞
	 * @param phone
	 * @param session
	 * @return
	 */
	@RequestMapping("/updateMyphoe")
	public String updateMyphone(@RequestParam("phone") String phone, HttpSession session) {
		User user = (User) session.getAttribute("user");
		Room room = user.getRoom();
		room.setPhone(phone);
		roomService.update(room);
		return "myTVinfo";
	}

}
