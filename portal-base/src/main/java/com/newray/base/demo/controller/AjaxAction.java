package com.newray.base.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AjaxAction {
	//@ResponseBody //本注解可以不加，若不加会走view的正常解析，最后选择jsonView处理；若加，则不存在ModelAndView对象，Dispatcher会直接本方法return的内容
	@RequestMapping({ "index.json" })
	public Object testAjax(HttpServletRequest req, HttpServletResponse res,
			ModelMap map, Person rp) {
		//System.out.println("tag能不能拿到  " + rp.getPerson().getName());
		Person p = new Person();
		Person p1 = new Person();
		p.setAge(12);
		p.setName("中文");
		p1.setAge(33);
		p1.setName("测试");
		p.setPerson(p1);
		return p;
	}
}
