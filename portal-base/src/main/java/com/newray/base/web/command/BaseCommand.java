package com.newray.base.web.command;

public abstract class BaseCommand {
	protected String token;

	public String getToken() {
		return this.token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}