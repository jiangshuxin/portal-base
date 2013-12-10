package com.newray.base.demo.dao;

import com.googlecode.genericdao.dao.hibernate.GenericDAO;
import com.newray.base.demo.model.TUserinfo;
//Demo
/**
 * @author justin.jiang
 *
 */
public interface UserDao extends GenericDAO<TUserinfo, String>{
	TUserinfo findUserByID(String id);
}
