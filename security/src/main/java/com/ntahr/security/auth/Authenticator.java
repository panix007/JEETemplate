package com.ntahr.security.auth;

import java.util.Set;

public interface Authenticator {

	public enum AUTH_RESULTS {
		SUCCESS, AUTHENTICATED, NOT_AUTHENTICATED, AUTHORIZED, NOT_AUTHORIZED, NOT_SUPPORTED, REQUEST_TOKEN, TOKEN_VALID, TOKEN_EXPIRED, TOKEN_INVALID
	};

	public AUTH_RESULTS authenticate(String authorizationHeader);
	
	public Credentials getCredentials(String authorizationHeader);

	public AUTH_RESULTS authorize(Credentials credentials, Set<String> targetRoles);

	public Token createToken(Credentials credentials);

	public AUTH_RESULTS validateToken(Token token);

}
