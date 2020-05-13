package org.radioplayer.wrapi.sdk.exception;

import org.apache.log4j.Logger;

public class InvalidArgumentsException extends Exception implements BaseInputParameterExceptionImpl {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Logger logger = Logger.getLogger(getClass());

	@Override
	public void showMessage(String message) {
		logger.info(message);
		
	}
	
	public InvalidArgumentsException(String message) {
		showMessage(message);
	}

}
