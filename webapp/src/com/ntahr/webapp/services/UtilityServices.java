package com.ntahr.webapp.services;

public class UtilityServices {

	public double fahrenheitToCelcius(double fahrenheit){
		return	((fahrenheit - 32) * 5 / 9);		
	}
	
	public double celciusToFahrenheit(double celsius){
		return	(((celsius * 9) / 5) + 32);		
	}
	
}
