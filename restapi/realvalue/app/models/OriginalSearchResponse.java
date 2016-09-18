package models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OriginalSearchResponse {

	private long resultCount;
	private long pageCount;
	private List<OriginalSearchItem> items;
	
	public OriginalSearchResponse(){
		this.items = new ArrayList<OriginalSearchItem>();
	}

	public long getResultCount() {
		return resultCount;
	}

	public long getPageCount() {
		return pageCount;
	}

	public List<OriginalSearchItem> getItems() {
		return items;
	}

	public void setResultCount(long resultCount) {
		this.resultCount = resultCount;
	}

	public void setPageCount(long pageCount) {
		this.pageCount = pageCount;
	}

	public void setItems(List<OriginalSearchItem> items) {
		this.items = items;
	}
}
