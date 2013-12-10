package com.newray.base.crud.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.genericdao.dao.hibernate.GeneralDAO;
import com.googlecode.genericdao.search.ISearch;
import com.googlecode.genericdao.search.SearchResult;
import com.newray.base.crud.service.GeneralCRUDService;

@Service("base.generalCRUDService")
@Transactional
public class GeneralCRUDServiceImpl implements GeneralCRUDService {
	private GeneralDAO generalDAO;

	@Override
	public <T> T find(Class<T> type, Serializable id) {
		return generalDAO.find(type, id);
	}

	@Override
	public <T> T[] find(Class<T> type, Serializable... ids) {
		return generalDAO.find(type, ids);
	}

	@Override
	public <T> T getReference(Class<T> type, Serializable id) {
		return generalDAO.getReference(type, id);
	}

	@Override
	public <T> T[] getReferences(Class<T> type, Serializable... ids) {
		return generalDAO.getReferences(type, ids);
	}

	@Override
	public boolean save(Object entity) {
		return generalDAO.save(entity);
	}

	@Override
	public boolean[] save(Object... entities) {
		return generalDAO.save(entities);
	}

	@Override
	public boolean remove(Object entity) {
		return generalDAO.remove(entity);
	}

	@Override
	public void remove(Object... entities) {
		generalDAO.remove(entities);
	}

	@Override
	public boolean removeById(Class<?> type, Serializable id) {
		return generalDAO.removeById(type, id);
	}

	@Override
	public void removeByIds(Class<?> type, Serializable... ids) {
		generalDAO.removeByIds(type, ids);
	}

	@Override
	public <T> List<T> findAll(Class<T> type) {
		return generalDAO.findAll(type);
	}

	@Override
	public List<?> search(ISearch search) {
		return generalDAO.search(search);
	}

	@Override
	public Object searchUnique(ISearch search) {
		return generalDAO.searchUnique(search);
	}

	@Override
	public int count(ISearch search) {
		return generalDAO.count(search);
	}

	@Override
	public SearchResult<?> searchAndCount(ISearch search) {
		return generalDAO.searchAndCount(search);
	}

	@Override
	public boolean isAttached(Object entity) {
		return generalDAO.isAttached(entity);
	}

	@Override
	public void refresh(Object... entities) {
		generalDAO.refresh(entities);
	}

	@Override
	public void flush() {
		generalDAO.flush();
	}

	@Autowired
	public void setGeneralDAO(@Qualifier("hibernate.DAODispatcher")GeneralDAO generalDAO) {
		this.generalDAO = generalDAO;
	}
}
