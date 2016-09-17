package models;

public class PriceResponse {
	
	private long fairPrice;
	private long actualPrice;
	
	public PriceResponse(){
		//
	}

	public long getFairPrice() {
		return fairPrice;
	}

	public long getActualPrice() {
		return actualPrice;
	}

	public void setFairPrice(long fairPrice) {
		this.fairPrice = fairPrice;
	}

	public void setActualPrice(long actualPrice) {
		this.actualPrice = actualPrice;
	}

	
}
