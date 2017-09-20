package com.ntahr.webapp.controller;

import com.ntahr.common.dataaccess.objects.Customer;
import com.ntahr.webapp.services.CustomerServices;

import javax.annotation.security.PermitAll;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/entities")
public class Entities {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@PermitAll
	@Path("/customers")
	public Response getAllCustomers() {
		CustomerServices customerServices = new CustomerServices();
		List<Customer> customers = customerServices.retrieve();
		return Response.status(200).entity(customers).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/customer")
	public Response createCustomer(Customer customer) {

		CustomerServices customerServices = new CustomerServices();
		customerServices.create(customer);
		return Response.status(200).entity(customer).build();
	}
}
