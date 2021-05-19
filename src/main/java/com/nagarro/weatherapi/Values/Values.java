package com.nagarro.weatherapi.Values;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Values {
	static LocalDate dateToday = LocalDate.now();
	public static final String date = dateToday.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	public static final String url ="http://api.openweathermap.org/data/2.5/weather";
	public static final String appId ="0276efc0dc4bed38b1d8982cce79a5f7";
	
	
//	public static final String[] emailData ={"sohail.khan@nagarro.com","sohailkhan4764@gmail.com"};
	

}
