package com.newray.base.spring.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class SessionCheckInterceptor implements HandlerInterceptor {
	public static final String ERROR_NO_SESSION_JSP = "/pages/error_no_session.jsp";
	
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object paramObject) throws Exception {
		HttpSession session = request.getSession(false);
		if(session == null) {
			response.sendRedirect(request.getContextPath() + ERROR_NO_SESSION_JSP);
			return false;
		}
		//TODO 创造一个RuntimeContext，存储用户登陆信息，导航信息
		return true;
	}

	public void postHandle(HttpServletRequest paramHttpServletRequest,
			HttpServletResponse paramHttpServletResponse, Object paramObject,
			ModelAndView paramModelAndView) throws Exception {
		//System.out.println(paramObject);
	}

	public void afterCompletion(HttpServletRequest paramHttpServletRequest,
			HttpServletResponse paramHttpServletResponse, Object paramObject,
			Exception paramException) throws Exception {
		//System.out.println(paramObject);
	}
}