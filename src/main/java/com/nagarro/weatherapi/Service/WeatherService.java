package com.nagarro.weatherapi.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.nagarro.weatherapi.Model.Weather;
import com.nagarro.weatherapi.Values.Values;

@Service
public class WeatherService {
	String[] cityData ={"Amritsar","gurgaon"};
	@Autowired
	private JavaMailSender javaMailSender;

	@SuppressWarnings("unchecked")
	// Get Data from api
	public Weather getWeatherData(String cityInp) {
		RestTemplate restTemplate = new RestTemplate();

		// Storing data in LinkedHashMap (data)
		LinkedHashMap<String, ?> data = (LinkedHashMap<String, ?>) restTemplate
				.getForObject(Values.url + "?q=" + cityInp + "&appid=" + Values.appId, Object.class);

		// Getting the data that is required for the functionality

		// coordinates
		LinkedHashMap<String, ?> coord = (LinkedHashMap<String, ?>) data.get("coord");
		double longitude = (double) coord.get("lon");
		double latitude = (double) coord.get("lat");

		// weather description
		ArrayList<?> weather = (ArrayList<?>) data.get("weather");
		LinkedHashMap<String, ?> weatherObj = (LinkedHashMap<String, ?>) weather.get(0);
		String description = (String) weatherObj.get("description");

		// Your City
		String city = (String) data.get("name");

//		 Temperature
		LinkedHashMap<String, ?> main = (LinkedHashMap<String, ?>) data.get("main");
		double temp = (double) main.get("temp") - 273.15;

//		Save Data in dataBase
		Weather weatherApp = new Weather(longitude, latitude, description, city);

		return weatherApp;

	}

	public Weather sendWeatherData(String cityInp) {
		Weather weatherData = getWeatherData(cityInp);

		return weatherData;

	}

	@Scheduled(cron = "0 18 17 * * ?")
	// @Scheduled(cron = "[Seconds] [Minutes] [Hours] [Day of month] [Month] [Day of
	// week] [Year]")
	// Send Periodic Mails
	public void sendPeriodicEmail() {
		Weather weatherData = getWeatherData(Values.cityData[0]);
		Weather weatherData1 = getWeatherData(Values.cityData[1]);
		sendEmail(Values.emailData[0], Values.date, Values.cityData[0], weatherData.getDescription());
		sendEmail(Values.emailData[1], Values.date, Values.cityData[1], weatherData1.getDescription());
	}

//	Mail Service
	public void sendEmail(String email, String date, String city, String description) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(email);
		message.setSubject("Your Daily  Weather Report");
		message.setText("You have registered for the daily Weather service @10 am IST." + "\r\n\n" + "Today's"
				+ " (Date: " + date + " ) weather report=> " + "\r\n\n" + "City:   " + city + "\r\n"
				+ "Weather Description:   " + description + "\r\n\n"
				+ "Nature is Powerfull,Stay Strong.\r\nThank you.\r\n");
		javaMailSender.send(message);
//		+ "\r\n" + "Temperature:  " + temp + " \u00B0C"
	}
}
