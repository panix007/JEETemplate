package com.ntahr.webapp.services;

import com.ntahr.common.dataaccess.genericdao.IDaoBase;

import java.util.List;

public class BaseService<T> {

    private IDaoBase<T> daoBase;

    public BaseService(IDaoBase<T> daoBase) {
        this.daoBase = daoBase;
    }

    public IDaoBase<T> getDaoBase() {
        return daoBase;
    }

    public void create(T t) {
        try {
            daoBase.getEntityManager().getTransaction().begin();
            daoBase.save(t);
            daoBase.getEntityManager().getTransaction().commit();
        } catch (Throwable throwable) {
            daoBase.getEntityManager().getTransaction().rollback();
            throw throwable;
        }
    }

    public List<T> retrieve() {
        return daoBase.loadAll();
    }

    public void update(T t) {
        try {
            daoBase.getEntityManager().getTransaction().begin();
            daoBase.update(t);
            daoBase.getEntityManager().getTransaction().commit();
        } catch (Throwable throwable) {
            daoBase.getEntityManager().getTransaction().rollback();
            throw throwable;
        }
    }

    public void delete(T t) {
        try {
            daoBase.getEntityManager().getTransaction().begin();
            daoBase.getEntityManager().remove(daoBase.getEntityManager().merge(t));
            daoBase.getEntityManager().getTransaction().commit();
        } catch (Throwable throwable) {
            daoBase.getEntityManager().getTransaction().rollback();
            throw throwable;
        }
    }

}
