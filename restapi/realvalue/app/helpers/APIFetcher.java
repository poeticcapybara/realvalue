package helpers;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;

import javax.inject.Inject;

import models.AdDetail;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import play.libs.ws.WSClient;
import play.libs.ws.WSResponse;

public class APIFetcher {
	
	private WSClient ws;
	private String baseURL = "https://api.tamedia.cloud/homegate/v1";
	
	@Inject
	public APIFetcher(WSClient ws){
		this.ws = ws;
	}

	public JsonNode fetch(String url, List<Map.Entry<String, String> > kvList){
		StringBuilder fetchURL = new StringBuilder(baseURL);
		fetchURL.append(url);
		fetchURL.append("?");
		for(int i=0;i<kvList.size();i++){
			Map.Entry<String,String> pair = kvList.get(i);
			fetchURL.append(pair.getKey());
			fetchURL.append('=');
			fetchURL.append(pair.getValue());
			fetchURL.append('&');
		}
		String getUrl = fetchURL.toString();
		CompletionStage<JsonNode> complStage = ws.url(getUrl)
				.setHeader("accept", "application/json")
				.setHeader("apikey", "6d293d15ec404a00acd74d8a5be27183")
				.get().thenApply(WSResponse::asJson);
		try {
			JsonNode node = complStage.toCompletableFuture().get();
			return node;
		} catch (InterruptedException e) {
			e.printStackTrace();
			return null;
		} catch (ExecutionException e) {
			e.printStackTrace();
			return null;
		}
	}
}
