package org.radioplayer.wrapi.sdk.request;

import java.util.ArrayList;
import java.util.List;

public class RetrieveParams {
	
	private static final String param = "include";
	
	private boolean getIds;
	private boolean getDetail;
	
	private boolean getImages;
	private boolean getStreams;
	private boolean getBearers;
	private boolean getSocial;
	
	public String getParamName() {
		return param;
	}
	
	// SETTER
	public void getIds(boolean getIds) {
		this.getIds = getIds;
	}
	
	public boolean isGetBearers() {
		return getBearers;
	}

	public boolean isGetIds() {
		return getIds;
	}

	public boolean isGetImages() {
		return getImages;
	}

	public boolean isGetStreams() {
		return getStreams;
	}

	public boolean isGetDetail() {
		return getDetail;
	}
	
	public void getDetail(boolean getDetail) {
		this.getDetail = getDetail;
	}
	

	public void getImages(boolean getImages) {
		this.getImages = getImages;
	}

	public void getStreams(boolean getStreams) {
		this.getStreams = getStreams;
	}

	public void getBearers(boolean getBearers) {
		this.getBearers = getBearers;
	}

	public boolean isGetSocial() {
		return getSocial;
	}

	public void setGetSocial(boolean getSocial) {
		this.getSocial = getSocial;
	}
	
	public String getRetrieveParams() {
		String includeList = "ids"; // Default is to get ids
		List<String> includes = new ArrayList<String>();
		if (isGetIds()) {
			includes.add("ids");
		}
		if (isGetDetail()) {
			includes.add("detail");
		}
		if (isGetBearers()) {
			includes.add("bearers");
		}
		if (isGetStreams()) {
			includes.add("streams");
		}
		if (isGetImages()) {
			includes.add("images");
		}
		
		if (!includes.isEmpty()) {
			includeList = String.join(",", includes);
		}
		return includeList;
	}
	
	public boolean hasParams() {
		return ( isGetIds() || isGetDetail() || isGetBearers() || isGetImages());
	}
}
