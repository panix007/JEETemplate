package com.ntahr.webapp.controller;

import com.ntahr.common.dataaccess.mongo.exception.MongoException;
import com.ntahr.common.dataaccess.mongo.objects.LocationPoint;
import com.ntahr.webapp.services.LocationServices;

import javax.annotation.security.PermitAll;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Set;

@Path("/entities")
public class LocationController {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@PermitAll
	@Path("/location/{deviceId}")
	public Response createLocation(LocationPoint location, @PathParam("deviceId") String deviceId) {
		LocationServices locationServices = new LocationServices();
		try {
			locationServices.createLocation(location, deviceId);
		} catch (MongoException e) {
			return Response.status(500).entity(e.getMessage()).build();
		}
		return Response.status(200).entity(location).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@PermitAll
	@Path("/locations/{deviceId}")
	public Response getLocationsByDevice(@PathParam("deviceId") String deviceId) {
		LocationServices locationServices = new LocationServices();
		List<LocationPoint> locations = null;
		try {
			locations = locationServices.getLocationsByDevice(deviceId);
		} catch (MongoException e) {
			return Response.status(500).entity(e.getMessage()).build();
		}
		return Response.status(200).entity(locations).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@PermitAll
	@Path("/locations/{deviceId}/{startTimeMillis}/{endTimeMillis}")
	public Response getLocationsByDeviceAndDateRange(@PathParam("deviceId") String deviceId,
													 @PathParam("startTimeMillis") String startTimeMillis, @PathParam("endTimeMillis") String endTimeMillis) {
		LocationServices locationServices = new LocationServices();
		List<LocationPoint> locations = null;
		try {
			locations = locationServices.getLocationsByDevice(deviceId, Long.parseLong(startTimeMillis), Long.parseLong(endTimeMillis));
		} catch (MongoException e) {
			return Response.status(500).entity(e.getMessage()).build();
		}
		return Response.status(200).entity(locations).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@PermitAll
	@Path("/deviceIds")
	public Response getDevices() {
		LocationServices locationServices = new LocationServices();
		Set<String> devices = null;
		try {
			devices = locationServices.getDevices();
		} catch (MongoException e) {
			return Response.status(500).entity(e.getMessage()).build();
		}
		return Response.status(200).entity(devices).build();
	}
}
