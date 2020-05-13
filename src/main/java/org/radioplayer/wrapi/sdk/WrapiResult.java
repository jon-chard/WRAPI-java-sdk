package org.radioplayer.wrapi.sdk;

import org.radioplayer.wrapi.sdk.domain.Data;
import org.radioplayer.wrapi.sdk.domain.Meta;

public class WrapiResult  {
	
	private Meta meta;
	private Data[] data;
	

	public Meta getMeta() {
		return meta;
	}
	
	public void setMeta(Meta meta) {
		this.meta = meta;
	}

	public Data[] getData() {
		return data;
	}

	public void setData(Data[] data) {
		this.data = data;
	}
	
}
