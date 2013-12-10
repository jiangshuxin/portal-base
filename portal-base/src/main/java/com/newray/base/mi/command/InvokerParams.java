package com.newray.base.mi.command;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class InvokerParams {
	/**
	 * 方法编号，必选
	 */
	private String id;
	/**
	 * 方法名称，可选
	 */
	private String methodName;
	/**
	 * 参数类型，可选
	 */
	//private String[] paramTypes = new String[5];
	private List<String> paramTypes;
	/**
	 * 参数值，可选
	 */
	private List<Map<String,String>> paramMap = new ArrayList<Map<String,String>>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public List<String> getParamTypes() {
		return paramTypes;
	}

	public void setParamTypes(List<String> paramTypes) {
		this.paramTypes = paramTypes;
	}

	public List<Map<String, String>> getParamMap() {
		return paramMap;
	}

	public void setParamMap(List<Map<String, String>> paramMap) {
		this.paramMap = paramMap;
	}
}
