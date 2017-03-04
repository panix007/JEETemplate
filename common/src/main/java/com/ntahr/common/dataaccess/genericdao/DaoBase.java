package com.ntahr.common.dataaccess.genericdao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.hibernate.criterion.DetachedCriteria;

import com.ntahr.common.dataaccess.util.PersistenceUtil;

public class DaoBase<T> implements IDaoBase<T> {

	protected EntityManager entityManager = null;

	private Class<T> classType;

	public DaoBase(Class<T> classType) {
		this.classType = classType;
		entityManager = PersistenceUtil.getEntityManager();
	}
	
	DaoBase(Class<T> classType, EntityManager entityManager){
		this.classType = classType;
		this.entityManager = entityManager;
	}

	@PersistenceContext
	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}

	@Override
	public List<T> loadAll() {
		TypedQuery<T> query = entityManager.createQuery("select e from " + this.classType.getName() + " e", this.classType);
		return query.getResultList();
	}

	@Override
	public void save(T domain) {
		entityManager.persist(domain);
		entityManager.flush();
	}

	@Override
	public void delete(T domain) {
		entityManager.remove(domain);
	}

	@Override
	public void update(T domain) {
		entityManager.merge(domain);
	}

	@Override
	public T get(Serializable id) {
		return (T) entityManager.find(this.classType, id);
	}

	public List<T> getListByCriteria(DetachedCriteria detachedCriteria) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<T> getListByCriteria(DetachedCriteria detachedCriteria, int offset, int size) {
		// TODO Auto-generated method stub
		return null;
	}
	
	protected void finalize() throws Throwable{
		close();
	}
	
	@Override
	public void close(){
		if (entityManager != null){
			entityManager.close();
		}
	}

}