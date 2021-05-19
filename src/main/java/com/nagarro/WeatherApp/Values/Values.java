package com.nagarro.WeatherApp.Values;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Values {

	static LocalDate dateToday = LocalDate.now();
	public static final String date = dateToday.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	public static final String url ="http://api.openweathermap.org/data/2.5/weather";
	public static final String appId ="0276efc0dc4bed38b1d8982cce79a5f7";

}
