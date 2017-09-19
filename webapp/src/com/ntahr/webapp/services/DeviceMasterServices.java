package com.ntahr.webapp.services;

import com.ntahr.common.dataaccess.genericdao.DaoBase;
import com.ntahr.common.dataaccess.genericdao.IDaoBase;
import com.ntahr.common.dataaccess.objects.DeviceModel;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class DeviceMasterServices {

    public List<DeviceModel> getAllDeviceModels() {
        IDaoBase<DeviceModel> daoBase = new DaoBase<DeviceModel>(DeviceModel.class);
        return daoBase.loadAll();
    }

    public List<DeviceModel> getDeviceMasterByName(String deviceMasterName) {
        IDaoBase<DeviceModel> daoBase = new DaoBase<DeviceModel>(DeviceModel.class);

        EntityManager em = daoBase.getEntityManager();
        TypedQuery<DeviceModel> query = em.createQuery(
                "SELECT deviceModel FROM DeviceModel AS deviceModel WHERE deviceModel.modelName = :modelName", DeviceModel.class
        ).setParameter("modelName", deviceMasterName);

        return query.getResultList();
    }

    public void createDeviceModel(DeviceModel deviceModel) {
        IDaoBase<DeviceModel> daoBase = new DaoBase<DeviceModel>(DeviceModel.class);
        daoBase.getEntityManager().getTransaction().begin();
        daoBase.save(deviceModel);
        daoBase.getEntityManager().getTransaction().commit();
    }

    public void deleteDeviceModel(DeviceModel deviceModel) {
        IDaoBase<DeviceModel> daoBase = new DaoBase<DeviceModel>(DeviceModel.class);
        daoBase.getEntityManager().getTransaction().begin();
        daoBase.delete(deviceModel);
        daoBase.getEntityManager().getTransaction().commit();
    }

    public void updateDeviceModel(DeviceModel deviceModel) {
        IDaoBase<DeviceModel> daoBase = new DaoBase<DeviceModel>(DeviceModel.class);
        daoBase.getEntityManager().getTransaction().begin();
        daoBase.update(deviceModel);
        daoBase.getEntityManager().getTransaction().commit();

    }
}
