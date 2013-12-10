package com.newray.base.mi.service.handler;

import java.util.List;
import java.util.Map;

import com.newray.base.mi.command.InvokerResult;
import com.newray.base.mi.exception.MIException;

/**
 * TODO 抽象第一层定义输入和输出，第二层分本地服务和远程服务，第三层对远程服务进行细化，分为WSDL/JMS/Restful等
 * TODO 一期只做同步接口，异步在二期处理
 * @author justin.jiang
 *
 */
public interface MethodInvokerHandler {
	/**
	 * @param obj 方法描述MethodDesc
	 * @param methodName 方法名称
	 * @param paramTypes 方法参数类型集合
	 * @param paramMap 方法参数
	 * @return
	 * @throws MIException
	 */
	InvokerResult handle(Object obj,String methodName,List<String> paramTypes,Map<String, ?>... paramMap) throws MIException;
}
