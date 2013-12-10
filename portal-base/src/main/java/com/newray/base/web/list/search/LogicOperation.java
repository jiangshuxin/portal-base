package com.newray.base.web.list.search;

import com.googlecode.genericdao.search.Filter;

/**
 * 逻辑操作符编程接口
 * @author justin.jiang
 *
 */
public interface LogicOperation {
	/**
	 * 获取条件过滤数组
	 * @param fieldName
	 * @param objects
	 * @return
	 */
	Filter[] doLogic(String fieldName,Object...objects);
}
