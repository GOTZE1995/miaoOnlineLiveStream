package com.miao.chat.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import net.sf.json.JSONObject;

/**
 * 聊天室
 * @author 孙兰云
 * 2016/11/30
 */
@ServerEndpoint("/chatroom")  //编程式注解
public class ChatServer {

	//设置消息发送日期的显示格式
	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("HH:mm:ss");
	
	// 静态变量，记录在线人数。
	public static int onlineCount = 0;

	// concurrent包的线程安全Set，用来存放每个客户端对应的ChatServer对象。
	private static CopyOnWriteArraySet<ChatServer> webSocketSet = new CopyOnWriteArraySet<ChatServer>();

	// 会话连接
	private Session session;
	
	/**
	 * 初始化一个会话
	 * @param session
	 */
	@OnOpen
	public void onOpen(Session session) {
		this.session = session;
		
		// 加入set中
		webSocketSet.add(this); 
		
		 // 在线数加1
		addOnlineCount();
		
		
		System.out.println("有新连接加入！当前在线人数为" + getOnlineCount());
	}

	@OnMessage
	public void onMessage(String message, Session session) {
		for (ChatServer item : webSocketSet) {
			try {
				
				// 将消息格式化成json
				JSONObject jsonObj = JSONObject.fromObject(message);

				// 在消息中添加发送时间
				jsonObj.put("date", DATE_FORMAT.format(new Date()));

				// 遍历所有的消息
				for (Session openSession : session.getOpenSessions()) {

					// 在消息中添加是否是当前会话的标志
					jsonObj.put("isSelf", openSession.equals(session));	
				}
				// 发送json格式的消息
				item.sendMessage(jsonObj.toString());
			} catch (IOException e) {
				e.printStackTrace();
				continue;
			}
		}
	}

	/**
	 * 关闭一个会话
	 */
	@OnClose
	public void onClose() {
		
		// 从set中删除
		webSocketSet.remove(this); 
		
		// 在线数减1
		subOnlineCount(); 
		
		System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
	}

	/**
	 *  错误处理
	 */
	@OnError
	public void onError(Throwable t) {
		System.out.println("发生错误");
		
		t.printStackTrace();
	}

	/**
	 * 发送消息
	 * @param message
	 * @throws IOException
	 */
	public void sendMessage(String message) throws IOException {
		this.session.getBasicRemote().sendText(message);
	}

	/**
	 * 获取在线人数
	 * @return
	 */
	public static synchronized int getOnlineCount() {
		return onlineCount;
	}

	/**
	 * 在线人数加1
	 */
	public static synchronized void addOnlineCount() {
		ChatServer.onlineCount++;
	}

	/**
	 * 在线人数减1
	 */
	public static synchronized void subOnlineCount() {
		ChatServer.onlineCount--;
	}
}
