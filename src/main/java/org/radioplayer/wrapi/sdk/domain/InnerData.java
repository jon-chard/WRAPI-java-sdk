package org.radioplayer.wrapi.sdk.domain;

public class InnerData {
	
	private String name;
	private String description;
	private String country;
	private String rpuid;
	private String id;
	private Song song;
	private Show show;
	private Series series;
	private MultiMedia[] multimedia;
	private OnDemandStreams[] onDemandStreams;
	private MediaCredits mediaCredits;
	
	private Location[] locations;
	private String scheduledStart;
	private String scheduledStop;
	
	
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public MultiMedia[] getMultimedia() {
		return multimedia;
	}

	public void setMultimedia(MultiMedia[] multimedia) {
		this.multimedia = multimedia;
	}

	public OnDemandStreams[] getOnDemandStreams() {
		return onDemandStreams;
	}

	public void setOnDemandStreams(OnDemandStreams[] onDemandStreams) {
		this.onDemandStreams = onDemandStreams;
	}

	public Series getSeries() {
		return series;
	}

	public void setSeries(Series series) {
		this.series = series;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRpuid() {
		return rpuid;
	}

	public void setRpuid(String rpuid) {
		this.rpuid = rpuid;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Location[] getLocations() {
		return locations;
	}

	public void setLocations(Location[] locations) {
		this.locations = locations;
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
