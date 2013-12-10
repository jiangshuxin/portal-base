package com.newray.base.mi.service.desc;

import java.util.ArrayList;
import java.util.List;

/**
 * 本地方法描述
 * @author justin.jiang
 *
 */
public class LocalMethodDesc {
	private String beanId;
	private String classPath;
	private String methodName;
	private List<String> paramTypes = new ArrayList<String>();

	public String getBeanId() {
		return beanId;
	}

	public void setBeanId(String beanId) {
		this.beanId = beanId;
	}

	public String getClassPath() {
		return classPath;
	}

	public void setClassPath(String classPath) {
		this.classPath = classPath;
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
}
