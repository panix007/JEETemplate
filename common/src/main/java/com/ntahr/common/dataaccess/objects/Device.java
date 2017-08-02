package com.ntahr.common.dataaccess.objects;

import javax.persistence.*;

@Entity
@Table(name = "device") // @Table is optional
public class Device {

	@Id // @Id indicates that this it a unique primary key
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	private Long deviceId;

	@Column(length = 100)
	private String deviceName;

	@ManyToOne
	@JoinColumn(name = "modelId")
	private DeviceModel deviceModel;

	@Column(length = 100)
	private String deviceType;

	@Column(length = 1)
	private Boolean deviceEnabled;

	public Long getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(Long deviceId) {
		this.deviceId = deviceId;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public DeviceModel getDeviceModel() {
		return deviceModel;
	}

	public void setDeviceModel(DeviceModel deviceModel) {
		this.deviceModel = deviceModel;
	}

	public Boolean getDeviceEnabled() {
		return deviceEnabled;
	}

	public void setDeviceEnabled(Boolean deviceEnabled) {
		this.deviceEnabled = deviceEnabled;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

}
