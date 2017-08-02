package com.ntahr.webapp.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.ntahr.common.dataaccess.genericdao.DaoBase;
import com.ntahr.common.dataaccess.genericdao.IDaoBase;
import com.ntahr.common.dataaccess.objects.Device;

public class DeviceServices {
	
	public void createDevice(Device device){
		IDaoBase<Device> daoBase = new DaoBase<Device>(Device.class);
		daoBase.getEntityManager().getTransaction().begin();
		daoBase.save(device);
		daoBase.getEntityManager().getTransaction().commit();
	}

	public List<Device> getAllDevices() {
		IDaoBase<Device> daoBase = new DaoBase<Device>(Device.class);
		return daoBase.loadAll();
	}
	
	public List<Device> getDeviceByName(String deviceName){
		IDaoBase<Device> daoBase = new DaoBase<Device>(Device.class);
		
		EntityManager em = daoBase.getEntityManager();
		TypedQuery<Device> query = em.createQuery(
				"SELECT device FROM Device AS device WHERE device.deviceName = :deviceName", Device.class
				).setParameter("deviceName", deviceName);
		
		return query.getResultList();
	}

}
