package com.ntahr.webapp.controller;

import com.ntahr.common.dataaccess.genericdao.DaoBase;
import com.ntahr.common.dataaccess.objects.User;
import com.ntahr.webapp.services.BaseService;

import javax.annotation.security.PermitAll;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/entities")
public class UserController {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@PermitAll
	@Path("/user")
	public Response createUser(User user) {
		BaseService<User> userServices = new BaseService<>(() -> new DaoBase<>(User.class));
		userServices.create(user);
		return Response.status(200).entity(user).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@PermitAll
	@Path("/users")
	public Response getAllUsers() {
		BaseService<User> userServices = new BaseService<>(() -> new DaoBase<>(User.class));
		List<User> users = userServices.retrieve();
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
