package com.newray.base.demo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.newray.base.web.info.annotation.InfoUI;
import com.newray.base.web.list.annotation.ColumnUI;
import com.newray.base.web.list.annotation.DataGrid;
import com.newray.base.web.list.annotation.QueryCondition;
//Demo
@Entity
@Table(name = "T_USERINFO")
@DataGrid(width=1100,height=350,sortName="id",sortOrder="desc")
public class TUserinfo implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String usercode;
	private String password;
	private String username;
	private String type;
	private String state;
	private String stateShow;
	private String businesstype;
	private String address;
	private String email;
	private String idno;
	private String idtype;
	private String idtypeShow;
	private String passanswer;
	private String passquestion;
	private String phoneno;
	
	private String checkBox;

	public TUserinfo() {
		super();
	}

	public TUserinfo(String id) {
		this.id = id;
	}

	public TUserinfo(String id, String usercode, String password,
			String username, String type, String state, String businesstype,
			String address, String email, String idno, String idtype,
			String passanswer, String passquestion, String phoneno) {
		this.id = id;
		this.usercode = usercode;
		this.password = password;
		this.username = username;
		this.type = type;
		this.state = state;
		this.businesstype = businesstype;
		this.address = address;
		this.email = email;
		this.idno = idno;
		this.idtype = idtype;
		this.passanswer = passanswer;
		this.passquestion = passquestion;
		this.phoneno = phoneno;
	}

	@ColumnUI(frozen=true,order="00000",checkbox=true)
	public String getCheckBox() {
		return checkBox;
	}

	public void setCheckBox(String checkBox) {
		this.checkBox = checkBox;
	}

	@Id
	@Column(name = "ID", nullable = false, length = 100)
	@ColumnUI(frozen=true,title="编号",width=200,sortable=true,order="00100")
	@QueryCondition(order="00100")
	@InfoUI(id="ID", title="编号", order="00100", isRequired=true, validClass="easyui-validatebox", groupId="1")
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "USERCODE", length = 100)
	public String getUsercode() {
		return this.usercode;
	}

	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}

	@Column(name = "PASSWORD", length = 100)
	@ColumnUI(title="密码",width=200,sortable=true,order="00300")
	@QueryCondition(order="00200")
	@InfoUI(id="PASSWORD", title="密码",order="00300", isRequired=true, groupId="2")
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "USERNAME", length = 100)
	@ColumnUI(title="用户名",order="00200")
	@QueryCondition(order="00400",name="用户名")
	@InfoUI(id="USERNAME", title="用户名",order="00200", isRequired=true, groupId="3")
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
	@ColumnUI(title="状态true",order="00401",hidden=true)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@ColumnUI(title="状态",order="00402",dictNo="Status",dictRefColumn="state")
	public String getStateShow() {
		return stateShow;
	}

	public void setStateShow(String stateShow) {
		this.stateShow = stateShow;
	}

	@Column(name = "BUSINESSTYPE", length = 100)
	public String getBusinesstype() {
		return this.businesstype;
	}

	public void setBusinesstype(String businesstype) {
		this.businesstype = businesstype;
	}

	@Column(name = "ADDRESS")
	@ColumnUI(title="地址",width=500,order="00400")
	@QueryCondition(name="地址",order="00300")
	@InfoUI(id="ADDRESS", title="地址", order="00400", groupId="3")
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "EMAIL")
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "IDNO")
	public String getIdno() {
		return this.idno;
	}

	public void setIdno(String idno) {
		this.idno = idno;
	}

	@Column(name = "IDTYPE")
	@ColumnUI(title="测试是否(真实值)",width=100,order="00500")
	@QueryCondition(name="测试是否的查询条件1",order="00500")
	public String getIdtype() {
		return this.idtype;
	}

	public void setIdtype(String idtype) {
		this.idtype = idtype;
	}

	@ColumnUI(title="测试是否(转换后值)",width=100,order="00600",dictNo="YesNo",dictRefColumn="idtype")
	@QueryCondition(name="测试是否的查询条件2",order="00600")
	public String getIdtypeShow() {
		return idtypeShow;
	}

	public void setIdtypeShow(String idtypeShow) {
		this.idtypeShow = idtypeShow;
	}

	@Column(name = "PASSANSWER")
	public String getPassanswer() {
		return this.passanswer;
	}

	public void setPassanswer(String passanswer) {
		this.passanswer = passanswer;
	}

	@Column(name = "PASSQUESTION")
	public String getPassquestion() {
		return this.passquestion;
	}

	public void setPassquestion(String passquestion) {
		this.passquestion = passquestion;
	}

	@Column(name = "PHONENO")
	public String getPhoneno() {
		return this.phoneno;
	}

	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}
}