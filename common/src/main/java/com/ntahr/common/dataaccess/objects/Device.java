package com.ntahr.common.dataaccess.objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "device") // @Table is optional
public class Device {

	@Id // @Id indicates that this it a unique primary key
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	Long id;

	@Column(length = 100)
	String deviceId;

	@Column(length = 100)
	String deviceName;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}


}
