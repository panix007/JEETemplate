package com.ntahr.common.dataaccess.objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "customer") // @Table is optional

public class Customer {

	@Id // @Id indicates that this it a unique primary key
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	Long id;

	@Column(length = 100)
	String name;

	@Column(length = 100, unique = true)
	String email;

	@Column(length = 32)
	String phone;

	@Column(length = 100)
	String address;

	public Customer(){
		
	}
	
	public Customer(String name, String email, String phone, String address) {
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.address = address;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String toString() {
		return id + ", " + name + ", " + email + ", " + phone + ", " + address;
	}
}
