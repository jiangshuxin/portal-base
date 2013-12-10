package com.newray.base.mi.command;

/**
 * 调用结果
 * @author justin.jiang
 *
 */
public class InvokerResult {
	private boolean successFlag;
	private String errorMsg;
	private Object content;

	public boolean isSuccessFlag() {
		return successFlag;
	}

	public void setSuccessFlag(boolean successFlag) {
		this.successFlag = successFlag;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public Object getContent() {
		return content;
	}

	public void setContent(Object content) {
		this.content = content;
	}
}
