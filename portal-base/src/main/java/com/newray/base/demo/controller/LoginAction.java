package com.newray.base.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.newray.base.demo.command.LoginCommand;
import com.newray.base.demo.model.TUserinfo;
import com.newray.base.demo.service.UserService;

@Controller
public class LoginAction {
	protected static final Logger logger = Logger.getLogger(LoginAction.class);
	
	private UserService userService;
	
	//以后info页面会有固定的访问url形式
	@RequestMapping({ "login_page.htm" })
	public String toLoginPage(HttpServletRequest req, HttpServletResponse res,@ModelAttribute("form") LoginCommand form){
		return "/login";
	}
	
	
	@RequestMapping({ "login_form.htm" })
	public String login(HttpServletRequest req, HttpServletResponse res,
			ModelMap map,@Valid @ModelAttribute("form") LoginCommand form,
			BindingResult result) {
		logger.info("login");
		HttpSession session = req.getSession();
		Object obj = session.getAttribute("User");
		if ((obj == null) && (form.getPassword() == null)
				&& (form.getUserName() == null))
			return "/login";

		if (result.hasErrors()) {
			logger.info(result.getFieldErrors());
			return "/login";
		}

		TUserinfo user = userService.login(form);

		System.out.println(user.getUsername());
		map.addAttribute("user", user);

		return "/result1";
	}

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}