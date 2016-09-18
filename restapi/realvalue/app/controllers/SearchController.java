package controllers;

import javax.inject.Inject;

import models.SearchResponse;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import services.SearchService;

public class SearchController extends Controller {

private SearchService searchService;
	
	@Inject
	public SearchController(SearchService searchService){
		this.searchService = searchService;
	}
	
	public Result index(String city, String toBuy){
		if ((city==null) || (city.isEmpty()) || (toBuy==null)) {
			city = "Error adURL was null!";
			return badRequest();
		}
		SearchResponse resp = searchService.search(city, Boolean.parseBoolean(toBuy));
		if (resp==null) return badRequest();
		return ok(Json.toJson(resp));
	}
}
