package models;

public class PriceResponse {
	
	private double fairPrice;
	private double actualPrice;
	
	public PriceResponse(){
		//
	}

	public double getFairPrice() {
		return fairPrice;
	}

	public double getActualPrice() {
		return actualPrice;
	}

	public void setFairPrice(double fairPrice) {
		this.fairPrice = fairPrice;
	}

	public void setActualPrice(double actualPrice) {
		this.actualPrice = actualPrice;
	}
	
}
