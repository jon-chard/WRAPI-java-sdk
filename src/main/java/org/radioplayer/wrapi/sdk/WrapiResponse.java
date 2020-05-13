package org.radioplayer.wrapi.sdk;

import java.util.List;

public interface WrapiResponse {

	List<Object> getResults();
	
	Object getResult(Long id);
	
}
