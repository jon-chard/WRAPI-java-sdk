package org.radioplayer.wrapi.sdk.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Data extends Station {

	private MediaCredits mediaCredits;
	private String scheduledStart;
	private String scheduledStop;
	private String type;
	private String[] factors;
	private Song song;
	private Show show;
	private String id;
	
	private InnerData[] innerdata;
	
	private Meta meta;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@JsonProperty("data")
	public InnerData[] getInnerdata() {
		return innerdata;
	}

	public void setInnerdata(InnerData[] innerdata) {
		this.innerdata = innerdata;
	}

	public Meta getMeta() {
		return meta;
	}

	public void setMeta(Meta meta) {
		this.meta = meta;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String[] getFactors() {
		return factors;
	}

	public void setFactors(String[] factors) {
		this.factors = factors;
	}

	public Song getSong() {
		return song;
	}

	public void setSong(Song song) {
		this.song = song;
	}

	public Show getShow() {
		return show;
	}

	public void setShow(Show show) {
		this.show = show;
	}

	public MediaCredits getMediaCredits() {
		return mediaCredits;
	}

	public void setMediaCredits(MediaCredits mediaCredits) {
		this.mediaCredits = mediaCredits;
	}

	public String getScheduledStart() {
		return scheduledStart;
	}

	public void setScheduledStart(String scheduledStart) {
		this.scheduledStart = scheduledStart;
	}

	public String getScheduledStop() {
		return scheduledStop;
	}

	public void setScheduledStop(String scheduledStop) {
		this.scheduledStop = scheduledStop;
	}
	
}
