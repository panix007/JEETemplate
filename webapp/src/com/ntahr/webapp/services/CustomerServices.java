package com.ntahr.webapp.services;

import com.ntahr.common.dataaccess.genericdao.DaoBase;
import com.ntahr.common.dataaccess.objects.Customer;


public class CustomerServices extends BaseService<Customer> {

	public CustomerServices() {
		super(() -> new DaoBase<>(Customer.class));
	}

}
