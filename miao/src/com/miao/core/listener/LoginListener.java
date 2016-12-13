package com.miao.core.listener;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import com.miao.entity.User;

/**
 * 前台登录监听器
 * @author 冯鑫
 *
 */

/**
 * Application Lifecycle Listener implementation class LoginListener
 *
 */
@WebListener
public class LoginListener implements HttpSessionAttributeListener {

	/**
	 * 用于存放账号和session对应关系的map
	 */
	private Map<String, HttpSession> map = new HashMap<String, HttpSession>();


	/**
	 * 当向session中放入数据触发
	 */
	/**
     * @see HttpSessionAttributeListener#attributeAdded(HttpSessionBindingEvent)
     */
    public void attributeAdded(HttpSessionBindingEvent event)  { 
         // TODO Auto-generated method stub
    	String name = event.getName();
		if (name.equals("loginuser")) {
			User user = (User) event.getValue();
			if (map.get(user.getUserName()) != null) {
				HttpSession session = map.get(user.getUserName());
				session.removeAttribute(user.getUserName());
				session.invalidate();
			}
			map.put(user.getUserName(), event.getSession());
		}

    }

    /**
	 * 当向session中移除数据触发
	 */
	/**
     * @see HttpSessionAttributeListener#attributeRemoved(HttpSessionBindingEvent)
     */
    public void attributeRemoved(HttpSessionBindingEvent event)  { 
         // TODO Auto-generated method stub
    	String name = event.getName();
		if (name.equals("loginuser")) {
			User user = (User) event.getValue();
			map.remove(user.getUserName());

		}
    }

	/**
     * @see HttpSessionAttributeListener#attributeReplaced(HttpSessionBindingEvent)
     */
    public void attributeReplaced(HttpSessionBindingEvent arg0)  { 
         // TODO Auto-generated method stub
    }
    
    public Map<String, HttpSession> getMap() {
		return map;
	}

	public void setMap(Map<String, HttpSession> map) {
		this.map = map;
	}

	
}
