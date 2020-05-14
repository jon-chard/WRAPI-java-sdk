package org.radioplayer.wrapi.sdk.service;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.logging.log4j.Logger;
import org.radioplayer.wrapi.sdk.request.RequestHandler;

public class WRAPIService {
	
	RequestHandler requestHandler;

	public WRAPIService(RequestHandler handler) {
		this.requestHandler = handler;
	}
	
	protected HttpResponse getResponse(HttpUriRequest request) throws IOException {
		return requestHandler.signAndSubmitRequest( request );
	}
	
	public void showInfo(Logger logger, String text) {
		if (logger.isInfoEnabled()) {
			logger.debug(text);
		}
	}
	
	public void showDebug(Logger logger, String text) {
		if (logger.isDebugEnabled()) {
			logger.debug(text);
		}
	}

}
