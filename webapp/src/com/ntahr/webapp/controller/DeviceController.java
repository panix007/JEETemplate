package com.ntahr.webapp.controller;

import com.ntahr.common.dataaccess.objects.Device;
import com.ntahr.webapp.services.DeviceServices;

import javax.annotation.security.PermitAll;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Path("/entities")
public class DeviceController {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@PermitAll
	@Path("/device")
	public Response createDevice(Device device) {
		DeviceServices deviceServices = new DeviceServices();
		deviceServices.createDevice(device);
		return Response.status(200).entity(device).build();
	}
	
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@PermitAll
	@Path("/devices")
	public Response getAllDevices(){
		DeviceServices deviceServices = new DeviceServices();
		List<Device> devices = deviceServices.getAllDevices();
		return Response.status(200).entity(devices).build();		
	}

	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@PermitAll
	@Path("/devices/model/{modelId}")
	public Response getDevicesByModel(@PathParam("modelId") String modelId) {
		DeviceServices deviceServices = new DeviceServices();
		List<Device> devices = deviceServices.getDeviceByModel(modelId);
		return Response.status(200).entity(devices).build();
	}
}
