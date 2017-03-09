package com.ntahr.common.dataaccess.objects;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "location") // @Table is optional
public class Location {

	@Id // @Id indicates that this it a unique primary key
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	Long id;
	
	@Column(length = 100)
	Double lattitude;
	
	@Column(length = 100)
	Double longitude;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "capturedate", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	Calendar capturedDate;	
	
	
	@OneToOne(fetch = FetchType.EAGER)
	Device device;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getLattitude() {
		return lattitude;
	}

	public void setLattitude(Double lattitude) {
		this.lattitude = lattitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Calendar getCapturedDate() {
		return capturedDate;
	}

	public void setCapturedDate(Calendar capturedDate) {
		this.capturedDate = capturedDate;
	}

	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}
	
}
