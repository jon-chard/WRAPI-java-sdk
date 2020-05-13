package org.radioplayer.wrapi.sdk;

public class Constants {
	
	private static final String BASE_URL = "http://api.radioplayer.org/v2/";
	
	public static String getBaseUrl() {
		return BASE_URL;
	}

	// API endpoints
	public static final String STATIONS = Constants.getBaseUrl() + "stations";
	public static final String STATIONS_WITH_PARAMS = STATIONS + "/";
	public static final String CATEGORIES = Constants.getBaseUrl() + "categories";
	public static final String ONDEMAND = Constants.getBaseUrl() + "ondemand";
	public static final String ONDEMAND_WITH_PARAMS = ONDEMAND + "/";
	public static final String RECOMMENDATIONS = Constants.getBaseUrl() + "recommendations";
	public static final String RECOMMENDATIONS_WITH_PARAMS = RECOMMENDATIONS + "/";
	public static final String ONAIR = "/onair";
	public static final String SCHEDULE = "/schedule";

	// Parameter names
	public static final String NEXT = "next";
	public static final String GEO = "geo";
	public static final String SEARCH = "search";
	public static final String BEARER_ID = "bearerId";
	public static final String COUNTRY = "country";
	public static final String SIZE = "size";
	public static final String PAGE = "page";
	public static final String TO = "to";
	public static final String FROM = "from";
	public static final String TYPE = "type";
	
}
