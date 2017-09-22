package com.ntahr.webapp.services;

import com.ntahr.common.dataaccess.genericdao.DaoBase;
import com.ntahr.common.dataaccess.objects.Device;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class DeviceServices extends BaseService<Device> {

	public DeviceServices() {
		super(new DaoBase<>(Device.class));
	}

	public List<Device> getDeviceByName(String deviceName){
		EntityManager em = getDaoBase().getEntityManager();
		TypedQuery<Device> query = em.createQuery(
				"SELECT device FROM Device AS device WHERE device.deviceName = :deviceName", Device.class
				).setParameter("deviceName", deviceName);
		
		return query.getResultList();
	}

	public List<Device> getDeviceByModel(String modelId) {
		EntityManager em = getDaoBase().getEntityManager();
		TypedQuery<Device> query = em.createQuery(
				"SELECT device FROM Device AS device WHERE device.deviceModel.modelId = :deviceModel", Device.class
		).setParameter("deviceModel", Long.parseLong(modelId));

		return query.getResultList();
	}

}
