package helpers;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletionException;
import java.util.concurrent.CompletionStage;

import javax.inject.Inject;

import com.fasterxml.jackson.databind.JsonNode;

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
			JsonNode node = complStage.toCompletableFuture().join();
			return node;
		} catch (CompletionException e) {
			e.printStackTrace();
			return null;
		} catch (CancellationException e) {
			e.printStackTrace();
			return null;
		}
	}
}
