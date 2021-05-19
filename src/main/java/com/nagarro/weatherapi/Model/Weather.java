package com.nagarro.weatherapi.Model;

public class Weather {

	private float longitude;
	private double latitude;
	private String description;
	private String city;

	public Weather() {

	}

	public Weather(float longitude, double latitude, String description, String city) {
		this.longitude = longitude;
		this.latitude = latitude;
		this.description = description;
		this.city = city;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "Weather [longitude=" + longitude + ", latitude=" + latitude + ", description=" + description + ", city="
				+ city + "]";
	}
	

}
