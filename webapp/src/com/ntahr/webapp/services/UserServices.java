package com.ntahr.webapp.services;

import com.ntahr.common.dataaccess.genericdao.DaoBase;
import com.ntahr.common.dataaccess.objects.Device;
import com.ntahr.common.dataaccess.objects.User;

import java.util.Arrays;
import java.util.List;

public class UserServices extends BaseService<User> {

	public UserServices() {
		super(new DaoBase<>(User.class));
	}

	public User addDeviceToUser(String userId, String deviceId) {
		User user = getDaoBase().get(Long.parseLong(userId));
		DeviceServices deviceServices = new DeviceServices();
		Device device = deviceServices.getDaoBase().get(Long.parseLong(deviceId));
		List<Device> userDevices = user.getDevices();
		if (userDevices != null) {
			userDevices.add(device);
		} else {
			user.setDevices(Arrays.asList(device));
		}
		update(user);
		return user;
	}

	public User deleteDeviceFromUser(String userId, String deviceId) {
		User user = getDaoBase().get(Long.parseLong(userId));
		if (user.getDevices() != null) {
			Device matchedDevice = null;
			Long deviceIdL = Long.parseLong(deviceId);
			for (Device device : user.getDevices()) {
				if (deviceIdL.equals(device.getDeviceId())) {
					matchedDevice = device;
					break;
				}
			}
			if (matchedDevice != null) {
				user.getDevices().remove(matchedDevice);
			}
		}
		update(user);
		return user;
	}
}
