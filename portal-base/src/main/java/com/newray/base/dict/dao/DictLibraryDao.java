package com.newray.base.dict.dao;

import java.util.Map;

import com.googlecode.genericdao.dao.hibernate.GenericDAO;
import com.newray.base.dict.model.DictCatalog;
import com.newray.base.dict.model.DictLibrary;
import com.newray.base.dict.model.DictLibraryId;

/**
 * 字典Dao
 * @author hlpeng
 *
 */
public interface DictLibraryDao extends GenericDAO<DictLibrary,DictLibraryId>{
	/**
	 * 获取代码表全部信息
	 * @return
	 */
	Map<DictCatalog,Map<String,DictLibrary>> findAllDictLibrary();
}
