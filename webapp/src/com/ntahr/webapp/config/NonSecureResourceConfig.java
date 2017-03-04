package com.ntahr.webapp.config;

import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.server.ResourceConfig;

import com.ntahr.common.message.handler.GSONMessageHandler;

public class NonSecureResourceConfig extends ResourceConfig {

	public NonSecureResourceConfig() {
		packages("com.ntahr.webapp");
		register(LoggingFilter.class);
		register(GSONMessageHandler.class);
	}
}
