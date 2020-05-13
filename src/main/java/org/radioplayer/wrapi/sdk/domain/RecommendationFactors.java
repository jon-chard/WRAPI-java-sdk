package org.radioplayer.wrapi.sdk.domain;

import java.util.ArrayList;

public class RecommendationFactors {
	
	private boolean geo;
	private boolean trending;
	private boolean affinity;
	private boolean musicmatch;
	
	
	public boolean isGeo() {
		return geo;
	}
	
	public void setGeo(boolean applyGEO) {
		this.geo = applyGEO;
	}
	
	public boolean isTrending() {
		return trending;
	}
	
	public void setTrending(boolean applyTRENDING) {
		this.trending = applyTRENDING;
	}
	
	public boolean isAffinity() {
		return affinity;
	}
	
	public void setAffinity(boolean applyAFFINITY) {
		this.affinity = applyAFFINITY;
	}
	
	public boolean isMusicMatch() {
		return musicmatch;
	}
	
	public void setMusicMatch(boolean mUSICMATCH) {
		musicmatch = mUSICMATCH;
	}

	public ArrayList<String> toArray() {
		ArrayList<String> factorArr = new ArrayList<String>();
		
		if (isAffinity()) {
			factorArr.add("AFFINITY");
		}
		
		if (isGeo()) {
			factorArr.add("GEO");
		}
		
		if (isTrending()) {
			factorArr.add("TRENDING");
		}
		
		if (isMusicMatch()) {
			factorArr.add("MUSICMATCH");
		}
		return factorArr;
	}
}
