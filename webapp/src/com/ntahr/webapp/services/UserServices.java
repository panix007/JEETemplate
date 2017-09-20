package com.ntahr.webapp.services;

import com.ntahr.common.dataaccess.genericdao.DaoBase;
import com.ntahr.common.dataaccess.genericdao.IDaoBase;
import com.ntahr.common.dataaccess.objects.User;

public class UserServices extends BaseService<User> {

	@Override
	public IDaoBase<User> getDaoBase() {
		return new DaoBase<User>(User.class);
	}
}
