package services;

import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;

import javax.inject.Inject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import models.AdDetail;
import play.libs.ws.*;

public class RealEstateDetailFetcher {

	private WSClient ws;
	
	@Inject
	public RealEstateDetailFetcher(WSClient ws){
		this.ws = ws;
	}
	
	public AdDetail fetchDetail(long adID){
		StringBuilder fetchURL = new StringBuilder("https://api.tamedia.cloud/homegate/v1/rs/real-estates/");
		fetchURL.append(Long.toString(adID));
		fetchURL.append("?lan=");
		fetchURL.append("de");
		String url = fetchURL.toString();
		CompletionStage<JsonNode> complStage = ws.url(url)
				.setHeader("accept", "application/json")
				.setHeader("apikey", "6d293d15ec404a00acd74d8a5be27183")
				.get().thenApply(WSResponse::asJson);
		try {
			JsonNode node = complStage.toCompletableFuture().get();
			ObjectMapper o = new ObjectMapper();
			AdDetail adDetail = o.treeToValue(node, AdDetail.class);
			return adDetail;
		} catch (InterruptedException e) {
			e.printStackTrace();
			return null;
		} catch (ExecutionException e) {
			e.printStackTrace();
			return null;
		}
		catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
	}
}
