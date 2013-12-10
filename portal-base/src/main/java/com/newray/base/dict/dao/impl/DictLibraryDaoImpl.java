package com.newray.base.dict.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.newray.base.crud.dao.impl.CommonGenericDAOImpl;
import com.newray.base.dict.dao.DictLibraryDao;
import com.newray.base.dict.model.DictCatalog;
import com.newray.base.dict.model.DictLibrary;
import com.newray.base.dict.model.DictLibraryId;

@Repository("base.dictLibraryDao")
public class DictLibraryDaoImpl extends CommonGenericDAOImpl<DictLibrary, DictLibraryId> implements DictLibraryDao {

	@Override
	public Map<DictCatalog,Map<String,DictLibrary>> findAllDictLibrary() {
		String hql = "from DictCatalog c,DictLibrary l where c.dictno = l.id.dictno and l.isinuse='1'";
		List<?> list = this.getSession().createQuery(hql).list();
		
		Map<DictCatalog,Map<String,DictLibrary>> dictMap = new HashMap<DictCatalog,Map<String,DictLibrary>>();
		for(Object objs : list){
			Object[] arr = (Object[])objs;
			DictCatalog catalog = (DictCatalog)arr[0];
			if(!dictMap.containsKey(catalog)){
				dictMap.put(catalog, new HashMap<String,DictLibrary>());
			}
			Map<String,DictLibrary> libMap = dictMap.get(catalog);
			
			DictLibrary library = (DictLibrary)arr[1];
			libMap.put(library.getId().getItemno(), library);
		}
		return dictMap;
	}
}
