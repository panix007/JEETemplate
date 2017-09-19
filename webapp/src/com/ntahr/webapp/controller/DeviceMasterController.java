package com.ntahr.webapp.controller;

import com.ntahr.common.dataaccess.objects.DeviceModel;
import com.ntahr.webapp.services.DeviceMasterServices;

import javax.annotation.security.PermitAll;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/entities")
public class DeviceMasterController {


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @PermitAll
    @Path("/devicemodel")
    public Response createDeviceMaster(DeviceModel deviceModel) {
        DeviceMasterServices deviceMasterServices = new DeviceMasterServices();
        deviceMasterServices.createDeviceModel(deviceModel);
        return Response.status(200).entity(deviceModel).build();
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @PermitAll
    @Path("/devicemodels")
    public Response getAllDeviceMaster() {
        DeviceMasterServices deviceMasterServices = new DeviceMasterServices();
        List<DeviceModel> deviceModels = deviceMasterServices.getAllDeviceModels();
        return Response.status(200).entity(deviceModels).build();
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @PermitAll
    @Path("/devicemodels/{modelName}")
    public Response getDeviceMasterByName(@PathParam("modelName") String modelName) {
        DeviceMasterServices deviceMasterServices = new DeviceMasterServices();
        List<DeviceModel> deviceModels = deviceMasterServices.getDeviceMasterByName(modelName);
        return Response.status(200).entity(deviceModels).build();
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @PermitAll
    @Path("/devicemodel")
    public Response deleteDeviceMaster(DeviceModel deviceModel) {
        DeviceMasterServices deviceMasterServices = new DeviceMasterServices();
        deviceMasterServices.deleteDeviceModel(deviceModel);
        return Response.status(200).entity(deviceModel).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @PermitAll
    @Path("/devicemodel")
    public Response updateDeviceMaster(DeviceModel deviceModel) {
        DeviceMasterServices deviceMasterServices = new DeviceMasterServices();
        deviceMasterServices.updateDeviceModel(deviceModel);
        return Response.status(200).entity(deviceModel).build();
    }
}
