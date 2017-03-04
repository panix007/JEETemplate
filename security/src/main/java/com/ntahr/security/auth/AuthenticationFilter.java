package com.ntahr.security.auth;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import com.ntahr.security.auth.Authenticator.AUTH_RESULTS;

/**
 * This filter verify the access permissions for user 
 */
@Provider
public class AuthenticationFilter implements javax.ws.rs.container.ContainerRequestFilter {

	@Context
	private ResourceInfo resourceInfo;

	private static final String AUTHORIZATION_PROPERTY = "Authorization";
	private static final String FORBIDDEN_TEXT = "You are not allowed to access this resource";

	@Override
	public void filter(ContainerRequestContext requestContext) {
		Method method = resourceInfo.getResourceMethod();
		if (!method.isAnnotationPresent(PermitAll.class)) {
			if (method.isAnnotationPresent(DenyAll.class)) {
				requestContext.abortWith(Response.status(Response.Status.FORBIDDEN).entity(FORBIDDEN_TEXT).build());
				return;
			}

			final MultivaluedMap<String, String> headers = requestContext.getHeaders();
			final List<String> authorization = headers.get(AUTHORIZATION_PROPERTY);

			if (authorization == null || authorization.isEmpty()) {
				requestContext.abortWith(Response.status(Response.Status.FORBIDDEN).entity(FORBIDDEN_TEXT).build());
				return;
			}

			String authorizationHeader = authorization.get(0);

			Authenticator authenticator = AuthenticationFactory.getAuthenticator();
			AUTH_RESULTS results = authenticator.authenticate(authorizationHeader);

			switch (results) {

			case AUTHENTICATED:

				if (method.isAnnotationPresent(RolesAllowed.class)) {
					RolesAllowed rolesAnnotation = method.getAnnotation(RolesAllowed.class);
					Set<String> targetRoles = new HashSet<String>(Arrays.asList(rolesAnnotation.value()));
					Credentials credentials = authenticator.getCredentials(authorizationHeader);
					AUTH_RESULTS authorizationResults = authenticator.authorize(credentials, targetRoles);
					if (authorizationResults != AUTH_RESULTS.AUTHORIZED) {
						requestContext.abortWith(Response.status(Response.Status.FORBIDDEN)
								.entity("[" + results.toString() + "]" + FORBIDDEN_TEXT).build());
						return;
					}
				}

				break;

			case AUTHORIZED:
				break;
			case SUCCESS:
				break;
			case TOKEN_VALID:
				break;

			case REQUEST_TOKEN:
				Credentials credentials = authenticator.getCredentials(authorizationHeader);
				Token token = authenticator.createToken(credentials);
				requestContext.abortWith(Response.status(Response.Status.OK).entity(token).build());
				return;

			case NOT_AUTHENTICATED:
			case NOT_AUTHORIZED:
			case NOT_SUPPORTED:
			case TOKEN_EXPIRED:
			case TOKEN_INVALID:
			default:
				requestContext.abortWith(Response.status(Response.Status.FORBIDDEN)
						.entity("[" + results.toString() + "]" + FORBIDDEN_TEXT).build());
				return;

			}
		}
	}

}