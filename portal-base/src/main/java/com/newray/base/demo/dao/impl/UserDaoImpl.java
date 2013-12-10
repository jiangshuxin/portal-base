package com.newray.base.demo.dao.impl;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.newray.base.crud.dao.impl.CommonGenericDAOImpl;
import com.newray.base.demo.dao.UserDao;
import com.newray.base.demo.model.TUserinfo;
//Demo
//注意命名规则（Model+Dao）
@Repository("TUserinfoDao")
public class UserDaoImpl extends CommonGenericDAOImpl<TUserinfo, String> implements UserDao {
	
	@Transactional
	public TUserinfo findUserByID(String id) {
		String hql = "from TUserinfo t where exists (select 1 from VUserinfo v,t where t.id=v.id) and t.id=:id";
		Object obj = this.getSession().createQuery(hql).setString("id", id).uniqueResult();
		System.out.println(obj.getClass().getName());
		return (TUserinfo)obj;
	}
}