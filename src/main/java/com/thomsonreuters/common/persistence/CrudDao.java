package com.thomsonreuters.common.persistence;

import java.util.List;

/**
 * CrudDao
 * 
 * @author
 * @version
 * @param <T>
 */
public interface CrudDao<T> extends BaseDao {

	/**
	 * getSingleRow
	 * 
	 * @param name
	 * @return
	 */
	public T get(String name);

	/**
	 * getSingleRow
	 * 
	 * @param entity
	 * @return
	 */
	public T get(T entity);

	/**
	 * findAll
	 * 
	 * @return
	 */
	public List<T> findAll();

	/**
	 * insert
	 * 
	 * @param entity
	 * @return
	 */
	public int insert(T entity);

	/**
	 * update
	 * 
	 * @param entity
	 * @return
	 */
	public int update(T entity);

	/**
	 * delete
	 * 
	 * @param id
	 * @see public int delete(T entity)
	 * @return
	 */
	@Deprecated
	public int delete(String id);

	/**
	 * delete
	 * 
	 * @param entity
	 * @return
	 */
	public int delete(T entity);

}