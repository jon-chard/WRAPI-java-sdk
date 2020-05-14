package org.radioplayer.wrapi.sdk.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.radioplayer.wrapi.sdk.Constants;
import org.radioplayer.wrapi.sdk.WrapiResult;
import org.radioplayer.wrapi.sdk.domain.Category;
import org.radioplayer.wrapi.sdk.domain.Data;
import org.radioplayer.wrapi.sdk.request.QueryStringBuilder;
import org.radioplayer.wrapi.sdk.request.RequestHandler;
import org.radioplayer.wrapi.sdk.request.RequestHelper;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;


public class CategoriesService extends WRAPIService {
	
	private Logger logger = LogManager.getLogger(getClass());
	
	QueryStringBuilder queryBuilder;
	RequestHelper requestHelper;

	public CategoriesService(RequestHandler handler) {
		super(handler);
	}
	
	/**
	 * GET /categories
	 * Call to /categories - returns a list of category names and hrefs for on demand or live data.
	 * 
	 * @param type - String - specifies the type of returned results. Valid options are either 'live' or 'ondemand' 
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public List<Category> getCategories(String type, Integer countryCode) throws JsonParseException, JsonMappingException, IOException {
		
		this.queryBuilder = new QueryStringBuilder();
		this.requestHelper = new RequestHelper(requestHandler);
		
		String onDemandUri = Constants.CATEGORIES + buildCategoriesQueryString(type, countryCode);
		showDebug(logger, "On Demand URL: " + onDemandUri);
				
		WrapiResult results = requestHelper.performGetRequest(onDemandUri);
		
		return generateCategories(results);
	}
	
	
	private String buildCategoriesQueryString(String type, Integer countryCode) {
		
		String stationsQueryString = "";
		queryBuilder.appendParam(Constants.COUNTRY, countryCode);
		queryBuilder.appendParam(Constants.TYPE, type);
		stationsQueryString += queryBuilder.toString();
		
		return stationsQueryString;
	}
	
	
	
	private static List<Category> generateCategories(WrapiResult results) {
		
		if (results == null) {
			List<Category> emptyList = new ArrayList<Category>();
			return emptyList;
		}
		
		List<Category> categories = new ArrayList<Category>();
		
		for (int i = 0; i<results.getData().length; i++) {
			
			Data data = results.getData()[i];
			Category category = new Category();
			
			category.setName(data.getName());
			category.setHref(data.getHref());
			
			categories.add(category);
		}
		
		return categories;
	}
	

}
