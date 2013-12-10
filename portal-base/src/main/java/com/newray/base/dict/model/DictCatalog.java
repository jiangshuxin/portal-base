package com.newray.base.dict.model;

// Generated 2013-5-11 15:43:03 by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 字典目录
 * DictCatalog generated by hbm2java
 */
@Entity
@Table(name = "DICT_CATALOG", schema = "NEWRAY")
public class DictCatalog implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String dictno;
	private String sortno;
	private String parentdictno;
	private String dictname;
	private String dictdescribe;
	private String dictattribute;
	private String inputuser;
	private String inputorg;
	private String inputdate;
	private String updateuser;
	private String updatedate;
	private String remark;

	public DictCatalog() {
	}

	public DictCatalog(String dictno) {
		this.dictno = dictno;
	}

	public DictCatalog(String dictno, String sortno, String parentdictno,
			String dictname, String dictdescribe, String dictattribute,
			String inputuser, String inputorg, String inputdate,
			String updateuser, String updatedate, String remark) {
		this.dictno = dictno;
		this.sortno = sortno;
		this.parentdictno = parentdictno;
		this.dictname = dictname;
		this.dictdescribe = dictdescribe;
		this.dictattribute = dictattribute;
		this.inputuser = inputuser;
		this.inputorg = inputorg;
		this.inputdate = inputdate;
		this.updateuser = updateuser;
		this.updatedate = updatedate;
		this.remark = remark;
	}

	@Id
	@Column(name = "DICTNO", unique = true, nullable = false, length = 32)
	public String getDictno() {
		return this.dictno;
	}

	public void setDictno(String dictno) {
		this.dictno = dictno;
	}

	@Column(name = "SORTNO", length = 32)
	public String getSortno() {
		return this.sortno;
	}

	public void setSortno(String sortno) {
		this.sortno = sortno;
	}

	@Column(name = "PARENTDICTNO", length = 32)
	public String getParentdictno() {
		return this.parentdictno;
	}

	public void setParentdictno(String parentdictno) {
		this.parentdictno = parentdictno;
	}

	@Column(name = "DICTNAME", length = 80)
	public String getDictname() {
		return this.dictname;
	}

	public void setDictname(String dictname) {
		this.dictname = dictname;
	}

	@Column(name = "DICTDESCRIBE", length = 250)
	public String getDictdescribe() {
		return this.dictdescribe;
	}

	public void setDictdescribe(String dictdescribe) {
		this.dictdescribe = dictdescribe;
	}

	@Column(name = "DICTATTRIBUTE", length = 250)
	public String getDictattribute() {
		return this.dictattribute;
	}

	public void setDictattribute(String dictattribute) {
		this.dictattribute = dictattribute;
	}

	@Column(name = "INPUTUSER", length = 32)
	public String getInputuser() {
		return this.inputuser;
	}

	public void setInputuser(String inputuser) {
		this.inputuser = inputuser;
	}

	@Column(name = "INPUTORG", length = 32)
	public String getInputorg() {
		return this.inputorg;
	}

	public void setInputorg(String inputorg) {
		this.inputorg = inputorg;
	}

	@Column(name = "INPUTDATE", length = 20)
	public String getInputdate() {
		return this.inputdate;
	}

	public void setInputdate(String inputdate) {
		this.inputdate = inputdate;
	}

	@Column(name = "UPDATEUSER", length = 32)
	public String getUpdateuser() {
		return this.updateuser;
	}

	public void setUpdateuser(String updateuser) {
		this.updateuser = updateuser;
	}

	@Column(name = "UPDATEDATE", length = 20)
	public String getUpdatedate() {
		return this.updatedate;
	}

	public void setUpdatedate(String updatedate) {
		this.updatedate = updatedate;
	}

	@Column(name = "REMARK", length = 250)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
