package com.ntahr.common.dataaccess.objects;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "user") // @Table is optional
public class User {

	@Id // @Id indicates that this it a unique primary key
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	Long id;
	
	@Column(length = 100)
	String firstName;
	
	@Column(length = 100)
	String lastName;
	
	@Column(length = 100, unique = true)
	String emailAddress;
	
	@Column(length = 100)
	String phoneNumber;
	
	@Column(length = 100)
	String password;
	
	@OneToMany
	List<Device> devices;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<Device> getDevices() {
		return devices;
	}
	public void setDevices(List<Device> devices) {
		this.devices = devices;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
