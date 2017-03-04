package com.ntahr.common.dataaccess;

import java.util.List;

import org.apache.log4j.Logger;

import com.ntahr.common.dataaccess.genericdao.DaoBase;
import com.ntahr.common.dataaccess.genericdao.IDaoBase;
import com.ntahr.common.dataaccess.objects.Customer;

public class Application {

	private static final Logger log = Logger.getLogger(Application.class);

	public static void main(String[] args) {
		long mainStartTime = System.currentTimeMillis();
		int errorCount = 0;
		for (int i = 5; i < 10; i++) {
			long startTime = System.currentTimeMillis();
			log.info("Enter: Main" + i + ":");

			try {

				log.info("Time to get Entity Manager: " + (System.currentTimeMillis() - startTime));
				Customer customer = new Customer("Pravin" + i, i + "p@gmail.com", "9008536810", "Kochi");
				persistPerson(customer);
				log.info("Total Time: " + (System.currentTimeMillis() - startTime));
			} catch (Exception e) {
				log.fatal(e.getMessage(), e);
				errorCount++;
			}
			log.info("Exit: Main" + i + ": " + (System.currentTimeMillis() - startTime));
		}

		showCustomers();

		long mainEndTime = System.currentTimeMillis();
		log.info("Exit: Total Main: " + (mainEndTime - mainStartTime) + " With Errors: " + errorCount);
	}

	private static void persistPerson(Customer customer) {
		long mainStartTime = System.currentTimeMillis();
		log.debug("Going to persist: ");
		IDaoBase<Customer> daoBase = new DaoBase<Customer>(Customer.class);
		daoBase.getEntityManager().getTransaction().begin();
		daoBase.save(customer);
		daoBase.getEntityManager().getTransaction().commit();
		daoBase.getEntityManager().close();
		log.debug("persisted: " + customer.toString() + " Time taken: " + (System.currentTimeMillis() - mainStartTime));
	}

	private static void showCustomers() {
		long mainStartTime = System.currentTimeMillis();
		log.debug("Going to persist: ");
		IDaoBase<Customer> daoBase = new DaoBase<Customer>(Customer.class);
		List<Customer> customers = daoBase.loadAll();
		daoBase.getEntityManager().close();
		for (Customer customer : customers) {
			log.debug("loaded: " + customer.toString());
		}
		log.debug("Time taken to load " + (System.currentTimeMillis() - mainStartTime));
	}

}