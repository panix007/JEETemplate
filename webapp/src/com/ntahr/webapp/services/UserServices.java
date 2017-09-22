package com.ntahr.webapp.services;

import com.ntahr.common.dataaccess.genericdao.DaoBase;
import com.ntahr.common.dataaccess.objects.User;

public class UserServices extends BaseService<User> {

	public UserServices() {
		super(new DaoBase<>(User.class));
	}
}
