package com.newray.base.web.list.search.impl;

import java.util.ArrayList;
import java.util.Collection;

import com.googlecode.genericdao.search.Filter;
import com.newray.base.web.list.search.LogicOperation;

/**
 * 逻辑操作运算基类
 * @author justin.jiang
 *
 */
public abstract class BaseOper implements LogicOperation {

	@Override
	public final Filter[] doLogic(String fieldName, Object... objects) {
		Collection<Filter> filters = new ArrayList<Filter>();
		doLogic(filters,fieldName,objects);
		return filters.toArray(new Filter[0]);
	}

	/**
	 * 获取filter条件，并使用filters收集
	 * @param filters
	 * @param fieldName
	 * @param objects
	 */
	protected abstract void doLogic(Collection<Filter> filters, String fieldName,Object... objects);
}
