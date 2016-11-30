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

/**
 * 前台登录过滤器
 * @author 孙兰云
 * 2016/11/27
 */
public class PreLoginFilter implements Filter {

    public PreLoginFilter() {

    }

	public void destroy() {

	}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)resp;
		
		String uri = request.getRequestURI();
		
		//过滤非登录方法
		if (!uri.contains("login")) {
			Object object = request.getSession().getAttribute("user");
			if(uri.contains("UI")||uri.contains("delete")||uri.contains("add")||uri.contains("update")){
				//如果是后台的请求则放行
				chain.doFilter(request,response);
			}else{//前台的请求
				//已登录，放行
				if (object != null||uri.contains("check")||uri.contains("regist")){
					chain.doFilter(request, response);
				}
				//未登录，跳转到登录页面
				else{ 
					response.sendRedirect(request.getContextPath()+"/index.jsp");
				}
			}
		}   
		//登录方法，放行
		else{
			chain.doFilter(request, response);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {

	}
}
