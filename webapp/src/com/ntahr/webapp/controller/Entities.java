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

import com.ntahr.common.dataaccess.objects.Customer;
import com.ntahr.webapp.services.CustomerServices;

@Path("/entities")
public class Entities {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@PermitAll
	@Path("/customers")
	public Response getAllCustomers() {
		CustomerServices customerServices = new CustomerServices();
		List<Customer> customers = customerServices.getAllCustomers();
		return Response.status(200).entity(customers).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/customer")
	public Response createCustomer(Customer customer) {

		CustomerServices customerServices = new CustomerServices();
		customerServices.createCustomer(customer);
		return Response.status(200).entity(customer).build();
	}
}
