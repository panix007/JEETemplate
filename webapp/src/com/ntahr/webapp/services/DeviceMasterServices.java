package com.ntahr.webapp.services;

import com.ntahr.common.dataaccess.genericdao.DaoBase;
import com.ntahr.common.dataaccess.genericdao.IDaoBase;
import com.ntahr.common.dataaccess.objects.DeviceModel;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class DeviceMasterServices extends BaseService<DeviceModel> {

    public List<DeviceModel> getDeviceMasterByName(String deviceMasterName) {
        IDaoBase<DeviceModel> daoBase = new DaoBase<DeviceModel>(DeviceModel.class);

        EntityManager em = daoBase.getEntityManager();
        TypedQuery<DeviceModel> query = em.createQuery(
                "SELECT deviceModel FROM DeviceModel AS deviceModel WHERE deviceModel.modelName = :modelName", DeviceModel.class
        ).setParameter("modelName", deviceMasterName);

        return query.getResultList();
    }

    @Override
    public IDaoBase<DeviceModel> getDaoBase() {
        return new DaoBase<DeviceModel>(DeviceModel.class);
    }
}
