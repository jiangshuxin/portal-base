package com.newray.base.mi.service;

import com.newray.base.mi.command.InvokerParams;
import com.newray.base.mi.command.InvokerResult;
import com.newray.base.mi.exception.MIException;

/**
 * 方法调用器编程接口，旨在通过serviceContext.xml中描述服务， 并由此调用，最终返回统一的结果。
 * <p>特点有：
 * <li>对于客户端来说，无需知道服务的类型，只需明白该服务的作用/id/所需参数即可；
 * <li>输入和输出都可以看作是动态抽象的；
 * <li>方法描述的基本属性通过统一的配置文件管理，serviceContext.xml
 * <p>适用于以下场景：
 * <li>js客户端通过本服务转发调用其他后台服务（无论是本地还是远程），满足基于前后端的SOA架构；
 * <li>本地客户端想调用一个动态（不确定的）服务
 * @author justin.jiang
 *
 */
public interface MethodInvokerService {
	InvokerResult execute(String id,InvokerParams invokerParams) throws MIException;
}
