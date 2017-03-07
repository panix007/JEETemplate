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

import com.ntahr.common.dataaccess.objects.Location;
import com.ntahr.webapp.services.LocationServices;

@Path("/entities")
public class LocationController {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@PermitAll
	@Path("/location")
	public Response createLocation(Location location) {
		LocationServices locationServices = new LocationServices();
		locationServices.createLocation(location);
		return Response.status(200).entity(location).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@PermitAll
	@Path("/locations")
	public Response getAllLocations() {
		LocationServices locationServices = new LocationServices();
		List<Location> locations = locationServices.getAllLocations();
		return Response.status(200).entity(locations).build();
	}

}
