package com.thomsonreuters.common.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.thomsonreuters.common.persistence.CrudDao;
import com.thomsonreuters.common.persistence.DataEntity;

/**
 * CrudService
 * 
 * @author
 * @version
 */
@Transactional(readOnly = true)
public abstract class CrudService<D extends CrudDao<T>, T extends DataEntity<T>>
		extends BaseService {

	/**
	 * dao
	 */
	@Autowired
	protected D dao;

	/**
	 * getSingleRow
	 * 
	 * @param id
	 * @return
	 */
	public T get(String id) {
		return dao.get(id);
	}

	/**
	 * getSingleRow
	 * 
	 * @param entity
	 * @return
	 */
	public T get(T entity) {
		return dao.get(entity);
	}

	/**
	 * findList
	 * 
	 * @param entity
	 * @return
	 */
	public List<T> findList(T entity) {
		return dao.findList(entity);
	}

	/**
	 * save
	 * 
	 * @param entity
	 */
	@Transactional(readOnly = false)
	public void save(T entity) {
		if (entity.getIsNewRecord()) {
			entity.preInsert();
			dao.insert(entity);
		} else {
			entity.preUpdate();
			dao.update(entity);
		}
	}

	/**
	 * delete
	 * 
	 * @param entity
	 */
	@Transactional(readOnly = false)
	public void delete(T entity) {
		dao.delete(entity);
	}

}
