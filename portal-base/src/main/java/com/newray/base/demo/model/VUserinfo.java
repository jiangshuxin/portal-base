package com.newray.base.demo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
//Demo
@Entity
@Table(name = "V_USERINFO")
public class VUserinfo implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String password;
	private String username;
	private String type;
	private String state;
	private String address;

	public VUserinfo() {
		super();
	}

	public VUserinfo(String id) {
		this.id = id;
	}

	public VUserinfo(String id, String password, String username, String type,
			String state, String address) {
		super();
		this.id = id;
		this.password = password;
		this.username = username;
		this.type = type;
		this.state = state;
		this.address = address;
	}

	@Id
	@Column(name = "ID", nullable = false, length = 100)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "PASSWORD", length = 100)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "USERNAME", length = 100)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "TYPE", length = 100)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "STATE", length = 10)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "ADDRESS")
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}