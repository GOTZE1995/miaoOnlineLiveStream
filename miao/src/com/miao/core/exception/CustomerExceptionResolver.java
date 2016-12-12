package com.miao.core.exception;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerExceptionResolverComposite;

public class CustomerExceptionResolver extends HandlerExceptionResolverComposite {
	public static Log log=LogFactory.getLog(CustomerExceptionResolver.class);   
	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		CustomerException customerException=null;
		 log.error(ex);
		 if(ex instanceof CustomerException){
			 customerException=(CustomerException) ex;
		 }else if(ex instanceof NullPointerException){
			customerException=new CustomerException("空指针异常");
		 }else{
			 customerException=new CustomerException("未知错误");
		 }
		 String message=customerException.getMessage();
		 ModelAndView modelAndView=new ModelAndView();    
		 modelAndView.addObject("message",message);
		 modelAndView.setViewName("error");  
		 return modelAndView;  

	}
	
}
