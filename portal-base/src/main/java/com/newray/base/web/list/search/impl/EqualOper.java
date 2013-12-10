package com.newray.base.web.list.search.impl;

import java.util.Collection;

import com.googlecode.genericdao.search.Filter;

/**
 * 等于运算符
 * @author justin.jiang
 *
 */
public class EqualOper extends BaseOper {

	@Override
	protected void doLogic(Collection<Filter> filters, String fieldName,
			Object... objects) {
		if(objects.length > 0){
			filters.add(Filter.equal(fieldName, objects[0]));
		}else{
			throw new IllegalArgumentException("equal参数不正确");
		}
	}
}
