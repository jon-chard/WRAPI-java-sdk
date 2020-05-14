package org.radioplayer.wrapi.sdk.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.radioplayer.wrapi.sdk.Constants;
import org.radioplayer.wrapi.sdk.WrapiResult;
import org.radioplayer.wrapi.sdk.domain.Data;
import org.radioplayer.wrapi.sdk.domain.InnerData;
import org.radioplayer.wrapi.sdk.domain.OnDemand;
import org.radioplayer.wrapi.sdk.domain.OnDemandParam;
import org.radioplayer.wrapi.sdk.exception.InvalidArgumentsException;
import org.radioplayer.wrapi.sdk.request.QueryStringBuilder;
import org.radioplayer.wrapi.sdk.request.RequestHandler;
import org.radioplayer.wrapi.sdk.request.RequestHelper;
import org.springframework.web.util.UriUtils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class OnDemandService extends WRAPIService  {
	
	private Logger logger = LogManager.getLogger(getClass());
	
	private static final String SEARCH_AND_COUNTRY_REQUIRED = "When specifying the 'search' parameter the 'country' parameter is also required.";
	
	QueryStringBuilder queryBuilder;
	RequestHelper requestHelper;
	
	
	public OnDemandService(RequestHandler handler) {
		super(handler);
	}

	/**
	 * GET /ondemand
	 * 
	 * Call to /ondemand endpoint - retrieve on demand items.
	 *  
	 * @param rpuids - a comma separated list of rpuid Integers. Is not used in conjunction with the search parameter.
	 * @param search - a String search term - requires a country code and can not be used in conjunction the the rpuids param.
	 * @param country - used only when a search parameter is supplied. Limits results by country.
	 * @return A List of OnDemand items
	 * @throws IOException
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws InvalidArgumentsException
	 */
	public List<OnDemand> getOnDemands(OnDemandParam params) throws JsonParseException, JsonMappingException, IOException, InvalidArgumentsException {

		queryBuilder = new QueryStringBuilder();
		requestHelper = new RequestHelper(requestHandler);
		
		if (params.getSearchTerm() != null && (params.getCountry() == null || params.getCountry().length() < 3)) {
			throw new InvalidArgumentsException(SEARCH_AND_COUNTRY_REQUIRED);
		}
		
		String onDemandUri = Constants.ONDEMAND + buildOnDemandForRpUidsQueryString(params);
		showDebug(logger, onDemandUri);
		
		WrapiResult results = requestHelper.performGetRequest(onDemandUri);
		
		return generateOnDemands(results);
	}
	
	
	/**
	 * GET /ondemand/{odIds}
	 * 
	 * Call to /ondemand/{odIds} endpoint - retrieve on demand items.
	 *  
	 * @param odIds - a comma separated list of odId Integers.
	 * @return A List of OnDemand items
	 * @throws IOException
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 */
	public List<OnDemand> getOnDemandsForOnDemandIds(String[] odIds) throws JsonParseException, JsonMappingException, IOException {
		
		queryBuilder = new QueryStringBuilder();
		requestHelper = new RequestHelper(requestHandler);
		
		String onDemandUri = Constants.ONDEMAND_WITH_PARAMS + buildOnDemandForOdIdsQueryString(odIds);
		showDebug(logger, onDemandUri);
		
		WrapiResult results = requestHelper.performGetRequest(onDemandUri);
		
		return generateOnDemandsByOnDemandsId(results);
	}
	
	
	public String buildOnDemandForOdIdsQueryString(String[] odIds) {
		
		queryBuilder = new QueryStringBuilder();
		
		String onDemandQueryString = "";
		onDemandQueryString += String.join(",", odIds);
		onDemandQueryString += queryBuilder.toString();
		
		return UriUtils.encode(onDemandQueryString, "UTF-8");
	}
	
	
	
	private static List<OnDemand> generateOnDemands(WrapiResult results) {
		
		if (results == null) {
			List<OnDemand> emptyList = new ArrayList<OnDemand>();
			return emptyList;
		}

		List<OnDemand> onDemands = new ArrayList<OnDemand>();
		
		for (int i = 0; i<results.getData().length; i++) {
			
			Data data = results.getData()[i];
			OnDemand onDemand = new OnDemand();
			
			onDemand.setName(data.getName());
			onDemand.setCountry(data.getCountry());
			onDemand.setDescription(data.getCountry());
			onDemand.setId(data.getId());
			onDemand.setMultimedia(data.getMultimedia());
			onDemand.setName(data.getName());
			onDemand.setOnDemandStreams(data.getOnDemandStreams());
			onDemand.setSong(data.getSong());
			onDemand.setShow(data.getShow());
			
			onDemands.add(onDemand);
		}
		
		return onDemands;
		
	}
	
	
	private static List<OnDemand> generateOnDemandsByOnDemandsId(WrapiResult results) {
		
		if (results == null) {
			List<OnDemand> emptyList = new ArrayList<OnDemand>();
			return emptyList;
		}

		List<OnDemand> onDemands = new ArrayList<OnDemand>();
		
		for (int i = 0; i<results.getData().length; i++) {
			
			InnerData data = results.getData()[i].getInnerdata()[0];
			OnDemand onDemand = new OnDemand();
			
			onDemand.setName(data.getName());
			onDemand.setCountry(data.getCountry());
			onDemand.setDescription(data.getCountry());
			onDemand.setId(data.getId());
			onDemand.setMultimedia(data.getMultimedia());
			onDemand.setName(data.getName());
			onDemand.setOnDemandStreams(data.getOnDemandStreams());
			onDemand.setSong(data.getSong());
			onDemand.setShow(data.getShow());
			
			onDemands.add(onDemand);
		}
		
		return onDemands;
		
	}
	

	public String buildOnDemandForRpUidsQueryString(OnDemandParam params) {
		
		String stationsQueryString = "";
		
		if (params.getCountry() != null) {
			queryBuilder.appendParam("country", params.getCountry());
		}
		
		if (params.getSearchTerm() != null && !params.getSearchTerm().isEmpty()) {
			queryBuilder.appendParam("search", params.getSearchTerm());
		}
		
		if (params.getRpUids() != null) {
			String rpUidList = String.join(",", params.getRpUids());
			if (rpUidList.length() > 0) {
				queryBuilder.appendParam("rpuids", params.getRpUids());
			}
		}
		
		stationsQueryString += queryBuilder.toString();
		
		return stationsQueryString;
	}
	
}
