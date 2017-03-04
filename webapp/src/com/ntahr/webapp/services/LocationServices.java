package com.ntahr.webapp.services;

import java.util.List;

import com.ntahr.common.dataaccess.genericdao.DaoBase;
import com.ntahr.common.dataaccess.genericdao.IDaoBase;
import com.ntahr.common.dataaccess.objects.Location;

public class LocationServices {

	public void createLocation(Location location) {
		IDaoBase<Location> daoBase = new DaoBase<Location>(Location.class);
		daoBase.getEntityManager().getTransaction().begin();
		daoBase.save(location);
		daoBase.getEntityManager().getTransaction().commit();		
	}

	public List<Location> getAllLocations() {
		IDaoBase<Location> daoBase = new DaoBase<Location>(Location.class);
		return daoBase.loadAll();
	}

}
