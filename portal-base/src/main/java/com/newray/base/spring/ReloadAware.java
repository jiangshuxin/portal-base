package com.newray.base.spring;

/**
 * 使组件具有reload功能的编程接口
 * @author justin.jiang
 *
 */
public interface ReloadAware {
	/**
	 * 重载组件缓存
	 */
	void reload();
}
