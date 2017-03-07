package com.ntahr.security.auth;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.ntahr.common.dataaccess.genericdao.DaoBase;
import com.ntahr.common.dataaccess.genericdao.IDaoBase;
import com.ntahr.common.dataaccess.objects.User;
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
		if (authenticateUser(credentials)) {
			return AUTH_RESULTS.AUTHENTICATED;
		}
		return AUTH_RESULTS.NOT_AUTHENTICATED;
	}

	private Boolean authenticateUser(Credentials credentials){
		IDaoBase<User> daoBase = new DaoBase<User>(User.class);
		EntityManager em = daoBase.getEntityManager();
		TypedQuery<User> query = em.createQuery(
				"SELECT user FROM User AS user WHERE user.emailAddress = :email and user.password = :password", User.class
				).setParameter("email", credentials.getUsername()).setParameter("password", credentials.getPassword());
		List<User> results = query.getResultList();
		if (results != null && !results.isEmpty()){
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
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
