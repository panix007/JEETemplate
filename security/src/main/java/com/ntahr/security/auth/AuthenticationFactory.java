package com.ntahr.security.auth;

public class AuthenticationFactory {

	public static Authenticator getAuthenticator(){
		
		return new DefaultAuthenticator();
	}
}
