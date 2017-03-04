package com.ntahr.common.properties;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ntahr.common.properties.DatabaseProperties;
import com.ntahr.common.properties.JmsProperties;

import junit.framework.TestCase;

/**
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/app-context.xml")
public class ApplicationPropertiesTest extends TestCase {

	static Logger log = Logger.getLogger(ApplicationPropertiesTest.class.getName());

	@Autowired
	private DatabaseProperties databaseProperties;

	@Autowired
	private JmsProperties jmsProperties;

	@Test
	public void testApplicationProperties() {

		// Using application properties through properties wrappers
		log.info(databaseProperties.toString());
		log.info(jmsProperties.toString());
		assert(true);
	}

}
