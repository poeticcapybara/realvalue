package services;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import models.OriginalSearchItem;
import models.OriginalSearchResponse;
import models.RealEstateItem;
import models.SearchResponse;
import helpers.APIFetcher;
import helpers.PriceComputer;

public class SearchService {

	private APIFetcher apiFetcher;
	
	@Inject
	public SearchService(APIFetcher apiFetcher){
		this.apiFetcher = apiFetcher;
	}
	
	public SearchResponse search(String city, boolean toBuy){
		//Add query parameter key value pairs
		List<Map.Entry<String,String>> l = new ArrayList<Map.Entry<String,String>>();
		l.add(new AbstractMap.SimpleEntry<String,String>("cit",city));
		if (toBuy) l.add(new AbstractMap.SimpleEntry<String,String>("cht","purchall"));
		else l.add(new AbstractMap.SimpleEntry<String,String>("cht","rentall"));
		//l.add(new AbstractMap.SimpleEntry<String,String>("nrs","1000"));
		JsonNode node = apiFetcher.fetch("/rs/real-estates", l);
		if (node==null) return null;
		try {
			ObjectMapper o = new ObjectMapper();
			OriginalSearchResponse searchResp = o.treeToValue(node, OriginalSearchResponse.class);
			//Keep only properties that have numberRooms, city, surfaceLiving and agencyId set
			List<OriginalSearchItem> items = searchResp.getItems();
			SearchResponse resp = new SearchResponse();
			List<RealEstateItem> respItems = new ArrayList<RealEstateItem>();
			for(int i = 0;i<items.size();i++){
				OriginalSearchItem item = items.get(i);
				if ((item.getCity()!=null)
						&& (item.getAgencyId()!=null)
						&& (item.getNumberRooms()>0)
						&& (item.getSurfaceLiving()>0)
						&& (item.getGeoLocation()!=null)
						&& (item.getPrice()>0)){
					//Compute fair price
					RealEstateItem it = new RealEstateItem();
					it.setAdvId(item.getAdvId());
					it.setActualPrice(item.getPrice());
					it.setFairPrice(PriceComputer.computePrice(item.getSurfaceLiving(), item.getNumberRooms()));
					it.setGeoLocation(item.getGeoLocation());
					respItems.add(it);
				}
			}
			resp.setList(respItems);
			return resp;
		}
		catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
	}
}
