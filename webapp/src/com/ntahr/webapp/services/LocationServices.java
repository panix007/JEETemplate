package com.ntahr.webapp.services;

import com.ntahr.common.dataaccess.mongo.dao.MongoDAO;
import com.ntahr.common.dataaccess.mongo.exception.MongoException;
import com.ntahr.common.dataaccess.mongo.objects.LocationPoint;
import com.ntahr.common.properties.PropertiesUtil;

import java.util.List;
import java.util.Set;

public class LocationServices {

	static String MONGO_HOST = PropertiesUtil.getInstance().getProperty("mongo.host");
	static Integer MONGO_PORT = Integer.parseInt(PropertiesUtil.getInstance().getProperty("mongo.port"));
	static String MONGO_COLLECTION = PropertiesUtil.getInstance().getProperty("mongo.db.name");

	MongoDAO<LocationPoint> mongoDAO = new MongoDAO<>(MONGO_HOST, MONGO_PORT, MONGO_COLLECTION);

	public void createLocation(LocationPoint location, String deviceId) throws MongoException {
		if (location.getRecordedTime() == null) {
			location.setRecordedTime(System.currentTimeMillis());
		}
		mongoDAO.insert(location, deviceId);
	}

	public List<LocationPoint> getAllLocations() {
		return null;
	}

	public List<LocationPoint> getAllLocationsByDevice(String deviceId) throws MongoException {
		return mongoDAO.findAll(LocationPoint.class, deviceId);
	}

	public List<LocationPoint> getAllLocationsByDeviceAndDate(String deviceId, String startDate, String endDate, String datePattern) {
		return null;
	}

	public List<LocationPoint> getLocationsByDevice(String deviceId) throws MongoException {
		return mongoDAO.findAll(LocationPoint.class, deviceId);
	}

	public List<LocationPoint> getLocationsByDevice(String deviceId, Long startTime, Long endTime) throws MongoException {
		return mongoDAO.filterOnDateRange(LocationPoint.class, deviceId, "recordedTime", startTime, endTime);
	}

	public Set<String> getDevices() throws MongoException {
		return mongoDAO.getCollections();
	}
}
