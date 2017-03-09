package com.ntahr.webapp.services;

import java.util.List;

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

}
