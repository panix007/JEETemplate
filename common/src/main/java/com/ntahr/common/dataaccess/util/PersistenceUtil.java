package com.ntahr.common.dataaccess.util;

import com.ntahr.common.properties.ApplicationProperties;
import com.ntahr.common.properties.PropertiesConstants;
import com.ntahr.common.properties.PropertiesUtil;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;

public class PersistenceUtil {
	private static final Logger log = Logger.getLogger(PersistenceUtil.class);
    private static boolean driverLoaded = false;

    @PersistenceUnit(unitName = "PersistenceUnit")
    private static EntityManagerFactory factory;

	public static EntityManager getEntityManager() {
		log.info("Enter: getEntityManager");
		if (!driverLoaded) {
            synchronized (PersistenceUtil.class) {
                if (!driverLoaded) {
                    driverLoaded = loadDriver(); // load only once.
                }
                // Introduced due to an issue with
                // Tomcat 7 requiring loading of the
                // DriverManager
            }
        }
        if (factory == null) {
            synchronized (PersistenceUtil.class) {
                if (factory == null) {
                    factory = Persistence.createEntityManagerFactory("PersistenceUnit");
                }
            }
        }
        log.info("Exit: getEntityManager");
        return factory.createEntityManager();
    }

    private static boolean loadDriver() {
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
