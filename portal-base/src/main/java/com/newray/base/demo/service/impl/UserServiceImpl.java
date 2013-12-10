package com.newray.base.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.newray.base.demo.command.LoginCommand;
import com.newray.base.demo.dao.UserDao;
import com.newray.base.demo.model.TUserinfo;
import com.newray.base.demo.service.UserService;
import com.newray.base.spring.SpringContextHolder;
//Demo
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
	private UserDao userDao;
	
	public TUserinfo login(LoginCommand comm) {
		DataSource ds = (DataSource) SpringContextHolder.getBean("dataSource",
				DataSource.class);

		TUserinfo user = userDao.findUserByID("201207041303182470000");

		return user;
	}

	@Override
	public TUserinfo login() {
		return login(null);
	}

	@Override
	public List<TUserinfo> getSomeUsers(String paramLoginCommand) {
		List<TUserinfo> list = new ArrayList<TUserinfo>();
		CollectionUtils.addAll(list, userDao.find("201207041303182470000","201307041303182470000"));
		return list;
	}

	@Override
	public List<TUserinfo> getSomeUsers(LoginCommand paramLoginCommand) {
		return getSomeUsers("");
	}


	@Override
	public List<TUserinfo> getSomeUsers(String paramLoginCommand, String str) {
		List<TUserinfo> list = new ArrayList<TUserinfo>();
		CollectionUtils.addAll(list, userDao.find("201207041303182470000","201307041303182470000"));
		return list;
	}

	@Override
	public List<TUserinfo> getSomeUsers(String paramLoginCommand,
			String str,LoginCommand loginCommand) {
		return getSomeUsers("");
	}

	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
}