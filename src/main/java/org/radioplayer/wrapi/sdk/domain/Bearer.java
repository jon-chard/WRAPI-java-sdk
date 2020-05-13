package org.radioplayer.wrapi.sdk.domain;

public class Bearer {
	
	private String id;
	private int cost;
	private String mimeValue;
	
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public int getCost() {
		return cost;
	}
	
	public void setCost(int cost) {
		this.cost = cost;
	}
	
	public String getMimeValue() {
		return mimeValue;
	}
	
	public void setMimeValue(String mimeValue) {
		this.mimeValue = mimeValue;
	}
	
}
