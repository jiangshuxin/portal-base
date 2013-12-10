package com.newray.base.web.list.search;

import com.googlecode.genericdao.search.Filter;

/**
 * 逻辑操作符门面
 * @author justin.jiang
 *
 */
public interface LogicOperFacade {
	/**
	 * 创建条件过滤器
	 * @param key
	 * @param fieldName
	 * @param objects
	 * @return
	 */
	Filter[] buildFilter(String key,String fieldName, Object... objects);
}
