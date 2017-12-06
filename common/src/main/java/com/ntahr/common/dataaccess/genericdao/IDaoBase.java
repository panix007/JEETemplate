package com.ntahr.common.dataaccess.genericdao;

import com.ntahr.common.dataaccess.objects.PaginationDetails;
import org.hibernate.criterion.DetachedCriteria;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

/**
 * Base interface for CRUD operations and common queries
 */
public interface IDaoBase<T> {

	List<T> loadAll();

	List<T> loadOffset(int offset, int size);

	PaginationDetails getPaginationDetails(int size);

	void save(T domain);

	void update(T domain);

	void delete(T domain);

	T get(Serializable id);

	List<T> getListByCriteria(DetachedCriteria detachedCriteria);

	List<T> getListByCriteria(DetachedCriteria detachedCriteria, int offset, int size);

	void close();

	EntityManager getEntityManager();

	void setEntityManager(EntityManager entityManager);
}