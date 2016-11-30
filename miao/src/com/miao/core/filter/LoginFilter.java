package com.miao.core.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 后台登录过滤器
 * @author 程菊飞
 *
 */
public class LoginFilter implements Filter {
	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest serveletRequest, ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request=(HttpServletRequest)serveletRequest;
		HttpServletResponse response=(HttpServletResponse)servletResponse;
		HttpSession session=request.getSession();
		String uri=request.getRequestURI();
		//判断当前请求是否是登陆的请求地址
		if(!uri.contains("login_back")){
			//不是后台的登录请求
			if(uri.contains("UI")||uri.contains("delete")||uri.contains("add")||uri.contains("update")){
				//是后台的请求
				if(session.getAttribute("AdminUser")!=null){
					chain.doFilter(request, response);
					//已经登录，让其继续执行
				}else{
					//没有登陆，跳转到后台登录页面
					response.sendRedirect(request.getContextPath()+"/sys/login_back.do");
				}
			}else{
				//不是关于后台的请求
				chain.doFilter(request, response);
			}
	
		}else{
			//是后台的登陆请求
			chain.doFilter(request, response);
		}
	
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
