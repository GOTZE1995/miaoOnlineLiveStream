package com.miao.chat.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
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

	// 设置消息发送日期的显示格式
	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("HH:mm:ss");

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
	}
	
	/**
	 * 关闭一个会话
	 */
	@OnClose
	public void onClose() {
		
		// 从set中删除
		webSocketSet.remove(this); 
	}

	@OnMessage
	public void onMessage(String message, Session session) {
		for (ChatServer item : webSocketSet) {
			try {
				
				// 将消息格式化成json
				JSONObject jsonObj = JSONObject.fromObject(message);

				// 在消息中添加发送时间
				jsonObj.put("date", DATE_FORMAT.format(new Date()));

				// 发送json格式的消息
				item.sendMessage(jsonObj.toString());
			} catch (IOException e) {
				e.printStackTrace();
				continue;
			}
		}
	}

	/**
	 * 发送消息
	 * @param message
	 * @throws IOException
	 */
	public void sendMessage(String message) throws IOException {
		this.session.getBasicRemote().sendText(message);
	}
}
