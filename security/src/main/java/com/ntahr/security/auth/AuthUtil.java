package com.ntahr.security.auth;

import java.util.StringTokenizer;

import org.glassfish.jersey.internal.util.Base64;

public class AuthUtil {

	public static final String AUTHENTICATION_SCHEME_BASIC = "Basic";
	public static final String AUTHENTICATION_SCHEME_BEARER = "Bearer";
	
	public static Credentials getCredentialsFromBasicHeader(String authorizationHeader) {
		final String encodedUserPassword = authorizationHeader.replaceFirst(AUTHENTICATION_SCHEME_BASIC + " ", "");
		String usernameAndPassword = new String(Base64.decode(encodedUserPassword.getBytes()));
		final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");
		final String username = tokenizer.nextToken();
		final String password = tokenizer.nextToken();
		Credentials credentials = new Credentials();
		credentials.setUsername(username);
		credentials.setPassword(password);
		return credentials;
	}
}
