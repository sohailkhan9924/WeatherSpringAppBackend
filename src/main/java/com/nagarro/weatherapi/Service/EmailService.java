package com.nagarro.weatherapi.Service;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


import com.nagarro.weatherapi.Model.Weather;
import com.nagarro.weatherapi.Values.Values;

@Service
public class EmailService {
	
	@Autowired
	private WeatherService WeatherService;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Scheduled(cron = "0 0 10 * * ?")
	// @Scheduled(cron = "[Seconds] [Minutes] [Hours] [Day of month] [Month] [Day of
	// week] [Year]")
	// Send Periodic Mails
	public void sendPeriodicEmail() {
		Map<String,String> userData=new LinkedHashMap<>();	     
	     userData.put("sohail.khan@nagarro.com","Amritsar");
	     userData.put("sohailkhan4764@gmail.com","Amritsar");		
	     for(String email:userData.keySet()) {
	    	 String city=userData.get(email);
	    	 Weather weatherData = WeatherService.getWeatherData(city);
	    	 sendEmail(email, Values.date, city, weatherData.getDescription());
	    	 System.out.println(weatherData);
	     }

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
