package com.newray.base.web.list.search.impl;

import java.util.Collection;

import com.googlecode.genericdao.search.Filter;

/**
 * 大于运算符
 * @author justin.jiang
 *
 */
public class GreaterThanOper extends BaseOper {

	@Override
	protected void doLogic(Collection<Filter> filters, String fieldName,
			Object... objects) {
		if(objects.length > 0){
			filters.add(Filter.greaterThan(fieldName, objects[0]));
		}else{
			throw new IllegalArgumentException("GreaterThan参数不正确");
		}
	}
}
