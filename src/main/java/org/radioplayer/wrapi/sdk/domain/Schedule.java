package org.radioplayer.wrapi.sdk.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Schedule {
	private Logger logger = LogManager.getLogger(getClass());
	
	private Long rpuid;
	private String name;
	private String description;
	
	private String scheduledStart; // Date?
	private String scheduledStop;
	
	private MediaCredits mediaCredits;
	private InnerData[] innerdata;

	public Long getRpuid() {
		return rpuid;
	}

	public void setRpuid(Long rpuid) {
		this.rpuid = rpuid;
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

	public MediaCredits getMediaCredits() {
		return mediaCredits;
	}

	public void setMediaCredits(MediaCredits mediaCredits) {
		this.mediaCredits = mediaCredits;
	}
	
	public InnerData[] getInnerdata() {
		return innerdata;
	}

	public void setInnerdata(InnerData[] innerdata) {
		this.innerdata = innerdata;
	}
	
	public Date getScheduledStartDate() {
		return parseDate(getScheduledStart());
	}
	
	public Date getScheduledStopDate() {
		return parseDate(getScheduledStop());
	}
	
	private Date parseDate(String date) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		try {
			return df.parse(date);
		} catch (ParseException e) {
			logger.error("Could not parse date " + date, e);
		}
		return null;
	}
}
