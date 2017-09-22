package com.ntahr.webapp.services;

import com.ntahr.common.dataaccess.genericdao.DaoBase;
import com.ntahr.common.dataaccess.objects.DeviceModel;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class DeviceMasterServices extends BaseService<DeviceModel> {

    public DeviceMasterServices() {
        super(new DaoBase<>(DeviceModel.class));
    }

    public List<DeviceModel> getDeviceMasterByName(String deviceMasterName) {
        EntityManager em = getDaoBase().getEntityManager();
        TypedQuery<DeviceModel> query = em.createQuery(
                "SELECT deviceModel FROM DeviceModel AS deviceModel WHERE deviceModel.modelName = :modelName", DeviceModel.class
        ).setParameter("modelName", deviceMasterName);

        return query.getResultList();
    }

}
