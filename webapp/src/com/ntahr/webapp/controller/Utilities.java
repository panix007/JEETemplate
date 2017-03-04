package com.ntahr.webapp.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.security.PermitAll;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.ntahr.webapp.services.UtilityServices;

@Path("/utilities")
public class Utilities {

	@GET
	@Produces("application/json")
	@Path("/CtoF")
	@PermitAll
	public Response convertCtoF() {
		double celsius = 36.88;
		return convertCtoFfromInput(celsius);
	}

	@GET
	@Produces("application/json")
	@Path("/CtoF/{c}")
	@PermitAll
	public Response convertCtoFfromInput(@PathParam("c") double celsius) {
		UtilityServices utilityServices = new UtilityServices();
		double fahrenheit = utilityServices.celciusToFahrenheit(celsius);
		return Response.status(200).entity(getTemperature(celsius, fahrenheit)).build();
	}

	@GET
	@Produces("application/json")
	@Path("/FtoC")
	@PermitAll
	public Response convertFtoC() {
		Double fahrenheit = 98.24;
		return convertFtoCfromInput(fahrenheit);
	}

	@GET
	@Produces("application/json")
	@Path("/FtoC/{f}")
	@PermitAll
	public Response convertFtoCfromInput(@PathParam("f") double fahrenheit) {
		UtilityServices utilityServices = new UtilityServices();
		double celsius = utilityServices.fahrenheitToCelcius(fahrenheit);
		return Response.status(200).entity(getTemperature(celsius, fahrenheit)).build();
	}

	private Map<String, String> getTemperature(double celsius, double fahrenheit) {
		Map<String, String> temperatureMap = new HashMap<>();
		temperatureMap.put("Fahrenheit", "" + fahrenheit);
		temperatureMap.put("Celsius", "" + celsius);
		return temperatureMap;
	}

}
