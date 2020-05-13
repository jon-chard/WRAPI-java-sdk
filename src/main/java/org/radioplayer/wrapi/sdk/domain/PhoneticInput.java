package org.radioplayer.wrapi.sdk.domain;

public class PhoneticInput {

	private String id;
	private String type;
	private String value;
	private String[] exclude;
	
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	public String[] getExclude() {
		return exclude;
	}
	
	public void setExclude(String[] exclude) {
		this.exclude = exclude;
	}
	
}
