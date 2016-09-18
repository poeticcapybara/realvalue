package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OriginalSearchItem {
	
	private String city;
	private double numberRooms;
	private double surfaceLiving;
	private String agencyId;
	private String advId;
	private double price;
	private String geoLocation;
	
	public OriginalSearchItem(){
		//
	}

	public String getCity() {
		return city;
	}

	public double getNumberRooms() {
		return numberRooms;
	}

	public double getSurfaceLiving() {
		return surfaceLiving;
	}

	public String getAgencyId() {
		return agencyId;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setNumberRooms(double numberRooms) {
		this.numberRooms = numberRooms;
	}

	public void setSurfaceLiving(double surfaceLiving) {
		this.surfaceLiving = surfaceLiving;
	}

	public void setAgencyId(String agencyId) {
		this.agencyId = agencyId;
	}

	public String getAdvId() {
		return advId;
	}

	public void setAdvId(String advId) {
		this.advId = advId;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getGeoLocation() {
		return geoLocation;
	}

	public void setGeoLocation(String geoLocation) {
		this.geoLocation = geoLocation;
	}
}
