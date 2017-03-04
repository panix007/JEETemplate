package com.ntahr.security.auth;

import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.server.ResourceConfig;

import com.ntahr.common.message.handler.GSONMessageHandler;

public class SecureResourceConfig extends ResourceConfig {
	public SecureResourceConfig() {
		packages("com.ntahr");
		register(LoggingFilter.class);
		register(GSONMessageHandler.class);
		register(AuthenticationFilter.class);
	}
}
