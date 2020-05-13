package org.radioplayer.wrapi.sdk;

import java.io.IOException;


public class WrapiFactory {
	
	public static WrapiConnector getConnector(String pathToPem, String apiKey) throws IOException {
		
		return new WrapiConnector(pathToPem, apiKey);
		
	}

}
