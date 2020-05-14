package org.radioplayer.wrapi.sdk.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Song {
	private Logger logger = LogManager.getLogger(getClass());
	
	private String artist;
	private String name;
	private String start;
	private String end;
	
	public String getArtist() {
		return artist;
	}
	public void setArtist(String artist) {
		this.artist = artist;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getEnd() {
		return end;
	}
	
	public void setEnd(String end) {
		this.end = end;
	}

	public Date getStartDate() {
		if(getStart()!=null) {
			return parseDate(getStart());
		}
		return null;
	}

	public Date getEndDate() {
		if(getEnd()!=null) {
			return parseDate(getEnd());
		}
		return null;
	}
	
	private Date parseDate(String date) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");
		try {
			return df.parse(date);
		} catch (ParseException e) {
			logger.error("Could not parse date " + date, e);
		}
		return null;
	}
}
