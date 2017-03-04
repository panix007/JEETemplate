package com.ntahr.common.dataaccess.genericdao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.criterion.DetachedCriteria;

/**
 * Base interface for CRUD operations and common queries
 */
public interface IDaoBase<T> {

	public List<T> loadAll();

	public void save(T domain);

	public void update(T domain);

	public void delete(T domain);

	public T get(Serializable id);

	public List<T> getListByCriteria(DetachedCriteria detachedCriteria);

	public List<T> getListByCriteria(DetachedCriteria detachedCriteria, int offset, int size);

	public void close();

	public void setEntityManager(EntityManager entityManager);

	public EntityManager getEntityManager();
}