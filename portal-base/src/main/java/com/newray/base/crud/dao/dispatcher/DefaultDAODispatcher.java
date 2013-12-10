package com.newray.base.crud.dao.dispatcher;

import java.util.HashMap;
import java.util.Map;

import org.springframework.aop.support.AopUtils;
import org.springframework.core.GenericTypeResolver;
import org.springframework.stereotype.Repository;

import com.googlecode.genericdao.dao.hibernate.DAODispatcher;
import com.newray.base.spring.ReloadAware;
import com.newray.base.spring.SpringContextHolder;

/**
 * 容器初始化完毕后，向DAODispatcher填充SpecificDAOs
 * @author justin.jiang
 *
 */
public class DefaultDAODispatcher extends DAODispatcher implements ReloadAware {
	
	@Override
	public void reload() {
		if(super.specificDAOs != null) return;
		Map<String, Object> specificDAOs = new HashMap<String,Object>();
		Map<String,Object> map = SpringContextHolder.getContext().getBeansWithAnnotation(Repository.class);
		for(String key : map.keySet()){
			Object dao_obj = map.get(key);
			//获取代理对象原对象
			Class<?> original = AopUtils.getTargetClass(dao_obj);
			//获取泛型
			Class<?>[] clazzes = GenericTypeResolver.resolveTypeArguments(original,original.getSuperclass());
			//只取第一个Model类型
			if(clazzes != null && clazzes.length > 0){
				Class<?> modelClass = clazzes[0];
				//构建以modelClass作为key，dao对象句柄作为value的map
				specificDAOs.put(modelClass.getName(), dao_obj);
			}
		}
		super.setSpecificDAOs(specificDAOs);
	}
}
