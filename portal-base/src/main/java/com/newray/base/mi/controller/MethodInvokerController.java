package com.newray.base.mi.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.newray.base.mi.command.InvokerParams;
import com.newray.base.mi.exception.MIException;
import com.newray.base.mi.service.MethodInvokerService;

@Controller
public class MethodInvokerController {
	protected static final Logger logger = Logger.getLogger(MethodInvokerController.class);
	private MethodInvokerService methodInvokerService;
	
	@RequestMapping({ "/common/srvInvoker.json" })
	public Object commonListQuery(HttpServletRequest req, HttpServletResponse res,InvokerParams invokerParams) throws MIException{
		return methodInvokerService.execute(invokerParams.getId(),invokerParams);
	}

	@Autowired
	public void setMethodInvokerService(MethodInvokerService methodInvokerService) {
		this.methodInvokerService = methodInvokerService;
	}
}
