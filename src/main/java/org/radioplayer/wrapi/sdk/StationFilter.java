package org.radioplayer.wrapi.sdk;

public class StationFilter {
	
	private int country;
	private String bearerId;
	private String searchTerm;
	private String geoLocation; 

	public int getCountry() {
		return country;
	}

	public void setCountry(int country) {
		this.country = country;
	}

	public String getBearerId() {
		return bearerId;
	}

	public void setBearerId(String bearerId) {
		this.bearerId = bearerId;
	}

	public String getSearchTerm() {
		return searchTerm;
	}

	public void setSearchTerm(String searchTerm) {
		this.searchTerm = searchTerm;
	}

	public String getGeoLocation() {
		return geoLocation;
	}

	public void setGeoLoction(String geoLoction) {
		this.geoLocation = geoLoction;
	}
	
}
