package com.ntahr.common.dataaccess.objects;

import javax.persistence.*;
import java.util.Calendar;

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

    public Location(Long id, Double lattitude, Double longitude, Calendar capturedDate, Device device) {
        this.id = id;
        this.lattitude = lattitude;
        this.longitude = longitude;
        this.capturedDate = capturedDate;
        this.device = device;
    }

    public Location() {
        // Empty constructor
    }

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

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", lattitude=" + lattitude +
                ", longitude=" + longitude +
                ", capturedDate=" + capturedDate +
                ", device=" + device +
                '}';
    }
}
