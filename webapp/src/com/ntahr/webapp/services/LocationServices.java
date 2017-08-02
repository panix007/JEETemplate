package com.ntahr.webapp.services;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import com.ntahr.common.dataaccess.genericdao.DaoBase;
import com.ntahr.common.dataaccess.genericdao.IDaoBase;
import com.ntahr.common.dataaccess.objects.Device;
import com.ntahr.common.dataaccess.objects.Location;
import com.ntahr.common.util.DateUtil;

public class LocationServices {

	public void createLocation(Location location) {
		IDaoBase<Location> daoBase = new DaoBase<Location>(Location.class);
		daoBase.getEntityManager().getTransaction().begin();
		if (location.getCapturedDate() == null){
			location.setCapturedDate(Calendar.getInstance());
		}
		if (location.getDevice() != null && location.getDevice().getDeviceName() != null){
			DeviceServices deviceServices = new DeviceServices();
			List<Device> devices = deviceServices.getDeviceByName(location.getDevice().getDeviceName());
			if (devices != null && !devices.isEmpty()){
				location.setDevice(devices.get(0));
			}
		}
		daoBase.save(location);
		daoBase.getEntityManager().getTransaction().commit();
	}

	public List<Location> getAllLocations() {
		IDaoBase<Location> daoBase = new DaoBase<Location>(Location.class);
		return daoBase.loadAll();
	}

	public List<Location> getAllLocationsByDevice(String deviceName) {

		DeviceServices deviceServices = new DeviceServices();
		List<Device> devices = deviceServices.getDeviceByName(deviceName);

		IDaoBase<Location> daoBase = new DaoBase<Location>(Location.class);
		EntityManager em = daoBase.getEntityManager();
		List<Location> results = new ArrayList<>();

		for (Device device: devices){
			TypedQuery<Location> query = em.createQuery(
					"SELECT location FROM Location AS location WHERE location.device = :device order by location.capturedDate asc", Location.class
					).setParameter("device", device);
			results.addAll(query.getResultList());
		}

		return results;
	}
	
	public List<Location> getAllLocationsByDeviceAndDate(String deviceName, String startDate, String endDate, String datePattern) throws ParseException {

		DeviceServices deviceServices = new DeviceServices();
		List<Device> devices = deviceServices.getDeviceByName(deviceName);

		IDaoBase<Location> daoBase = new DaoBase<Location>(Location.class);
		EntityManager em = daoBase.getEntityManager();
		List<Location> results = new ArrayList<>();

		for (Device device: devices){
			TypedQuery<Location> query = em.createQuery(
					"SELECT location FROM Location AS location WHERE location.device = :device "
					+ "AND location.capturedDate >= :startDate AND location.endDate <= :endDate "
					+ "order by location.capturedDate asc", Location.class
					).setParameter("device", device)
					.setParameter("startDate", DateUtil.getCalendar(startDate, datePattern), TemporalType.TIMESTAMP)
					.setParameter("endDate", DateUtil.getCalendar(endDate, datePattern), TemporalType.TIMESTAMP);
			results.addAll(query.getResultList());
		}

		return results;
	}	

}
