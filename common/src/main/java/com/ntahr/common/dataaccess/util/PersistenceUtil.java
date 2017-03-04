package com.ntahr.common.dataaccess.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;

import com.ntahr.common.properties.ApplicationProperties;
import com.ntahr.common.properties.PropertiesConstants;
import com.ntahr.common.properties.PropertiesUtil;

public class PersistenceUtil {
	private static final Logger log = Logger.getLogger(PersistenceUtil.class);
	static boolean driverLoaded = false;

	public static EntityManager getEntityManager() {
		log.info("Enter: getEntityManager");
		if (!driverLoaded) {
			driverLoaded = loadDriver(); // load only once.
											// Introduced due to an issue with
											// Tomcat 7 requiring loading of the
											// DriverManager
		}

		EntityManagerFactory factory = null;
		EntityManager entityManager = null;
		try {
			factory = Persistence.createEntityManagerFactory("PersistenceUnit");
			entityManager = factory.createEntityManager();
			log.info("Exit: getEntityManager");
			return entityManager;
		} finally {
			if (factory != null) {
				// factory.close();
			}
		}
	}

	public static boolean loadDriver() {
		try {
			ApplicationProperties properties = PropertiesUtil.getInstance().getAppProperties();
			Class.forName(properties.getProperty(PropertiesConstants.DB_DRIVERCLASS));
			return true;
		} catch (Throwable e) {
			log.error("Exception loading Driver class", e);
			return false;
		}
	}
}
