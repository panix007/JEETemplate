package com.ntahr.webapp.controller;

import com.ntahr.common.dataaccess.objects.DeviceModel;
import com.ntahr.common.dataaccess.objects.PaginationDetails;
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
        deviceMasterServices.create(deviceModel);
        return Response.status(200).entity(deviceModel).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @PermitAll
    @Path("/devicemodels")
    public Response getAllDeviceMaster(@QueryParam("offset") String offset, @QueryParam("size") String size) {
        DeviceMasterServices deviceMasterServices = new DeviceMasterServices();
        List<DeviceModel> deviceModels;
        if (offset != null && size != null) {
            deviceModels = deviceMasterServices.retrievePage(Integer.parseInt(offset), Integer.parseInt(size));
        } else {
            deviceModels = deviceMasterServices.retrieve();
        }

        return Response.status(200).entity(deviceModels).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @PermitAll
    @Path("/devicemodels/pagination/{size}")
    public Response getDeviceMasterPaginationDetails(@PathParam("size") String size) {
        DeviceMasterServices deviceMasterServices = new DeviceMasterServices();
        PaginationDetails paginationDetails = deviceMasterServices.getPaginationDetails(Integer.parseInt(size));
        return Response.status(200).entity(paginationDetails).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
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
        deviceMasterServices.delete(deviceModel);
        return Response.status(200).entity(deviceModel).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @PermitAll
    @Path("/devicemodel")
    public Response updateDeviceMaster(DeviceModel deviceModel) {
        DeviceMasterServices deviceMasterServices = new DeviceMasterServices();
        deviceMasterServices.update(deviceModel);
        return Response.status(200).entity(deviceModel).build();
    }
}
