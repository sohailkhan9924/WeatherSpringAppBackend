package com.nagarro.WeatherApp.Controller;

import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.WeatherApp.Model.Weather;
import com.nagarro.WeatherApp.Service.WeatherService;

@RestController
@CrossOrigin(origins = "*")
public class WeatherController {

	@Autowired
	private WeatherService WeatherService;

	@RequestMapping(value = "/weather", method = RequestMethod.POST)
	public ResponseEntity<Weather> getWeatherData(@RequestBody LinkedHashMap<String, ?> data) {
		String city = (String) data.get("city");
		

		Weather weatherApp=WeatherService.getWeatherData(city);
		System.out.println(weatherApp.getDescription());
		return ResponseEntity.status(HttpStatus.OK).body(weatherApp);
	}
//
//	@RequestMapping(value = "/weather", method = RequestMethod.GET)
//	public ResponseEntity<Weatherdto> getWeatherData() {
//
//		return ResponseEntity.status(HttpStatus.OK).body(Weatherdto);
//	}

}
