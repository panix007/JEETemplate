package com.ntahr.common.properties;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PropertiesUtil {

	static private PropertiesUtil propertiesUtil = new PropertiesUtil();

	ApplicationProperties appProperties;

	DatabaseProperties dbProperties;

	JmsProperties jmsProperties;

	private PropertiesUtil() {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring/app-context.xml");
		appProperties = context.getBean(ApplicationProperties.class);
		dbProperties = context.getBean(DatabaseProperties.class);
		jmsProperties = context.getBean(JmsProperties.class);
	}

	public String getProperty(String propName){
		return appProperties.getProperty(propName);
	}
	
	public static PropertiesUtil getInstance() {
		return propertiesUtil;
	}

	public ApplicationProperties getAppProperties() {
		return appProperties;
	}

	public void setAppProperties(ApplicationProperties appProperties) {
		this.appProperties = appProperties;
	}

	public DatabaseProperties getDbProperties() {
		return dbProperties;
	}

	public void setDbProperties(DatabaseProperties dbProperties) {
		this.dbProperties = dbProperties;
	}

	public JmsProperties getJmsProperties() {
		return jmsProperties;
	}

	public void setJmsProperties(JmsProperties jmsProperties) {
		this.jmsProperties = jmsProperties;
	}

}
