package com.newray.base.crud.service;

import java.io.Serializable;
import java.util.List;

import com.googlecode.genericdao.search.ISearch;
import com.googlecode.genericdao.search.SearchResult;

/**
 * 通用的增删改查服务
 * <p>任何增删改查的需求可以通过注入此服务来满足，主要特点有：
 * <ol>
 * 	<li>普通的增删改查无需注入具体接口类型的DAO，使用本服务即可
 * 	<li>将类级别上的泛型转化为方法级别的泛型
 * 	<li>本服务更像是一个Manager或者是一个Dispatcher，能够有效的管理起所有的DAOImpl Bean
 * </ol>
 * @author justin.jiang
 *
 */
public interface GeneralCRUDService {
	
	/**
	 * <p>
	 * Get the entity with the specified type and id from the datastore.
	 * 
	 * <p>
	 * If none is found, return null.
	 */
	public <T> T find(Class<T> type, Serializable id);

	/**
	 * Get all entities of the specified type from the datastore that have one
	 * of these ids. An array of entities is returned that matches the same
	 * order of the ids listed in the call. For each entity that is not found in
	 * the datastore, a null will be inserted in its place in the return array.
	 */
	public <T> T[] find(Class<T> type, Serializable... ids);

	/**
	 * <p>
	 * Get a reference to the entity with the specified type and id from the
	 * datastore.
	 * 
	 * <p>
	 * This does not require a call to the datastore and does not populate any
	 * of the entity's values. Values may be fetched lazily at a later time.
	 * This increases performance if a another entity is being saved that should
	 * reference this entity but the values of this entity are not needed.
	 * 
	 * @throws a
	 *             HibernateException if no matching entity is found
	 */
	public <T> T getReference(Class<T> type, Serializable id);

	/**
	 * <p>
	 * Get a reference to the entities of the specified type with the given ids
	 * from the datastore. An array of entities is returned that matches the
	 * same order of the ids listed in the call.
	 * 
	 * <p>
	 * This does not require a call to the datastore and does not populate any
	 * of the entities' values. Values may be fetched lazily at a later time.
	 * This increases performance if a another entity is being saved that should
	 * reference these entities but the values of these entities are not needed.
	 * 
	 * @throws a
	 *             HibernateException if any of the matching entities are not
	 *             found.
	 */
	public <T> T[] getReferences(Class<T> type, Serializable... ids);

	/**
	 * <p>
	 * If the id of the entity is null or zero, add it to the datastore and
	 * assign it an id; otherwise, update the corresponding entity in the
	 * datastore with the properties of this entity. In either case the entity
	 * passed to this method will be attached to the session.
	 * 
	 * <p>
	 * If an entity to update is already attached to the session, this method
	 * will have no effect. If an entity to update has the same id as another
	 * instance already attached to the session, an error will be thrown.
	 * 
	 * @return <code>true</code> if create; <code>false</code> if update.
	 */
	public boolean save(Object entity);

	/**
	 * <p>
	 * For each entity, if the id of the entity is null or zero, add it to the
	 * datastore and assign it an id; otherwise, update the corresponding entity
	 * in the datastore with the properties of this entity. In either case the
	 * entity passed to this method will be attached to the session.
	 * 
	 * <p>
	 * If an entity to update is already attached to the session, this method
	 * will have no effect. If an entity to update has the same id as another
	 * instance already attached to the session, an error will be thrown.
	 */
	public boolean[] save(Object... entities);

	/**
	 * Remove the specified entity from the datastore.
	 * 
	 * @return <code>true</code> if the entity is found in the datastore and
	 *         removed, <code>false</code> if it is not found.
	 */
	public boolean remove(Object entity);

	/**
	 * Remove all of the specified entities from the datastore.
	 */
	public void remove(Object... entities);

	/**
	 * Remove the entity with the specified type and id from the datastore.
	 * 
	 * @return <code>true</code> if the entity is found in the datastore and
	 *         removed, <code>false</code> if it is not found.
	 */
	public boolean removeById(Class<?> type, Serializable id);

	/**
	 * Remove all the entities of the given type from the datastore that have
	 * one of these ids.
	 */
	public void removeByIds(Class<?> type, Serializable... ids);

	/**
	 * Get a list of all the objects of the specified type.
	 */
	public <T> List<T> findAll(Class<T> type);

	/**
	 * Search for objects given the search parameters in the specified
	 * <code>ISearch</code> object.
	 */
	public List<?> search(ISearch search);

	/**
	 * Search for a single result using the given parameters.
	 */
	public Object searchUnique(ISearch search);

	/**
	 * Returns the total number of results that would be returned using the
	 * given <code>ISearch</code> if there were no paging or maxResults limits.
	 */
	public int count(ISearch search);

	/**
	 * Returns a <code>SearchResult</code> object that includes both the list of
	 * results like <code>search()</code> and the total length like
	 * <code>count()</code>.
	 */
	public SearchResult<?> searchAndCount(ISearch search);

	/**
	 * Returns <code>true</code> if the object is connected to the current
	 * Hibernate session.
	 */
	public boolean isAttached(Object entity);

	/**
	 * Refresh the content of the given entity from the current datastore state.
	 */
	public void refresh(Object... entities);

	/**
	 * Flushes changes in the Hibernate session to the datastore.
	 */
	public void flush();
}
