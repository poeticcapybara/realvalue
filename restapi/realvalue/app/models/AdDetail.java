package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AdDetail {

	private long advertismentId;
	private String title;
	private String propertyStreet;
	private String propertyZip;
	private String propertyCityname;
	private String propertyCountry;
	private String currency;
	private long sellingPrice;
	private String objectCategory;
	private long surfaceLiving;
	private String geoLocation;
	private double numberRooms;
	
	public AdDetail(){
		//
	}

	public long getAdvertismentId() {
		return advertismentId;
	}

	public String getTitle() {
		return title;
	}

	public String getPropertyStreet() {
		return propertyStreet;
	}

	public String getPropertyZip() {
		return propertyZip;
	}

	public String getPropertyCityname() {
		return propertyCityname;
	}

	public String getPropertyCountry() {
		return propertyCountry;
	}

	public String getCurrency() {
		return currency;
	}

	public long getSellingPrice() {
		return sellingPrice;
	}

	public String getObjectCategory() {
		return objectCategory;
	}

	public long getSurfaceLiving() {
		return surfaceLiving;
	}

	public String getGeoLocation() {
		return geoLocation;
	}

	public void setAdvertismentId(long advertismentId) {
		this.advertismentId = advertismentId;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setPropertyStreet(String propertyStreet) {
		this.propertyStreet = propertyStreet;
	}

	public void setPropertyZip(String propertyZip) {
		this.propertyZip = propertyZip;
	}

	public void setPropertyCityname(String propertyCityname) {
		this.propertyCityname = propertyCityname;
	}

	public void setPropertyCountry(String propertyCountry) {
		this.propertyCountry = propertyCountry;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public void setSellingPrice(long sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public void setObjectCategory(String objectCategory) {
		this.objectCategory = objectCategory;
	}

	public void setSurfaceLiving(long surfaceLiving) {
		this.surfaceLiving = surfaceLiving;
	}

	public void setGeoLocation(String geoLocation) {
		this.geoLocation = geoLocation;
	}

	public double getNumberRooms() {
		return numberRooms;
	}

	public void setNumberRooms(double numberRooms) {
		this.numberRooms = numberRooms;
	}
}
