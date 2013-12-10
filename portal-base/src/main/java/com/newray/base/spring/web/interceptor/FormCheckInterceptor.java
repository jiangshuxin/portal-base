package com.newray.base.spring.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 实现思路：
 * 1.页面表单中增加当前页面的jsp路径，供拦截后返回
 * 2.info页面加载即给予token，该token在服务端session中保存一份，在form标签中
 * 3.后台对token进行比较，如发现没有直接到error页面，不匹配则提示重复提交，
 * 4.若提供jsp路径，出错后返回，没有则到error页面
 * @author justin.jiang
 *
 */
public class FormCheckInterceptor implements HandlerInterceptor {
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object paramObject) throws Exception {

		//response.sendRedirect(request.getRequestURI());
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