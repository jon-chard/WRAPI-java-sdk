package org.radioplayer.wrapi.sdk;

import java.io.IOException;

import org.radioplayer.wrapi.sdk.request.RequestHandler;
import org.radioplayer.wrapi.sdk.service.CategoriesService;
import org.radioplayer.wrapi.sdk.service.OnDemandService;
import org.radioplayer.wrapi.sdk.service.RecommendationsService;
import org.radioplayer.wrapi.sdk.service.StationsService;


public class WrapiConnector {
	
	StationsService stationsService;
	
	OnDemandService onDemandService;

	RecommendationsService recommendationService;
	
	CategoriesService categoriesService;
	
	WrapiConnector(String pathToPem, String apiKey) throws IOException {
		
		RequestHandler requestHandler = new RequestHandler(pathToPem, apiKey);
		
		stationsService = new StationsService(requestHandler);
		onDemandService = new OnDemandService(requestHandler);
		recommendationService = new RecommendationsService(requestHandler);
		categoriesService = new CategoriesService(requestHandler);
	}

	public StationsService getStationsService() {
		return stationsService;
	}


	public void setStationsService(StationsService stationsService) {
		this.stationsService = stationsService;
	}


	public OnDemandService getOnDemandService() {
		return onDemandService;
	}


	public void setOnDemandService(OnDemandService onDemandService) {
	}


	public RecommendationsService getRecommendationService() {
		return recommendationService;
	}


	public void setRecommendationService(RecommendationsService recommendationService) {
		this.recommendationService = recommendationService;
	}


	public CategoriesService getCategoriesService() {
		return categoriesService;
	}


	public void setCategoriesService(CategoriesService categoriesService) {
		this.categoriesService = categoriesService;
	}
	
}
