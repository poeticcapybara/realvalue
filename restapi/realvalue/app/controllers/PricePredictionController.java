package controllers;

import javax.inject.Inject;

import models.PriceResponse;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import services.PricePredictionService;

public class PricePredictionController extends Controller {
	
	private PricePredictionService priceService;
	
	@Inject
	public PricePredictionController(PricePredictionService priceService){
		this.priceService = priceService;
	}
	
	public Result index(String adURL){
		if ((adURL==null) || (adURL.isEmpty())) {
			adURL = "Error adURL was null!";
			return badRequest();
		}
		PriceResponse resp = priceService.getPriceResponse(adURL);
		if (resp==null) return badRequest();
		return ok(Json.toJson(resp));
	}
}
