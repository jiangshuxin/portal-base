package com.newray.base.web.list.search.impl;

import java.util.Map;

import com.googlecode.genericdao.search.Filter;
import com.newray.base.web.list.search.LogicOperFacade;
import com.newray.base.web.list.search.LogicOperation;

/**
 * 逻辑操作符门面默认实现
 * @author justin.jiang
 *
 */
public class LogicOperFacadeImpl implements LogicOperFacade {
	private Map<String,LogicOperation> logicOperMap;

	@Override
	public Filter[] buildFilter(String key, String fieldName, Object... objects) {
		LogicOperation logicOperation = getLogicOperMap().get(key);
		if(logicOperation == null) throw new IllegalArgumentException(String.format("LogicOperationKey = %s can not be found!", key));
		return logicOperation.doLogic(fieldName, objects);
	}

	public Map<String, LogicOperation> getLogicOperMap() {
		return logicOperMap;
	}

	public void setLogicOperMap(Map<String, LogicOperation> logicOperMap) {
		this.logicOperMap = logicOperMap;
	}
}
