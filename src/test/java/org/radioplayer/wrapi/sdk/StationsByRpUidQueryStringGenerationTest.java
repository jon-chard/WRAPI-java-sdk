package org.radioplayer.wrapi.sdk;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.radioplayer.wrapi.sdk.request.RetrieveParams;
import org.radioplayer.wrapi.sdk.service.StationsService;


public class StationsByRpUidQueryStringGenerationTest {
	
	@Test
	public void testGetStationsByRpUidForMultipleIdsAndParams() {
		
		String[] rpUids = {"826100","826101","826102","826103"};
		
		RetrieveParams params = new RetrieveParams();
		params.getIds(true);
		params.getDetail(true);
		
		StationsService stationsService = new StationsService(null);
		
		String stationsUri = Constants.STATIONS_WITH_PARAMS
				+ stationsService.buildStationsByRpUidsQueryString(rpUids, params);

		assertEquals("http://api.radioplayer.org/v2/stations/826100,826101,826102,826103?include=ids%2Cdetail", stationsUri);
		
	}
	
	@Test
	public void testGetStationsByRpUidForMultipleIdsNoParams() {
		
		String[] rpUids = {"826100","826101","826102","826103"};
		
		RetrieveParams params = new RetrieveParams();
		params.getIds(false);
		params.getDetail(false);
		
		StationsService stationsService = new StationsService(null);

		String stationsUri = Constants.STATIONS_WITH_PARAMS
				+ stationsService.buildStationsByRpUidsQueryString(rpUids, params);

		assertEquals("http://api.radioplayer.org/v2/stations/826100,826101,826102,826103", stationsUri);
		
	}

}
