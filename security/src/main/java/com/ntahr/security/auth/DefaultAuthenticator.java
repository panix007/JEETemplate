package com.ntahr.security.auth;

import java.util.Set;

import com.ntahr.security.auth.token.JWTTokenHelper;

public class DefaultAuthenticator implements Authenticator {

	@Override
	public AUTH_RESULTS authenticate(String authorizationHeader) {
		if (authorizationHeader.contains(AuthUtil.AUTHENTICATION_SCHEME_BASIC)) {
			Credentials credentials = AuthUtil.getCredentialsFromBasicHeader(authorizationHeader);
			AUTH_RESULTS results = authenticate(credentials);
			if (results == AUTH_RESULTS.AUTHENTICATED) {
				return AUTH_RESULTS.REQUEST_TOKEN;
			}
			return results;

		} else if (authorizationHeader.contains(AuthUtil.AUTHENTICATION_SCHEME_BEARER)) {
			String authToken = authorizationHeader.substring("Bearer".length()).trim();
			Token token = new Token();
			token.setToken(authToken);
			return validateToken(token);
		}
		return AUTH_RESULTS.NOT_SUPPORTED;
	}

	private AUTH_RESULTS authenticate(Credentials credentials) {
		// TODO: proper implementation
		if ("user".equalsIgnoreCase(credentials.getUsername())
				&& "password".equalsIgnoreCase(credentials.getPassword())) {
			return AUTH_RESULTS.AUTHENTICATED;
		}
		return AUTH_RESULTS.NOT_AUTHENTICATED;
	}

	@Override
	public AUTH_RESULTS authorize(Credentials credentials, Set<String> targetRoles) {
		// TODO: proper implementation
		String userRole = "admin"; // getUserRole();
		if (targetRoles == null || targetRoles.isEmpty() || targetRoles.contains(userRole)) {
			return AUTH_RESULTS.AUTHORIZED;
		} else {
			return AUTH_RESULTS.NOT_AUTHORIZED;
		}
	}

	@Override
	public Token createToken(Credentials credentials) {
		Token token = JWTTokenHelper.generateToken(credentials.getUsername());
		return token;
	}

	@Override
	public AUTH_RESULTS validateToken(Token token) {
		if (JWTTokenHelper.validateToken(token)) {
			return AUTH_RESULTS.TOKEN_VALID;
		}

		return AUTH_RESULTS.TOKEN_INVALID;
	}

	@Override
	public Credentials getCredentials(String authorizationHeader) {
		if (authorizationHeader.contains(AuthUtil.AUTHENTICATION_SCHEME_BASIC)) {
			return AuthUtil.getCredentialsFromBasicHeader(authorizationHeader);
		}
		return null;
	}

}
