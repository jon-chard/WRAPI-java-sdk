package org.radioplayer.wrapi.sdk;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.radioplayer.wrapi.sdk.service.StationsService;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class StationsOnAirQueryStringGenerationTest {

	@Test
	public void testGetStationsOnAir() throws JsonParseException, JsonMappingException, IOException {

		StationsService stationsService = new StationsService(null);

		String[] rpUids = {"826100"}; // ,"826101","826102","826103"
		boolean next = true;

		String stationsUri = Constants.STATIONS_WITH_PARAMS
				+ stationsService.buildStationsByRpUidsOnAirQueryString(rpUids, next);

		assertEquals("http://api.radioplayer.org/v2/stations/826100/onair?next=true", stationsUri);
		
		next = false;
		
		stationsUri = Constants.STATIONS_WITH_PARAMS
				+ stationsService.buildStationsByRpUidsOnAirQueryString(rpUids, next);
		
		assertEquals("http://api.radioplayer.org/v2/stations/826100/onair?next=false", stationsUri);

	}

}
