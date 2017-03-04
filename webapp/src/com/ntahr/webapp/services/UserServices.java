package com.ntahr.webapp.services;

import java.util.List;

import com.ntahr.common.dataaccess.genericdao.DaoBase;
import com.ntahr.common.dataaccess.genericdao.IDaoBase;
import com.ntahr.common.dataaccess.objects.User;

public class UserServices {

	public List<User> getAllUsers(){
		IDaoBase<User> daoBase = new DaoBase<User>(User.class);
		return daoBase.loadAll();
	}

	public void createUser(User user) {
		IDaoBase<User> daoBase = new DaoBase<User>(User.class);
		daoBase.getEntityManager().getTransaction().begin();
		daoBase.save(user);
		daoBase.getEntityManager().getTransaction().commit();
	}

	public void updateUser(User user) {
		IDaoBase<User> daoBase = new DaoBase<User>(User.class);
		daoBase.getEntityManager().getTransaction().begin();
		daoBase.update(user);
		daoBase.getEntityManager().getTransaction().commit();
	}
	
	public void deleteUser(User user){
		IDaoBase<User> daoBase = new DaoBase<User>(User.class);
		daoBase.getEntityManager().getTransaction().begin();
		daoBase.delete(user);
		daoBase.getEntityManager().getTransaction().commit();		
	}
	
	
}
