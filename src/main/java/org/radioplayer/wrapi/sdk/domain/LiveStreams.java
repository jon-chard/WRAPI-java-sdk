package org.radioplayer.wrapi.sdk.domain;

import java.util.Date;


public class LiveStreams {
	
	private StreamSource streamSource;
	private String audioFormat;
	private BitRate bitRate;
	private StreamRestriction streamRestriction;
	
	private Date availableStart;
	private Date availableStop;
	
	
	public StreamSource getStreamSource() {
		return streamSource;
	}
	
	public void setStreamSource(StreamSource streamSource) {
		this.streamSource = streamSource;
	}
	
	public String getAudioFormat() {
		return audioFormat;
	}
	
	public void setAudioFormat(String audioFormat) {
		this.audioFormat = audioFormat;
	}
	
	public BitRate getBitRate() {
		return bitRate;
	}
	
	public void setBitRate(BitRate bitRate) {
		this.bitRate = bitRate;
	}
	
	public StreamRestriction getStreamRestriction() {
		return streamRestriction;
	}
	
	public void setStreamRestriction(StreamRestriction streamRestriction) {
		this.streamRestriction = streamRestriction;
	}
	
	public Date getAvailableStart() {
		return availableStart;
	}
	
	public void setAvailableStart(Date availableStart) {
		this.availableStart = availableStart;
	}
	
	public Date getAvailableStop() {
		return availableStop;
	}
	
	public void setAvailableStop(Date availableStop) {
		this.availableStop = availableStop;
	}
	
}
