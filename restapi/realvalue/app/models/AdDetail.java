package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AdDetail {

	private Long advertismentId;
	private String title;
	private String propertyStreet;
	private String propertyZip;
	private String propertyCityname;
	private String propertyCountry;
	private String currency;
	private Double sellingPrice;
	private String objectCategory;
	private Long surfaceLiving;
	private String geoLocation;
	private Double numberRooms;
	
	public AdDetail(){
		//
	}

	public Long getAdvertismentId() {
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

	public Double getSellingPrice() {
		return sellingPrice;
	}

	public String getObjectCategory() {
		return objectCategory;
	}

	public Long getSurfaceLiving() {
		return surfaceLiving;
	}

	public String getGeoLocation() {
		return geoLocation;
	}

	public void setAdvertismentId(Long advertismentId) {
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

	public void setSellingPrice(Double sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public void setObjectCategory(String objectCategory) {
		this.objectCategory = objectCategory;
	}

	public void setSurfaceLiving(Long surfaceLiving) {
		this.surfaceLiving = surfaceLiving;
	}

	public void setGeoLocation(String geoLocation) {
		this.geoLocation = geoLocation;
	}

	public double getNumberRooms() {
		return numberRooms;
	}

	public void setNumberRooms(Double numberRooms) {
		this.numberRooms = numberRooms;
	}
}
