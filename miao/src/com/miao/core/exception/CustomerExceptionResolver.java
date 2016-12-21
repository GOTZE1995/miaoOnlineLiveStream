package com.miao.core.exception;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerExceptionResolverComposite;

public class CustomerExceptionResolver extends HandlerExceptionResolverComposite {
	public static Log log=LogFactory.getLog(CustomerExceptionResolver.class);   
	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		 //得到是哪个类抛出的异常
		 String name=((HandlerMethod) handler).getMethod().getName();
		 log.error(name+"这个方法抛出了异常："+ex);
		 String message=ex.getMessage();
		 ModelAndView modelAndView=new ModelAndView();    
		 modelAndView.addObject("message",message);
		 modelAndView.setViewName("error");  
		 return modelAndView;  

	}
	
}
