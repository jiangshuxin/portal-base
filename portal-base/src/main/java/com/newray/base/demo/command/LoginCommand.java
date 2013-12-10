package com.newray.base.demo.command;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.newray.base.web.command.BaseCommand;

public class LoginCommand extends BaseCommand {
	@NotEmpty
	@Size(min=2,max=5)
	private String userName;
	@NotEmpty
	private String password;

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}