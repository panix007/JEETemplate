package com.ntahr.webapp.controller;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.ntahr.common.dataaccess.objects.User;
import com.ntahr.webapp.services.UserServices;

@Path("/entities")
public class UserController {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@PermitAll
	@Path("/user")
	public Response createUser(User user) {

		UserServices userServices = new UserServices();
		userServices.createUser(user);
		return Response.status(200).entity(user).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@PermitAll
	@Path("/users")
	public Response getAllUsers() {
		UserServices userServices = new UserServices();
		List<User> users = userServices.getAllUsers();
		return Response.status(200).entity(users).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/authenticate")
	public Response authenticate(User user) {

		// UserServices userServices = new UserServices();
		// userServices.createUser(user);
		return Response.status(200).entity(user).build();
	}
}
