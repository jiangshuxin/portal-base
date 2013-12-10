package com.newray.base.web.list.search.impl;

import java.util.Collection;

import com.googlecode.genericdao.search.Filter;

/**
 * 在……之间运算符
 * @author justin.jiang
 *
 */
public class BetweenOper extends BaseOper {

	@Override
	protected void doLogic(Collection<Filter> filters, String fieldName,
			Object... objects) {
		if(objects.length > 1){
			Filter f1 = Filter.greaterOrEqual(fieldName, objects[0]);
			Filter f2 = Filter.lessOrEqual(fieldName, objects[1]);
			filters.add(f1);
			filters.add(f2);
		}else{
			throw new IllegalArgumentException("Between参数不正确");
		}
	}
}
