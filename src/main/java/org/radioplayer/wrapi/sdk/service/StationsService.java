package org.radioplayer.wrapi.sdk.service;

import static org.radioplayer.wrapi.sdk.Constants.BEARER_ID;
import static org.radioplayer.wrapi.sdk.Constants.COUNTRY;
import static org.radioplayer.wrapi.sdk.Constants.FROM;
import static org.radioplayer.wrapi.sdk.Constants.GEO;
import static org.radioplayer.wrapi.sdk.Constants.NEXT;
import static org.radioplayer.wrapi.sdk.Constants.ONAIR;
import static org.radioplayer.wrapi.sdk.Constants.PAGE;
import static org.radioplayer.wrapi.sdk.Constants.SCHEDULE;
import static org.radioplayer.wrapi.sdk.Constants.SEARCH;
import static org.radioplayer.wrapi.sdk.Constants.SIZE;
import static org.radioplayer.wrapi.sdk.Constants.STATIONS;
import static org.radioplayer.wrapi.sdk.Constants.STATIONS_WITH_PARAMS;
import static org.radioplayer.wrapi.sdk.Constants.TO;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.radioplayer.wrapi.sdk.StationFilter;
import org.radioplayer.wrapi.sdk.WrapiResult;
import org.radioplayer.wrapi.sdk.domain.Data;
import org.radioplayer.wrapi.sdk.domain.Location;
import org.radioplayer.wrapi.sdk.domain.OnAir;
import org.radioplayer.wrapi.sdk.domain.Schedule;
import org.radioplayer.wrapi.sdk.domain.Show;
import org.radioplayer.wrapi.sdk.domain.Song;
import org.radioplayer.wrapi.sdk.domain.Station;
import org.radioplayer.wrapi.sdk.domain.StationParam;
import org.radioplayer.wrapi.sdk.exception.InvalidArgumentsException;
import org.radioplayer.wrapi.sdk.request.QueryStringBuilder;
import org.radioplayer.wrapi.sdk.request.RequestHandler;
import org.radioplayer.wrapi.sdk.request.RequestHelper;
import org.radioplayer.wrapi.sdk.request.RetrieveParams;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;


public class StationsService extends WRAPIService {
	
	private static final String INVALID_SCHEDULE_RANGE = "Must have both From and To params to query for a schedule";

	private static final String FIVE_RPUID_LIMIT = "This call has a limit of 5 rpUids as input parameters";
	
	private static final String SEARCH_COUNTRY_REQUIRED = "When using the 'search' term parameter a 'country' parameter is required.";
	
	private static final String GEO_COUNTRY_REQUIRED = "When using the 'geo' parameter a 'country' parameter is required.";
	
	private Logger logger = LogManager.getLogger(getClass());
	
	QueryStringBuilder queryBuilder;
	RequestHelper requestHelper;
	
	public StationsService(RequestHandler handler) {
		super(handler);
	}

	/**
	 * GET /stations
	 * 
	 * Call to /stations endpoint - retrieve information about multiple, filtered stations.
	 *  
	 * @param filter queries
	 * @param params which data to get from the WRAPI
	 * @return A List of Stations
	 * @throws IOException
	 * @throws InvalidArgumentsException
	 */
	public List<Station> getStations(StationFilter filter, RetrieveParams params) throws IOException, InvalidArgumentsException {
		
		if (filter.getSearchTerm() != null && filter.getCountry() < 1) {
			throw new InvalidArgumentsException(SEARCH_COUNTRY_REQUIRED);
		}
		
		if (filter.getGeoLocation() != null && filter.getCountry() < 1) {
			throw new InvalidArgumentsException(GEO_COUNTRY_REQUIRED);
		}
		
		requestHelper = new RequestHelper(requestHandler);
		
		String stationsUri = STATIONS + buildStationsQueryString(filter, params);
		showDebug(logger, stationsUri);
		
		WrapiResult results = requestHelper.performGetRequest(stationsUri);
		
		return generateStations(results);
	}


	/**
	 * GET /stations/{rpuids}
	 * 
	 * Retrieve information about a particular, specified station
	 * 
	 * @param rpUids Array of stations to retrieve data for
	 * @param params The data to retrieve
	 * @return A List of populated Stations
	 * 
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public List<Station> getStationsByRpUids(String[] rpUids, RetrieveParams params) throws JsonParseException, JsonMappingException, IOException {
		
		requestHelper = new RequestHelper(requestHandler);
		
		String stationsUri = STATIONS_WITH_PARAMS + buildStationsByRpUidsQueryString(rpUids, params);
		String stationsQS = buildStationsByRpUidsQueryString(rpUids, params);
		showDebug(logger, "Query string: " + stationsQS);
		
		WrapiResult results = requestHelper.performGetRequest(stationsUri);
		
		return generateStations(results);
	}
	
	/**
	 * GET stations/{rpuids}/onair
	 * Retrieve on air, now-playing events, such as programs or songs.
	 * 
	 * @param rpUids Array of Strings to get information on
	 * @param next A boolean to indicate if you would like your response to include information about the next scheduled events, as well as those currently on air
	 * @return A List of OnAir information
	 * 
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public List<OnAir> getStationsByRpUidsOnAir(String[] rpUids, boolean next) throws JsonParseException, JsonMappingException, IOException {
		
		requestHelper = new RequestHelper(requestHandler);

		String stationsUri = STATIONS_WITH_PARAMS + buildStationsByRpUidsOnAirQueryString(rpUids, next);
		
		showDebug(logger, stationsUri);
		
		WrapiResult results = requestHelper.performGetRequest(stationsUri);
		
		return generateOnAirStations(results);
		
	}
	
	
	/**
	 * GET stations/{rpuids}/schedule
	 * 
	 * Retrieve a station or stations' schedules
	 * 
	 * @param params Schedule information to retrieve
	 * 
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 * @throws InvalidArgumentsException
	 */
	public List<Schedule> getStationScheduleByRpUids(StationParam params) throws JsonParseException, JsonMappingException, IOException, InvalidArgumentsException {
	
		
		int rpUidCount = params.getRpUids().length;
		if (rpUidCount > 5) {
			throw new InvalidArgumentsException(FIVE_RPUID_LIMIT);
		}
		
		requestHelper = new RequestHelper(requestHandler);
		
		String stationsUri = STATIONS_WITH_PARAMS + buildStationSchedulesByRpUidsQueryString(params);
		showDebug(logger, "Query string: " + stationsUri);
		
		WrapiResult results = requestHelper.performGetRequest(stationsUri);
		
		return generateStationSchedules(results);
	}
	
	
	public String buildStationSchedulesByRpUidsQueryString(StationParam params) throws InvalidArgumentsException {

		if(params.getFrom()!=null && params.getTo() == null || 
				params.getTo()!=null && params.getFrom() == null) {
			throw new InvalidArgumentsException(INVALID_SCHEDULE_RANGE);
		}
		QueryStringBuilder queryBuilder = new QueryStringBuilder();
		
		String stationsQueryString = "";
		
		String rpUidList = "";
		if (params.getRpUids() != null) {
			rpUidList = String.join(",", params.getRpUids());
		}
		
		if (!rpUidList.isEmpty()) {
			stationsQueryString += rpUidList + SCHEDULE;
		}
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ"); // ISO 8601 format
		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));

		if (params.getFrom() != null) {
			queryBuilder.appendParam(FROM, sdf.format(params.getFrom()));
		}
		
		if (params.getTo() !=null) {
			queryBuilder.appendParam(TO, sdf.format(params.getTo()));
		}
	
		if (params.getPage() > -1) {
			queryBuilder.appendParam(PAGE, params.getPage());
		}
		
		if (params.getSize() > -1) {
			queryBuilder.appendParam(SIZE, params.getSize());
		}
		
		stationsQueryString += queryBuilder.toString();
		
		return stationsQueryString;
	}
	

	public String buildStationsQueryString(StationFilter filter, RetrieveParams params) {
		
		QueryStringBuilder queryBuilder = new QueryStringBuilder();
		
		String stationsQueryString = "";
		
		if (filter.getCountry() > 0) {
			queryBuilder.appendParam(COUNTRY, filter.getCountry());
		}
		
		if (filter.getBearerId() != null) {
			queryBuilder.appendParam(BEARER_ID, filter.getBearerId());
		}
		
		if (filter.getSearchTerm() != null) {
			queryBuilder.appendParam(SEARCH, filter.getSearchTerm());
		}
		
		if (filter.getGeoLocation() != null) {
			queryBuilder.appendParam(GEO, filter.getGeoLocation());
		}
		
		if(params.hasParams()) {
			queryBuilder.appendParam(params.getParamName(), params.getRetrieveParams());
		}
		stationsQueryString = queryBuilder.toString();
		
		return stationsQueryString;
	}	
	
	
	public String buildStationsByRpUidsQueryString(String[] rpUids, RetrieveParams params) {
		
		QueryStringBuilder queryBuilder = new QueryStringBuilder();
		
		String stationsQueryString = "";
		String rpUidList = String.join(",", rpUids);
		
		if (!rpUidList.isEmpty()) {
			stationsQueryString += rpUidList;
		}
		
		if(params.hasParams()) {
			queryBuilder.appendParam(params.getParamName(), params.getRetrieveParams());
		}
		
		stationsQueryString += queryBuilder.toString();
		
		return stationsQueryString;
	}
	
	
	public String buildStationsByRpUidsOnAirQueryString(String[] rpUids, boolean next) {
		
		QueryStringBuilder queryBuilder = new QueryStringBuilder();
		
		String stationsQueryString = "";
		String rpUidList = String.join(",", rpUids);
		
		if (!rpUidList.isEmpty()) {
			stationsQueryString += rpUidList + ONAIR;
		}
		
		String nextValue = "false";
		if (next) {
			nextValue = "true";
		}
		queryBuilder.appendParam(NEXT, nextValue);
		
		stationsQueryString += queryBuilder.toString();
		
		return stationsQueryString;
	}
	
	
	public static List<Station> generateStations(WrapiResult results) {
		
		if (results == null) {
			List<Station> emptyList = new ArrayList<Station>();
			return emptyList;
		}
		
		List<Station> stations = new ArrayList<Station>();
		
		for (int i=0; i<results.getData().length; i++) {
			
			Data data = results.getData()[i];
			Station station = (Station) data;
			stations.add(station);
		}
		
		return stations;
	}
	
	
	public static List<Schedule> generateStationSchedules(WrapiResult results) {
		
		if (results == null) {
			List<Schedule> emptyList = new ArrayList<Schedule>();
			return emptyList;
		}
		
		List<Schedule> schedules = new ArrayList<Schedule>();
		
		for (int i=0; i<results.getData().length; i++) {
			
			Data data = results.getData()[i];
			
			for (int ii=0; ii<data.getInnerdata().length; ii++) {
				
				Schedule schedule = new Schedule();
				schedule.setName(data.getInnerdata()[ii].getName());
				schedule.setDescription(data.getInnerdata()[ii].getDescription());
				schedule.setMediaCredits(data.getInnerdata()[ii].getMediaCredits());
				Location[] locations = data.getInnerdata()[ii].getLocations();
				if(locations !=null && locations.length > 0) {
					schedule.setScheduledStart(locations[0].getScheduledStart());
					schedule.setScheduledStop(locations[0].getScheduledStop());
				}
				schedules.add(schedule);
				
			}
			
		}
		
		return schedules;
	}

	
	public static List<OnAir> generateOnAirStations(WrapiResult results) {
		
		if (results == null) {
			List<OnAir> emptyList = new ArrayList<OnAir>();
			return emptyList;
		}
		
		List<OnAir> onAirs = new ArrayList<OnAir>();
		
		for (int i=0; i<results.getData().length; i++) {
			Data data = results.getData()[i];
			
			for (int ii=0; ii<data.getInnerdata().length; ii++) {
				OnAir onAir = new OnAir();
				Show show = data.getInnerdata()[ii].getShow();
				onAir.setShow(show);
				if(data.getInnerdata()[ii].getSong() != null && 
						data.getInnerdata()[ii].getSong().getName() != null ) {
					Song song = data.getInnerdata()[ii].getSong();
					onAir.setSong(song);
				}
				onAirs.add(onAir);
			}
		}
		
		return onAirs;
	}
	
	
}
