package com.newray.base.demo.service;

import java.util.List;

import com.newray.base.demo.command.LoginCommand;
import com.newray.base.demo.model.TUserinfo;

//Demo
public abstract interface UserService {
	TUserinfo login();
	TUserinfo login(LoginCommand paramLoginCommand);
	List<TUserinfo> getSomeUsers(LoginCommand paramLoginCommand);
	List<TUserinfo> getSomeUsers(String paramLoginCommand);
	List<TUserinfo> getSomeUsers(String paramLoginCommand,String str);
	List<TUserinfo> getSomeUsers(String paramLoginCommand,String str,LoginCommand loginCommand);
}