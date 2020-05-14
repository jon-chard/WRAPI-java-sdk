package org.radioplayer.wrapi.sdk.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.radioplayer.wrapi.sdk.Constants;
import org.radioplayer.wrapi.sdk.WrapiResult;
import org.radioplayer.wrapi.sdk.domain.ArtistPlayCounts;
import org.radioplayer.wrapi.sdk.domain.Data;
import org.radioplayer.wrapi.sdk.domain.Recommendation;
import org.radioplayer.wrapi.sdk.domain.RecommendationFactors;
import org.radioplayer.wrapi.sdk.request.QueryStringBuilder;
import org.radioplayer.wrapi.sdk.request.RecommendationParam;
import org.radioplayer.wrapi.sdk.request.RequestHandler;
import org.radioplayer.wrapi.sdk.request.RequestHelper;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class RecommendationsService extends WRAPIService {
	
	private Logger logger = LogManager.getLogger(getClass());
	
	QueryStringBuilder queryBuilder;
	RequestHelper requestHelper;
	
	public RecommendationsService(RequestHandler handler) {
		super(handler);
	}

	/**
	 * POST /recommendations
	 * 
	 * Call to /recommendations endpoint - retrieve recommendations and optionally on demand items. 
	 *  
	 * @param country - 3 digit numeric country code
	 * @param factors - an array of String - available options are GEO, TRENDING, AFFINITY and MUSICMATCH
	 * @param rpuid - a String of a rpuid
	 * @param latitude - a double - tailors returned recommendations when the GEO factor parameter is applied. 
	 * @param longitude - a double - tailors returned recommendations when the GEO factor parameter is applied.
	 * @param artistPlayCounts - an array of objects each containing an artistName String and a corresponding playCount Integer. Applied by setting enabling the MUSICMATCH factor parameter.
	 * @param facebookArtists - an array of artist names - used in conjunction with the MUSICMATCH factor parameter.
	 * @param - onDemand - boolean - when true; on demand items will be included in the response. Default if false.  
	 * @return A List of Recommendation results
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public List<Recommendation> getRecommendations(RecommendationFactors factors, RecommendationParam params) throws JsonParseException, JsonMappingException, IOException {
		
		queryBuilder = new QueryStringBuilder();
		requestHelper = new RequestHelper(requestHandler);
		
		ObjectNode jsonArgs = generateBodyArguments(factors, params);
		
		String uri = Constants.RECOMMENDATIONS + buildRecommendationsQueryString(params);
		showDebug(logger, "Recommendations URL: " + uri);
		
		WrapiResult results = requestHelper.performPostRequest(uri, jsonArgs);
		
		return generateRecommendations(results);
		
	}

	
	private ObjectNode generateBodyArguments(RecommendationFactors factors, RecommendationParam params) throws UnsupportedEncodingException, JsonProcessingException {
		
		ObjectMapper om = new ObjectMapper();
		ObjectNode jsonNode = om.createObjectNode();
		
		ArrayNode factorsList = jsonNode.putArray("factors");
		for(String factor : factors.toArray()) {
			factorsList.add(factor);
		}

		if (factors.isMusicMatch()) {
			if ( params.getFacebookArtists() !=null && params.getFacebookArtists().length > 0) {
				
				ArrayNode fbArtists = jsonNode.putArray("facebookArtists");
				for(String artist : params.getFacebookArtists()) {
					fbArtists.add(artist);
				}
			}
			
			if (params.getArtistPlayCounts() != null && params.getArtistPlayCounts().length > 0) {
				
				ArrayNode playCounts = jsonNode.putArray("artistPlayCounts");
				for( ArtistPlayCounts apc : params.getArtistPlayCounts()) {
					playCounts.add(apc.toJson());
				}
			}
		}

		if (params.isOnDemand() == true) {
			jsonNode.put("onDemand", true);
		}
		
		if (factors.isAffinity() && StringUtils.hasText(params.getRpuid())) {
			jsonNode.put("rpuid", params.getRpuid());
		}
		
		if(factors.isGeo()) {
			if (params.getLatitude() != null) {
				jsonNode.put("latitude", params.getLatitude());
			}
			
			if (params.getLongitude()!=null) {
				jsonNode.put("longitude", params.getLongitude());
			}
		}
		
		return jsonNode;
	}
	
	
	public String buildRecommendationsQueryString(RecommendationParam params) {
		
		queryBuilder = new QueryStringBuilder();
		requestHelper = new RequestHelper(requestHandler);
		
		String stationsQueryString = "";
		
		if (params.getCountry() != null && params.getCountry().length() == 3) {
			queryBuilder.appendParam("country", params.getCountry());
		}
		
		stationsQueryString = queryBuilder.toString();
		
		return stationsQueryString;
	}
	

	private static List<Recommendation> generateRecommendations(WrapiResult results) {
		
		if (results == null) {
			List<Recommendation> emptyList = new ArrayList<Recommendation>();
			return emptyList;
		}
		
		List<Recommendation> recommendations = new ArrayList<Recommendation>();
		
		for (int i=0; i<results.getData().length; i++) {
			
			Data data = results.getData()[i];
			Recommendation rec = new Recommendation();
			rec.setFactors(data.getFactors());
			rec.setType(data.getType());
			
			rec.setAlphanumericKey(data.getAlphanumericKey());
			rec.setBearers(data.getBearers());
			rec.setDescription(data.getDescription());
			rec.setEpgLanguages(data.getEpgLanguages());
			rec.setFactors(data.getFactors());
			rec.setHref(data.getHref());
			rec.setId(data.getId());
			rec.setInnerdata(data.getInnerdata());
			rec.setLiveStreams(data.getLiveStreams());
			rec.setLocation(data.getLocation());
			rec.setMeta(data.getMeta());
			rec.setMultimedia(data.getMultimedia());
			rec.setName(data.getName());
			rec.setOnDemandStreams(data.getOnDemandStreams());
			rec.setPhoneticInputs(data.getPhoneticInputs());
			rec.setPhoneticOutputs(data.getPhoneticOutputs());
			rec.setRpuid(data.getRpuid());
			rec.setSeries(data.getSeries());
			rec.setShow(data.getShow());
			rec.setSocialIds(data.getSocialIds());
			rec.setSong(data.getSong());
			rec.setType(data.getType());
			
			recommendations.add(rec);
		}
		
		return recommendations;
	}
	
	
}
