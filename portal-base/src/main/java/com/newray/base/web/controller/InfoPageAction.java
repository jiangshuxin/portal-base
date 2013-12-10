package com.newray.base.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.newray.base.crud.service.GeneralCRUDService;
import com.newray.base.demo.model.TUserinfo;
import com.newray.base.web.list.search.LogicOperFacade;
import com.newray.base.web.router.MVCRouter;
import com.newray.base.web.router.MVCRouterService;

@Controller
@Transactional
public class InfoPageAction {
	protected static final Logger logger = Logger.getLogger(ListPageAction.class);
	
	private GeneralCRUDService generalCRUDService;
	
	private LogicOperFacade logicOperFacade;
	
	private MVCRouterService mVCRouterService;
	
	public static final String CLASS_KEY = "_class";
	public static final String ROUTER_KEY = "_routerKey";
	
	/**
	 * info页面通用请求后缀
	 */
	public static final String INFO_ACTION_SUFFIX = "_infoPage.htm";
	
	/**
	 * info页面通用页面后缀
	 */
	public static final String INFO_JSP_SUFFIX = "Info";
	
	@RequestMapping({ "/**/*"+INFO_ACTION_SUFFIX })
	public String toInfoPage(HttpServletRequest req, HttpServletResponse res,ModelMap mm) throws ClassNotFoundException{
		String routerKeyAll = genRouterKeyAll(req);
		
		MVCRouter router = mVCRouterService.getRouter().get(routerKeyAll);
		Assert.notNull(router, String.format("传入的参数%s，找不到对应MVC路由", routerKeyAll));
		if(StringUtils.isEmpty(router.getModelClass())) throw new IllegalArgumentException("ModelClass不能为空");
		
		Class<?> clazz = Class.forName(router.getModelClass());
		
		mm.put(ROUTER_KEY, routerKeyAll);
		mm.put(CLASS_KEY, clazz);
		
		if(StringUtils.isNotEmpty(router.getJspPath())){
			return router.getJspPath();
		}else{
			return genDefaultJspPath(routerKeyAll);
		}
	}
	
	/**
	 * 生成默认的jsp路径
	 * @param routerKeyAll
	 * @return
	 */
	private String genDefaultJspPath(String routerKeyAll) {
		return routerKeyAll + INFO_JSP_SUFFIX;
	}

	/**
	 * 生成完整的routerKey
	 * @param req
	 * @param routerKey
	 * @return
	 */
	private String genRouterKeyAll(HttpServletRequest req) {
		String reqUri = req.getRequestURI();
		String contextPath = req.getContextPath();
		return StringUtils.replaceOnce(StringUtils.replaceOnce(reqUri, contextPath, ""),INFO_ACTION_SUFFIX,"");
	}
	
	@ResponseBody
	@RequestMapping({ "info/{daoName}/info_form.json" })
	public Object commonListQuery(HttpServletRequest req, HttpServletResponse res) throws ServletRequestBindingException{
		Map<String,Object> map = new HashMap<String,Object>();
		
		//此处的id应该在list选中记录里获取，选中则一定存在该对象
		return map;
	}
	
	@RequestMapping({"info/{daoName}/saveInfoForm.htm"})
	//@RequestMapping({"saveInfoForm.htm"})
	public void saveInfoForm(HttpServletRequest req, HttpServletResponse res, ModelMap map,
			@PathVariable("daoName")String daoName, @ModelAttribute("form")TUserinfo form, BindingResult result) {
		
	}

	@Autowired
	public void setGenericCRUDService(GeneralCRUDService generalCRUDService) {
		this.generalCRUDService = generalCRUDService;
	}

	@Autowired
	public void setLogicOperFacade(LogicOperFacade logicOperFacade) {
		this.logicOperFacade = logicOperFacade;
	}
	
	@Autowired
	public void setmVCRouterService(MVCRouterService mVCRouterService) {
		this.mVCRouterService = mVCRouterService;
	}
}
