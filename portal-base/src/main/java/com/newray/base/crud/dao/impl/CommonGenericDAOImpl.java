package com.newray.base.crud.dao.impl;

import java.io.Serializable;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.googlecode.genericdao.dao.hibernate.GenericDAOImpl;

/**
 * 扩展GenericDAOImpl，自动装配SessionFactory
 * @author justin.jiang
 *
 * @param <T>
 * @param <ID>
 */
public class CommonGenericDAOImpl<T, ID extends Serializable> extends GenericDAOImpl<T, ID> {

	@Override
	@Autowired(required=true)
	public void setSessionFactory(@Qualifier("sessionFactory")SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
}
