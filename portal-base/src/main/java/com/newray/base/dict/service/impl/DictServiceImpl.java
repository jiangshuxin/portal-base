package com.newray.base.dict.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.newray.base.dict.dao.DictLibraryDao;
import com.newray.base.dict.model.DictCatalog;
import com.newray.base.dict.model.DictLibrary;
import com.newray.base.dict.service.DictService;

@Service("base.dictService")
public class DictServiceImpl implements DictService {
	private Map<String,Map<String,DictLibrary>> simpleDictMap;
	private Map<DictCatalog,Map<String,DictLibrary>> objDictMap;
	
	private DictLibraryDao dictLibraryDao;
	
	@Override
	@Transactional
	public void reload() {
		//reload时重新分配内存
		this.objDictMap = this.dictLibraryDao.findAllDictLibrary();
		simpleDictMap = new HashMap<String,Map<String,DictLibrary>>();
		for(DictCatalog catalog : this.objDictMap.keySet()){
			simpleDictMap.put(catalog.getDictno(), this.objDictMap.get(catalog));
		}
	}

	@Override
	public Map<String, DictLibrary> getDictMap(String dictNo) {
		if(!simpleDictMap.containsKey(dictNo)) return null;
		return simpleDictMap.get(dictNo);
	}

	@Override
	public DictLibrary getDictLibrary(String dictNo, String itemNo) {
		Map<String, DictLibrary> map = this.getDictMap(dictNo);
		if(map == null || !map.containsKey(itemNo)) return null;
		return map.get(itemNo);
	}

	@Override
	public String getDictItemName(String dictNo, String itemNo) {
		DictLibrary dictLibrary = this.getDictLibrary(dictNo, itemNo);
		if(dictLibrary == null) return null;
		return dictLibrary.getItemname();
	}

	public Map<String, Map<String, DictLibrary>> getSimpleDictMap() {
		return simpleDictMap;
	}

	public void setSimpleDictMap(Map<String, Map<String, DictLibrary>> simpleDictMap) {
		this.simpleDictMap = simpleDictMap;
	}

	public Map<DictCatalog, Map<String, DictLibrary>> getObjDictMap() {
		return objDictMap;
	}

	public void setObjDictMap(Map<DictCatalog, Map<String, DictLibrary>> objDictMap) {
		this.objDictMap = objDictMap;
	}

	@Autowired
	public void setDictLibraryDao(DictLibraryDao dictLibraryDao) {
		this.dictLibraryDao = dictLibraryDao;
	}
}
