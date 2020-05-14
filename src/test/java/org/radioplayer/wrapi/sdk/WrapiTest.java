package org.radioplayer.wrapi.sdk;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.radioplayer.wrapi.sdk.domain.ArtistPlayCounts;
import org.radioplayer.wrapi.sdk.domain.Category;
import org.radioplayer.wrapi.sdk.domain.OnAir;
import org.radioplayer.wrapi.sdk.domain.OnDemand;
import org.radioplayer.wrapi.sdk.domain.OnDemandParam;
import org.radioplayer.wrapi.sdk.domain.Recommendation;
import org.radioplayer.wrapi.sdk.domain.RecommendationFactors;
import org.radioplayer.wrapi.sdk.domain.Schedule;
import org.radioplayer.wrapi.sdk.domain.Station;
import org.radioplayer.wrapi.sdk.domain.StationParam;
import org.radioplayer.wrapi.sdk.exception.InvalidArgumentsException;
import org.radioplayer.wrapi.sdk.request.RecommendationParam;
import org.radioplayer.wrapi.sdk.request.RetrieveParams;

public class WrapiTest  {

	private Logger logger = LogManager.getLogger(getClass());
	private static WrapiConnector wrapi;

	@BeforeAll
	public static void createWRAPI() throws IOException {
		Properties prop = new Properties();
		InputStream in = WrapiTest.class.getResourceAsStream("/test-application.properties");
		prop.load(in);
		in.close();
		
		String pemFileWithPath = prop.getProperty("pemfile.path");
		String key = getKeyFromFileAndPath(pemFileWithPath);
		
		wrapi = WrapiFactory.getConnector(pemFileWithPath, key);

	}

	@Test
	public void testGetAllStations() throws IOException, InvalidArgumentsException {

		StationFilter filter = new StationFilter();
		filter.setCountry(826);
		filter.setSearchTerm("90s");

		RetrieveParams params = new RetrieveParams();
		params.getIds(true);
		params.getDetail(true);
		params.getImages(true);
		params.getStreams(true);
		params.getBearers(false);

		List<Station> allStations = wrapi.getStationsService().getStations(filter, params);

		viewResults(allStations);

		assertNotNull(allStations);

	}

	@Test
	public void testGetStationsByRpids() throws IOException {

		String[] rpUids = { "8261004", "826101", "826102", "826103" };

		RetrieveParams params = new RetrieveParams();
		params.getIds(true);
		params.getDetail(true);

		List<Station> stations = wrapi.getStationsService().getStationsByRpUids(rpUids, params);

		viewResults(stations);

		assertNotNull(stations);

	}

	@Test
	public void testGetStationsByRpUidsOnAir() throws IOException {

		String[] rpUids = { "8261004", "826100" }; // "826102","826103"
		boolean next = true;

		List<OnAir> stations = wrapi.getStationsService().getStationsByRpUidsOnAir(rpUids, next);

		viewOnAirResults(stations);

		assertNotNull(stations);
	}

	@Test
	public void testGetStationSchedulesByRpUids()
			throws IOException, InvalidArgumentsException {

		String[] rpUids = { "8261004","826100" }; // ,"826102","826103"

		Date now = new Date(System.currentTimeMillis());
		Calendar yesterday = Calendar.getInstance();
		yesterday.setTime(now);
		yesterday.add(Calendar.DATE, -1);
		
		Calendar tomorrow = Calendar.getInstance();
		tomorrow.setTime(now);
		tomorrow.add(Calendar.DATE, 1);

		Integer page = 1;
		Integer size = 7;

		StationParam params = new StationParam();
		params.setPage(page);
		params.setSize(size);
		params.setRpUids(rpUids);
		params.setFrom(yesterday.getTime());
		params.setTo(tomorrow.getTime());

		List<Schedule> schedules = wrapi.getStationsService().getStationScheduleByRpUids(params);

		viewStationScheduleResults(schedules);

		assertNotNull(schedules);

	}

	@Test
	public void testGetOnDemands() throws IOException, InvalidArgumentsException {

		String countryCode = "826";
		String searchTerm = "Foo Fighters";

		OnDemandParam params = new OnDemandParam();
		params.setCountry(countryCode);
		params.setSearchTerm(searchTerm);
		
		List<OnDemand> onDemands = wrapi.getOnDemandService().getOnDemands(params);

		viewOnDemandResults(onDemands);

		assertNotNull(onDemands);

	}

	@Test
	public void testGetOnDemandsForOdIds() throws IOException {

		String[] odIds = { "826342:odp:/crid://www.bbc.co.uk/programmes/p01hgn64", "826343:sp:/crid://www.bbc.co.uk/programmes/b048nh2y" };

		List<OnDemand> onDemands = wrapi.getOnDemandService().getOnDemandsForOnDemandIds(odIds);

		viewOnDemandResults(onDemands);

		assertNotNull(onDemands);

	}

	@Test
	public void testGetCategories() throws IOException {

		String type = "live";
		Integer countryCode = 826;

		List<Category> categories = wrapi.getCategoriesService().getCategories(type, countryCode);

		viewCategoryResults(categories);

		assertNotNull(categories);

	}

	@Test
	public void testGetRecommendations() throws IOException {

		String rpUid = "8261187"; // An rpuid that would be used to get affinity
		String countryCode = "826"; // 826 = UK, Spain = 724, Germany = 276

		String[] facebookArtists = { "Dua Lipa" };

		ArtistPlayCounts artist1 = new ArtistPlayCounts();
		artist1.setArtistName("Mabel");
		artist1.setPlayCount(15);

		ArtistPlayCounts artist2 = new ArtistPlayCounts();
		artist2.setArtistName("Camila Cabello");
		artist2.setPlayCount(25);

		ArtistPlayCounts[] artistArr = { artist1, artist2 };

		RecommendationFactors factors = new RecommendationFactors();
		factors.setAffinity(true);
		factors.setGeo(true);
		factors.setTrending(true);
		factors.setMusicMatch(true);

		RecommendationParam params = new RecommendationParam();
		params.setCountry(countryCode);
		params.setRpuid(rpUid);
		params.setLatitude(51.509); // LatLong for London
		params.setLongitude(-0.118);
		params.setOnDemand(true);
		params.setArtistPlayCounts(artistArr);
		params.setFacebookArtists(facebookArtists);

		List<Recommendation> recommendations = wrapi.getRecommendationService().getRecommendations(factors, params);

		viewRecommendationResults(recommendations);

		assertNotNull(recommendations);
	}

	public void viewResults(List<Station> stations) {
		if (logger.isDebugEnabled()) {
			logger.debug("List size: " + stations.size());

			for (Station station : stations) {
				logger.debug("Station name: " + station.getName() + " ID: " + station.getRpuid());
			}
		}
	}

	public void viewStationScheduleResults(List<Schedule> schedules) {
		if (logger.isDebugEnabled()) {
			logger.debug("List size: " + schedules.size());

			for (Schedule schedule : schedules) {
				logger.debug("Show name: " + schedule.getName() + " Description: " + schedule.getDescription() + " starting at " + schedule.getScheduledStartDate());;
			}
		}
	}

	public void viewOnAirResults(List<OnAir> onAirs) {
		if (logger.isDebugEnabled()) {
			logger.debug("List size: " + onAirs.size());

			for (OnAir onAir : onAirs) {
				logger.debug("Show name: " + onAir.getShow().getName() + " -> " + onAir.getShow().getDescription());
				if(onAir.getSong() != null) {
					logger.debug("Now playing " + onAir.getSong().getName() + " by " +  onAir.getSong().getArtist() + " which started playing at " + onAir.getSong().getStartDate().toString());
				}
			}
		}
	}

	public void viewOnDemandResults(List<OnDemand> onDemands) {
		if (logger.isDebugEnabled()) {
			logger.debug("List size: " + onDemands.size());

			for (OnDemand onDemand : onDemands) {
				logger.debug("On Demand name: " + onDemand.getName() + " ID: " + onDemand.getId());
			}
		}
	}

	public void viewCategoryResults(List<Category> categories) {
		if (logger.isDebugEnabled()) {
			logger.debug("List size: " + categories.size());

			for (Category category : categories) {
				logger.debug("Category name: " + category.getName() + " ID: " + category.getHref());
			}
		}
	}

	public void viewRecommendationResults(List<Recommendation> recommendations) {
		if (logger.isDebugEnabled()) {
			logger.debug("List size: " + recommendations.size());

			for (Recommendation recommendation : recommendations) {
				logger.debug("Recommendation: " + recommendation.getName() + " ID: " + recommendation.getRpuid());
			}
		}
	}
	
	private static final String getKeyFromFileAndPath(String filepath) {
		
		String[] units = filepath.split("\\/");
		String filename = units[units.length-1];
		
		String[] filenameUnits = filename.split("\\.");
		String pemKey = filenameUnits[0];
		
		return pemKey;
	}

}
