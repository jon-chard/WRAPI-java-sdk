package org.radioplayer.wrapi.sdk;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.radioplayer.wrapi.sdk.service.OnDemandService;


public class OnDemandsQueryStringGenerationTest {
	
	@Test
	public void testGetOnDemands() {
		
		OnDemandService onDemandService = new OnDemandService(null);
		
		String[] rpUids = {"826354","826101","826102","826103"};
		String queryString = Constants.ONDEMAND_WITH_PARAMS + onDemandService.buildOnDemandForOdIdsQueryString(rpUids);
		
		assertEquals("http://api.radioplayer.org/v2/ondemand/826354%2C826101%2C826102%2C826103", queryString);
		
	}
	
	@Test
	public void testGetOnDemandsForOdids() {
		
		OnDemandService onDemandService = new OnDemandService(null);
		
		String[] odIds = {"1112","2223","3334","4445"};
		String queryString = Constants.ONDEMAND_WITH_PARAMS + onDemandService.buildOnDemandForOdIdsQueryString(odIds);
		
		assertEquals("http://api.radioplayer.org/v2/ondemand/1112%2C2223%2C3334%2C4445", queryString);
		
	}
	
}
