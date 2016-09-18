package models;

import java.util.ArrayList;
import java.util.List;

public class SearchResponse {

	private List<RealEstateItem> list;
	
	public SearchResponse(){
		list = new ArrayList<RealEstateItem>();
	}

	public List<RealEstateItem> getList() {
		return list;
	}

	public void setList(List<RealEstateItem> list) {
		this.list = list;
	}
}
