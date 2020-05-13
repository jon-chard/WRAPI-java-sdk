package org.radioplayer.wrapi.sdk.domain;

public class OnDemandParam {
	
	private String country;
	private String searchTerm;
	private String[] rpUids;
	
	
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
	
	public String getSearchTerm() {
		return searchTerm;
	}
	
	public void setSearchTerm(String searchTerm) {
		this.searchTerm = searchTerm;
	}
	
	public String[] getRpUids() {
		return rpUids;
	}
	
	public void setRpUids(String[] rpUids) {
		this.rpUids = rpUids;
	}
	
}
