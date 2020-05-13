package org.radioplayer.wrapi.sdk;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.radioplayer.wrapi.sdk.request.RecommendationParam;
import org.radioplayer.wrapi.sdk.service.RecommendationsService;


public class RecommendationsQueryStringGenerationTest {

	@Test
	public void testCountryParamApplied() {
		
		RecommendationsService recommendationsService = new RecommendationsService(null);
		
		String rpUid = "826354";
		String countryCode = "826"; // 826 = UK, Spain = 724
		
		RecommendationParam params = new RecommendationParam();
		params.setCountry(countryCode);
		params.setRpuid(rpUid);
		
		String queryString = Constants.RECOMMENDATIONS + recommendationsService.buildRecommendationsQueryString(params);
		
		assertEquals("http://api.radioplayer.org/v2/recommendations?country=826", queryString);
		
	}
	
}
