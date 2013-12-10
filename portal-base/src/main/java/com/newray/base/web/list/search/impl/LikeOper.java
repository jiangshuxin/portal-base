package com.newray.base.web.list.search.impl;

import java.util.Collection;

import com.googlecode.genericdao.search.Filter;

/**
 * 类似于运算符
 * @author justin.jiang
 *
 */
public class LikeOper extends BaseOper {

	@Override
	protected void doLogic(Collection<Filter> filters, String fieldName,
			Object... objects) {
		if(objects.length > 0){
			filters.add(Filter.like(fieldName, "%"+objects[0].toString()+"%"));
		}else{
			throw new IllegalArgumentException("Like参数不正确");
		}
	}
}
