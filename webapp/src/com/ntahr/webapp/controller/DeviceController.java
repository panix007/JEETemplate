package com.ntahr.webapp.controller;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.ntahr.common.dataaccess.objects.Device;
import com.ntahr.webapp.services.DeviceServices;


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
}
