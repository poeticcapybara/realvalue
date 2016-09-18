package services;

import helpers.AdIdExtractor;
import helpers.PriceComputer;

import javax.inject.Inject;

import models.AdDetail;
import models.PriceResponse;

public class PricePredictionService {

	private RealEstateDetailFetcher fetcher;
	
	@Inject
	public PricePredictionService(RealEstateDetailFetcher fetcher){
		this.fetcher = fetcher;
	}
	
	public PriceResponse getPriceResponse(String adURL){
		long adID = AdIdExtractor.extractAdId(adURL);
		AdDetail adDetail = fetcher.fetchDetail(adID);
		if (adDetail==null) return null;
		PriceResponse pr = new PriceResponse();
		pr.setActualPrice(adDetail.getSellingPrice());
		//Compute fair price
		pr.setFairPrice(PriceComputer.computePrice(adDetail.getSurfaceLiving(), adDetail.getNumberRooms()));
		return pr;
	}
}