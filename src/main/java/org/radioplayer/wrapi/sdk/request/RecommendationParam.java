package org.radioplayer.wrapi.sdk.request;

import org.radioplayer.wrapi.sdk.domain.ArtistPlayCounts;

public class RecommendationParam {

	private String country;
	private String[] factors;
	private String rpuid;
	private Double latitude;
	private Double longitude;
	private ArtistPlayCounts[] artistPlayCounts;
	private String[] facebookArtists;
	private boolean onDemand;
	
	
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
	
	public String[] getFactors() {
		return factors;
	}
	
	public void setFactors(String[] factors) {
		this.factors = factors;
	}
	
	public String getRpuid() {
		return rpuid;
	}
	
	public void setRpuid(String rpuid) {
		this.rpuid = rpuid;
	}
	
	public Double getLatitude() {
		return latitude;
	}
	
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	
	public Double getLongitude() {
		return longitude;
	}
	
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	
	public ArtistPlayCounts[] getArtistPlayCounts() {
		return artistPlayCounts;
	}
	
	public void setArtistPlayCounts(ArtistPlayCounts[] artistArr) {
		this.artistPlayCounts = artistArr;
	}
	
	public String[] getFacebookArtists() {
		return facebookArtists;
	}
	
	public void setFacebookArtists(String[] facebookArtists) {
		this.facebookArtists = facebookArtists;
	}
	
	public boolean isOnDemand() {
		return onDemand;
	}
	
	public void setOnDemand(boolean onDemand) {
		this.onDemand = onDemand;
	}
	
}
