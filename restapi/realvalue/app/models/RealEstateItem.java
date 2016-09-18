package models;

public class RealEstateItem {
		
	private String advId;
	private String geoLocation;
	private double fairPrice;
	private double actualPrice;
		
	public String getAdvId() {
		return advId;
	}
	public String getGeoLocation() {
		return geoLocation;
	}
	public double getFairPrice() {
		return fairPrice;
	}
	public double getActualPrice() {
		return actualPrice;
	}
	public void setAdvId(String advId) {
		this.advId = advId;
	}
	public void setGeoLocation(String geoLocation) {
		this.geoLocation = geoLocation;
	}
	public void setFairPrice(double fairPrice) {
		this.fairPrice = fairPrice;
	}
	public void setActualPrice(double actualPrice) {
		this.actualPrice = actualPrice;
	}
}
