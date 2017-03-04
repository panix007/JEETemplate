package com.ntahr.webapp.services;

import java.util.List;

import com.ntahr.common.dataaccess.genericdao.DaoBase;
import com.ntahr.common.dataaccess.genericdao.IDaoBase;
import com.ntahr.common.dataaccess.objects.Customer;


public class CustomerServices {

	public List<Customer> getAllCustomers(){
		IDaoBase<Customer> daoBase = new DaoBase<Customer>(Customer.class);
		return daoBase.loadAll();
	}

	public void createCustomer(Customer customer) {
		IDaoBase<Customer> daoBase = new DaoBase<Customer>(Customer.class);
		daoBase.getEntityManager().getTransaction().begin();
		daoBase.save(customer);
		daoBase.getEntityManager().getTransaction().commit();
	}
}
