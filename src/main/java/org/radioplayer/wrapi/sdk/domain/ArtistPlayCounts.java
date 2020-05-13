package org.radioplayer.wrapi.sdk.domain;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class ArtistPlayCounts {
	
	private String artistName;
	private Integer playCount;
	
	
	public String getArtistName() {
		return artistName;
	}
	
	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}
	
	public Integer getPlayCount() {
		return playCount;
	}
	
	public void setPlayCount(Integer playCount) {
		this.playCount = playCount;
	}

	public ObjectNode toJson() {
		ObjectMapper om = new ObjectMapper();
		ObjectNode node = om.createObjectNode();
		node.put("artistName", getArtistName());
		node.put("playCount", getPlayCount());
		return node;
	}

}
