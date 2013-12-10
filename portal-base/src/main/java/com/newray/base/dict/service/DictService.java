package com.newray.base.dict.service;

import java.util.Map;

import com.newray.base.dict.model.DictLibrary;
import com.newray.base.spring.ReloadAware;

/**
 * 字典服务
 * @author justin.jiang
 *
 */
public interface DictService extends ReloadAware{
	/**
	 * 获取以ItemNo作为key的代码
	 * @param dictNo 编号
	 * @return
	 */
	Map<String,DictLibrary> getDictMap(String dictNo);
	
	/**
	 * 获取精确的代码对象
	 * @param dictNo 代码编号
	 * @param itemNo 代码栏目编号
	 * @return
	 */
	DictLibrary getDictLibrary(String dictNo,String itemNo);
	
	/**
	 * 获取代码栏目名称
	 * @param dictNo 代码编号
	 * @param itemNo 代码栏目编号
	 * @return
	 */
	String getDictItemName(String dictNo,String itemNo);
}
