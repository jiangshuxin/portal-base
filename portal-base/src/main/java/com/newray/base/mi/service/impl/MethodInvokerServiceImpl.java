package com.newray.base.mi.service.impl;

import java.util.Map;

import org.apache.log4j.Logger;

import com.newray.base.mi.command.InvokerParams;
import com.newray.base.mi.command.InvokerResult;
import com.newray.base.mi.exception.MIException;
import com.newray.base.mi.service.MethodInvokerService;
import com.newray.base.mi.service.handler.MethodInvokerHandler;

public class MethodInvokerServiceImpl implements MethodInvokerService {
	protected static final Logger logger = Logger.getLogger(MethodInvokerServiceImpl.class);
	
	private Map<String,Object> methodDescMap;
	private Map<String,MethodInvokerHandler> handlerMap;
	
	@Override
	public InvokerResult execute(String id,InvokerParams invokerParams) throws MIException {
		if(!getMethodDescMap().containsKey(id)) {
			logger.warn(String.format("找不到编号为%s的方法", id));
			throw new MIException(String.format("找不到编号为%s的方法", id));
		}
		
		Object methodDesc = getMethodDescMap().get(id);//methodDesc对象单例，其中属性不能随意修改
		String key = methodDesc.getClass().getSimpleName();
		if(!getHandlerMap().containsKey(key)) {
			logger.warn(String.format("找不到key=%s的方法处理器", key));
			throw new MIException(String.format(String.format("找不到key=%s的方法处理器", key)));
		}
		
		return getHandlerMap().get(key).handle(methodDesc,invokerParams.getMethodName(),invokerParams.getParamTypes(),invokerParams.getParamMap().toArray(new Map[invokerParams.getParamMap().size()]));
	}

	public Map<String, Object> getMethodDescMap() {
		return methodDescMap;
	}


	public void setMethodDescMap(Map<String, Object> methodDescMap) {
		this.methodDescMap = methodDescMap;
	}


	public Map<String, MethodInvokerHandler> getHandlerMap() {
		return handlerMap;
	}

	public void setHandlerMap(Map<String, MethodInvokerHandler> handlerMap) {
		this.handlerMap = handlerMap;
	}
}
