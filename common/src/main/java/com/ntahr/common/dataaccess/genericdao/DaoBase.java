package com.ntahr.common.dataaccess.genericdao;

import com.ntahr.common.dataaccess.objects.PaginationDetails;
import com.ntahr.common.dataaccess.util.PersistenceUtil;
import org.hibernate.criterion.DetachedCriteria;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.List;

public class DaoBase<T> implements IDaoBase<T> {


    private EntityManager entityManager;

	private Class<T> classType;

	public DaoBase(Class<T> classType) {
		this.classType = classType;
		entityManager = PersistenceUtil.getEntityManager();
        // entityManager.setFlushMode(FlushModeType.COMMIT);
    }

    DaoBase(Class<T> classType, EntityManager entityManager) {
        this.classType = classType;
        setEntityManager(entityManager);
    }

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
    public List<T> loadOffset(int offset, int size) {
        TypedQuery<T> query = entityManager.createQuery("select e from " + this.classType.getName() + " e", this.classType)
                .setFirstResult((offset - 1) * size)
                .setMaxResults(size);
        return query.getResultList();
    }

    @Override
    public PaginationDetails getPaginationDetails(int size) {
        Query queryTotal = entityManager.createQuery
                ("select count(e.id) from " + this.classType.getName() + " e");
        long countResult = (long) queryTotal.getSingleResult();
        double totalPages = Math.ceil((double) countResult / (double) size);
        return new PaginationDetails(countResult, (int) totalPages, size);
    }

	@Override
	public void save(T domain) {
		entityManager.persist(domain);
		entityManager.flush();
	}

	@Override
	public void delete(T domain) {
		entityManager.remove(domain);
		entityManager.flush();
	}

	@Override
	public void update(T domain) {
		entityManager.merge(domain);
		entityManager.flush();
	}

	@Override
	public T get(Serializable id) {
        return entityManager.find(this.classType, id);
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