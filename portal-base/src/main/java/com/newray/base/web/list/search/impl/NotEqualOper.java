package com.newray.base.web.list.search.impl;

import java.util.Collection;

import com.googlecode.genericdao.search.Filter;

/**
 * 不等于运算符
 * @author justin.jiang
 *
 */
public class NotEqualOper extends BaseOper {

	@Override
	protected void doLogic(Collection<Filter> filters, String fieldName,
			Object... objects) {
		if(objects.length > 0){
			filters.add(Filter.notEqual(fieldName, objects[0]));
		}else{
			throw new IllegalArgumentException("NotEqual参数不正确");		
		}
	}
}
