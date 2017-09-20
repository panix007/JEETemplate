package com.ntahr.webapp.services;

import com.ntahr.common.dataaccess.genericdao.IDaoBase;

import java.util.List;

public abstract class BaseService<T> {

    public void create(T t) {
        IDaoBase<T> daoBase = getDaoBase();
        daoBase.getEntityManager().getTransaction().begin();
        daoBase.save(t);
        daoBase.getEntityManager().getTransaction().commit();
    }

    public List<T> retrieve() {
        IDaoBase<T> daoBase = getDaoBase();
        return daoBase.loadAll();
    }

    public void update(T t) {
        IDaoBase<T> daoBase = getDaoBase();
        daoBase.getEntityManager().getTransaction().begin();
        daoBase.update(t);
        daoBase.getEntityManager().getTransaction().commit();
    }

    public void delete(T t) {
        IDaoBase<T> daoBase = getDaoBase();
        daoBase.getEntityManager().getTransaction().begin();
        daoBase.getEntityManager().remove(daoBase.getEntityManager().merge(t));
        daoBase.getEntityManager().getTransaction().commit();
    }

    public abstract IDaoBase<T> getDaoBase();
}
