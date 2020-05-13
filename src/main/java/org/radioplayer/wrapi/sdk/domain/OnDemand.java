package org.radioplayer.wrapi.sdk.domain;


public class OnDemand {

	private String id;
	private String name;
	private String description;
	private String country;
	private MultiMedia[] multimedia;
	private OnDemandStreams[] onDemandStreams;
	private Song song;
	private Show show;
	private Series series;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
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

	public Series getSeries() {
		return series;
	}

	public void setSeries(Series series) {
		this.series = series;
	}
			
}
