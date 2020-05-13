package org.radioplayer.wrapi.sdk.domain;

public class MultiMedia {
	
	private String url;
	private String mimeValue;
	private String language;
	private String type;
	private int width;
	private int height;
	private int index;
	
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getMimeValue() {
		return mimeValue;
	}
	
	public void setMimeValue(String mimeValue) {
		this.mimeValue = mimeValue;
	}
	
	public String getLanguage() {
		return language;
	}
	
	public void setLanguage(String language) {
		this.language = language;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public int getWidth() {
		return width;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
}
