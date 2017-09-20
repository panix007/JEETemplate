package com.ntahr.webapp.services;

import com.ntahr.common.dataaccess.genericdao.DaoBase;
import com.ntahr.common.dataaccess.genericdao.IDaoBase;
import com.ntahr.common.dataaccess.objects.Customer;


public class CustomerServices extends BaseService<Customer> {

	@Override
	public IDaoBase<Customer> getDaoBase() {
		return new DaoBase<Customer>(Customer.class);
	}
}
