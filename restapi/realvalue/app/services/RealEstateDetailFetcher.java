package services;

import helpers.APIFetcher;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;

import javax.inject.Inject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import models.AdDetail;

public class RealEstateDetailFetcher {

	private APIFetcher apiFetcher;
	
	@Inject
	public RealEstateDetailFetcher(APIFetcher apiFetcher){
		this.apiFetcher = apiFetcher;
	}
	
	public AdDetail fetchDetail(long adID){
		//Add query parameter key value pairs
		List<Map.Entry<String,String>> l = new ArrayList<Map.Entry<String,String>>();
		l.add(new AbstractMap.SimpleEntry<String,String>("lan","de"));
		//Provide entpoint url and query parameter list
		JsonNode node = apiFetcher.fetch("/rs/real-estates/"+Long.toString(adID), l);
		try {
			ObjectMapper o = new ObjectMapper();
			AdDetail adDetail = o.treeToValue(node, AdDetail.class);
			return adDetail;
		}
		catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
	}
}
