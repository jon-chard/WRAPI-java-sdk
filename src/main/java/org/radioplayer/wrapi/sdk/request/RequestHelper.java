package org.radioplayer.wrapi.sdk.request;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.radioplayer.wrapi.sdk.WrapiResult;
import org.radioplayer.wrapi.sdk.service.WRAPIService;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;


public class RequestHelper extends WRAPIService {
	
	private Logger logger = LogManager.getLogger(getClass());
	
	public RequestHelper(RequestHandler handler) {
		super(handler);
	}

	public WrapiResult performGetRequest(String url) throws IOException, JsonParseException, JsonMappingException {
		
		HttpResponse response = getResponse(new HttpGet(url));
		WrapiResult results = getResults(response);
		
		return results;
	}
	
	public WrapiResult performPostRequest(String url, ObjectNode json) throws IOException, JsonParseException, JsonMappingException  {
		
		HttpPost post = new HttpPost(url);
		StringEntity requestEntity = new StringEntity(
			    json.toString(),
			    ContentType.APPLICATION_JSON);
        post.setEntity(requestEntity);
		
		HttpResponse response = getResponse(post);
		WrapiResult results = getResults(response);
		
		return results;
	}
	
	private WrapiResult getResults(HttpResponse response) throws ParseException, IOException {
		
		if (response == null) {
			WrapiResult result = null;
			return result;
		}
		
		HttpEntity entity = response.getEntity();
		String responseString = EntityUtils.toString(entity, "UTF-8");
		
		if (logger.isDebugEnabled()) {
			logger.debug("Output object: " + responseString);
		}
		
		ObjectMapper objectMapper = new ObjectMapper();
		WrapiResult results = objectMapper.readValue(responseString, WrapiResult.class);
		return results;
	}

}
