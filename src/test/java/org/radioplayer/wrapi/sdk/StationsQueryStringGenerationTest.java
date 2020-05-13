package org.radioplayer.wrapi.sdk;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.radioplayer.wrapi.sdk.request.RetrieveParams;
import org.radioplayer.wrapi.sdk.service.StationsService;


public class StationsQueryStringGenerationTest {

	private static final String SEARCH_TERM_1 = "bbc";
	
	@Test
	public void testGetAllStationsCountryFilterApplied() {

		// TODO in service - Only one filter allowed at a time

		StationsService stationsService = new StationsService(null);

		StationFilter filter = new StationFilter();
		filter.setCountry(826);

		RetrieveParams params = new RetrieveParams();
		params.getIds(false);
		params.getDetail(false);

		String stationsUri = Constants.STATIONS
				+ stationsService.buildStationsQueryString(filter, params);

		assertEquals("http://api.radioplayer.org/v2/stations?country=826", stationsUri);

	}

	@Test
	public void testGetAllStationsSearchTermFilterApplied() {
		
		StationsService stationsService = new StationsService(null);

		StationFilter filter = new StationFilter();
		filter.setSearchTerm(SEARCH_TERM_1);

		RetrieveParams params = new RetrieveParams();
		params.getIds(false);
		params.getDetail(false);

		String stationsUri = Constants.STATIONS
				+ stationsService.buildStationsQueryString(filter, params);

		assertEquals("http://api.radioplayer.org/v2/stations?search=bbc", stationsUri);

	}

	@Test
	public void testGetAllStationsLimitResponseByRetrieveParam() {

		StationsService stationsService = new StationsService(null);

		StationFilter filter = new StationFilter();
		filter.setSearchTerm(SEARCH_TERM_1);

		RetrieveParams params = new RetrieveParams();
		params.getIds(true);
		params.getDetail(true);

		String stationsUri = Constants.STATIONS
				+ stationsService.buildStationsQueryString(filter, params);

		assertEquals("http://api.radioplayer.org/v2/stations?search=bbc&include=ids%2Cdetail", stationsUri);
		
	}

}
