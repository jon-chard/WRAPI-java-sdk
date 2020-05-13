package org.radioplayer.wrapi.sdk.domain;

import java.util.Date;

public class StationParam {
	
	private String[] rpUids;
	private Date from;
	private Date to;
	
	private int page;
	private int size;
	
	
	public String[] getRpUids() {
		return rpUids;
	}
	
	public void setRpUids(String[] rpUids) {
		this.rpUids = rpUids;
	}
	
	public Date getFrom() {
		return from;
	}
	
	public void setFrom(Date from) {
		this.from = from;
	}
	
	public Date getTo() {
		return to;
	}
	
	public void setTo(Date to) {
		this.to = to;
	}
	
	public int getPage() {
		return page;
	}
	
	public void setPage(int page) {
		this.page = page;
	}
	
	public int getSize() {
		return size;
	}
	
	public void setSize(int size) {
		this.size = size;
	}
	
}
