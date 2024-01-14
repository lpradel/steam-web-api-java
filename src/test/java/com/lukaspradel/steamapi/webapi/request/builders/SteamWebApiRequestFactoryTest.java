package com.lukaspradel.steamapi.webapi.request.builders;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lukaspradel.steamapi.webapi.core.SteamWebApiInterface;
import com.lukaspradel.steamapi.webapi.core.SteamWebApiInterfaceMethod;
import com.lukaspradel.steamapi.webapi.core.SteamWebApiVersion;
import com.lukaspradel.steamapi.webapi.request.GetAppListRequest;
import com.lukaspradel.steamapi.webapi.request.GetFriendListRequest;
import com.lukaspradel.steamapi.webapi.request.GetFriendListRequest.GetFriendListRequestBuilder;
import com.lukaspradel.steamapi.webapi.request.GetFriendListRequest.Relationship;
import com.lukaspradel.steamapi.webapi.request.GetGlobalAchievementPercentagesForAppRequest;
import com.lukaspradel.steamapi.webapi.request.GetGlobalAchievementPercentagesForAppRequest.GetGlobalAchievementPercentagesForAppRequestBuilder;
import com.lukaspradel.steamapi.webapi.request.GetGlobalStatsForGameRequest;
import com.lukaspradel.steamapi.webapi.request.GetGlobalStatsForGameRequest.GetGlobalStatsForGameRequestBuilder;
import com.lukaspradel.steamapi.webapi.request.GetNewsForAppRequest;
import com.lukaspradel.steamapi.webapi.request.GetNewsForAppRequest.GetNewsForAppRequestBuilder;
import com.lukaspradel.steamapi.webapi.request.GetOwnedGamesRequest;
import com.lukaspradel.steamapi.webapi.request.GetOwnedGamesRequest.GetOwnedGamesRequestServiceParameter;
import com.lukaspradel.steamapi.webapi.request.GetPlayerAchievementsRequest;
import com.lukaspradel.steamapi.webapi.request.GetPlayerAchievementsRequest.GetPlayerAchievementsRequestBuilder;
import com.lukaspradel.steamapi.webapi.request.GetPlayerBansRequest;
import com.lukaspradel.steamapi.webapi.request.GetPlayerBansRequest.GetPlayerBansRequestBuilder;
import com.lukaspradel.steamapi.webapi.request.GetPlayerSummariesRequest;
import com.lukaspradel.steamapi.webapi.request.GetPlayerSummariesRequest.GetPlayerSummariesRequestBuilder;
import com.lukaspradel.steamapi.webapi.request.GetRecentlyPlayedGamesRequest;
import com.lukaspradel.steamapi.webapi.request.GetRecentlyPlayedGamesRequest.GetRecentlyPlayedGamesRequestServiceParameter;
import com.lukaspradel.steamapi.webapi.request.GetSchemaForGameRequest;
import com.lukaspradel.steamapi.webapi.request.GetSchemaForGameRequest.GetSchemaForGameRequestBuilder;
import com.lukaspradel.steamapi.webapi.request.GetUserStatsForGameRequest;
import com.lukaspradel.steamapi.webapi.request.GetUserStatsForGameRequest.GetUserStatsForGameRequestBuilder;
import com.lukaspradel.steamapi.webapi.request.IsPlayingSharedGameRequest;
import com.lukaspradel.steamapi.webapi.request.IsPlayingSharedGameRequest.IsPlayingSharedGameRequestServiceParameter;
import com.lukaspradel.steamapi.webapi.request.ResolveVanityUrlRequest;
import com.lukaspradel.steamapi.webapi.request.dota2.GetFantasyPlayerStatsRequest;
import com.lukaspradel.steamapi.webapi.request.dota2.GetFantasyPlayerStatsRequest.GetFantasyPlayerStatsRequestBuilder;
import com.lukaspradel.steamapi.webapi.request.dota2.GetGameItemsRequest;
import com.lukaspradel.steamapi.webapi.request.dota2.GetHeroesRequest;
import com.lukaspradel.steamapi.webapi.request.dota2.GetHeroesRequest.GetHeroesRequestBuilder;
import com.lukaspradel.steamapi.webapi.request.dota2.GetLeagueListingRequest;
import com.lukaspradel.steamapi.webapi.request.dota2.GetLeagueListingRequest.GetLeagueListingRequestBuilder;
import com.lukaspradel.steamapi.webapi.request.dota2.GetMatchHistoryBySequenceNumRequest;
import com.lukaspradel.steamapi.webapi.request.dota2.GetMatchHistoryBySequenceNumRequest.GetMatchHistoryBySequenceNumRequestBuilder;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;


public class SteamWebApiRequestFactoryTest {

	@Test
	public void testCreateGetNewsForAppRequestOnlyAppId() {

		int appId = 400;

		GetNewsForAppRequest request = SteamWebApiRequestFactory
				.createGetNewsForAppRequest(appId);

		assertNotNull(request);
		assertEquals(request.getApiInterface(),
				SteamWebApiInterface.I_STEAM_NEWS);
		assertEquals(request.getInterfaceMethod(),
				SteamWebApiInterfaceMethod.GET_NEWS_FOR_APP);
		assertEquals(request.getVersion(), SteamWebApiVersion.VERSION_TWO);

		Map<String, String> parameters = request.getParameters();
		assertNotNull(parameters);
		assertEquals(
				parameters
						.get(GetNewsForAppRequestBuilder.REQUEST_PARAM_APP_ID),
				String.valueOf(appId));
		assertNull(parameters
				.get(GetNewsForAppRequestBuilder.REQUEST_PARAM_COUNT));
		assertNull(parameters
				.get(GetNewsForAppRequestBuilder.REQUEST_PARAM_MAX_LENGTH));
	}

	@Test
	public void testCreateGetNewsForAppAllParameters() {

		int appId = 400;
		int count = 100;
		int maxLength = 300;

		GetNewsForAppRequest request = SteamWebApiRequestFactory
				.createGetNewsForAppRequest(appId, count, maxLength);

		assertNotNull(request);
		assertEquals(request.getApiInterface(),
				SteamWebApiInterface.I_STEAM_NEWS);
		assertEquals(request.getInterfaceMethod(),
				SteamWebApiInterfaceMethod.GET_NEWS_FOR_APP);
		assertEquals(request.getVersion(), SteamWebApiVersion.VERSION_TWO);

		Map<String, String> parameters = request.getParameters();
		assertNotNull(parameters);
		assertEquals(
				parameters
						.get(GetNewsForAppRequestBuilder.REQUEST_PARAM_APP_ID),
				String.valueOf(appId));
		assertEquals(
				parameters.get(GetNewsForAppRequestBuilder.REQUEST_PARAM_COUNT),
				String.valueOf(count));
		assertEquals(
				parameters
						.get(GetNewsForAppRequestBuilder.REQUEST_PARAM_MAX_LENGTH),
				String.valueOf(maxLength));
	}

	@Test
	public void testCreateGetGlobalAchievementPercentagesForAppRequest() {

		int gameId = 400;

		GetGlobalAchievementPercentagesForAppRequest request = SteamWebApiRequestFactory
				.createGetGlobalAchievementPercentagesForAppRequest(gameId);

		assertNotNull(request);

		assertEquals(request.getApiInterface(),
				SteamWebApiInterface.I_STEAM_USER_STATS);
		assertEquals(
				request.getInterfaceMethod(),
				SteamWebApiInterfaceMethod.GET_GLOBAL_ACHIEVEMENT_PERCENTAGES_FOR_APP);
		assertEquals(request.getVersion(), SteamWebApiVersion.VERSION_TWO);

		Map<String, String> parameters = request.getParameters();
		assertNotNull(parameters);
		assertEquals(
				parameters
						.get(GetGlobalAchievementPercentagesForAppRequestBuilder.REQUEST_PARAM_GAME_ID),
				String.valueOf(gameId));
	}

	@Test
	public void testCreateGetGlobalStatsForGameRequest() {

		int gameId = 400;
		int count = 1;

		List<String> names = new ArrayList<String>();
		names.add("global.map.emp_isle");

		GetGlobalStatsForGameRequest request = SteamWebApiRequestFactory
				.createGetGlobalStatsForGameRequest(gameId, count, names);

		assertNotNull(request);

		assertEquals(request.getApiInterface(),
				SteamWebApiInterface.I_STEAM_USER_STATS);
		assertEquals(request.getInterfaceMethod(),
				SteamWebApiInterfaceMethod.GET_GLOBAL_STATS_FOR_GAME);
		assertEquals(request.getVersion(), SteamWebApiVersion.VERSION_ONE);

		Map<String, String> parameters = request.getParameters();
		assertNotNull(parameters);
		assertEquals(
				parameters
						.get(GetGlobalStatsForGameRequestBuilder.REQUEST_PARAM_GAME_ID),
				String.valueOf(gameId));
		assertEquals(
				parameters
						.get(GetGlobalStatsForGameRequestBuilder.REQUEST_PARAM_COUNT),
				String.valueOf(count));
		assertEquals(parameters.get("name[0]"),
				String.valueOf("global.map.emp_isle"));
	}

	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testCreateGetGlobalStatsForGameRequestMissingNames() {

		int gameId = 400;
		int count = 0;

		List<String> names = new ArrayList<String>();

		SteamWebApiRequestFactory.createGetGlobalStatsForGameRequest(gameId,
				count, names);
	}

	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testCreateGetGlobalStatsForGameRequestMismatchCountNames() {

		int gameId = 400;
		int count = 2;

		List<String> names = new ArrayList<String>();
		names.add("val1");

		SteamWebApiRequestFactory.createGetGlobalStatsForGameRequest(gameId,
				count, names);
	}

	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testCreateGetPlayerSummariesRequestMissingSteamIds() {

		SteamWebApiRequestFactory.createGetPlayerSummariesRequest(Collections
				.<String> emptyList());
	}

	@Test
	public void testCreateGetPlayerSummariesRequest() {

		List<String> steamIds = new ArrayList<String>();
		steamIds.add("123");
		steamIds.add("456");
		steamIds.add("789");

		GetPlayerSummariesRequest request = SteamWebApiRequestFactory
				.createGetPlayerSummariesRequest(steamIds);

		assertNotNull(request);

		assertEquals(request.getApiInterface(),
				SteamWebApiInterface.I_STEAM_USER);
		assertEquals(request.getInterfaceMethod(),
				SteamWebApiInterfaceMethod.GET_PLAYER_SUMMARIES);
		assertEquals(request.getVersion(), SteamWebApiVersion.VERSION_TWO);

		Map<String, String> parameters = request.getParameters();
		assertNotNull(parameters);
		assertEquals(
				parameters
						.get(GetPlayerSummariesRequestBuilder.REQUEST_PARAM_STEAM_IDS),
				String.valueOf("123,456,789"));
	}

	@Test
	public void testCreateGetFriendListRequestOnlySteamId() {

		GetFriendListRequest request = SteamWebApiRequestFactory
				.createGetFriendListRequest("12345");

		assertNotNull(request);
		assertEquals(request.getApiInterface(),
				SteamWebApiInterface.I_STEAM_USER);
		assertEquals(request.getInterfaceMethod(),
				SteamWebApiInterfaceMethod.GET_FRIEND_LIST);
		assertEquals(request.getVersion(), SteamWebApiVersion.VERSION_ONE);

		Map<String, String> parameters = request.getParameters();
		assertNotNull(parameters);
		assertEquals(
				parameters
						.get(GetFriendListRequestBuilder.REQUEST_PARAM_STEAM_ID),
				String.valueOf("12345"));
	}

	@Test
	public void testCreateGetFriendListRequestAllParameters() {

		// Relationship ALL
		GetFriendListRequest request = SteamWebApiRequestFactory
				.createGetFriendListRequest("12345", Relationship.ALL);

		assertNotNull(request);
		assertEquals(request.getApiInterface(),
				SteamWebApiInterface.I_STEAM_USER);
		assertEquals(request.getInterfaceMethod(),
				SteamWebApiInterfaceMethod.GET_FRIEND_LIST);
		assertEquals(request.getVersion(), SteamWebApiVersion.VERSION_ONE);

		Map<String, String> parameters = request.getParameters();
		assertNotNull(parameters);
		assertEquals(
				parameters
						.get(GetFriendListRequestBuilder.REQUEST_PARAM_STEAM_ID),
				String.valueOf("12345"));
		assertEquals(
				parameters
						.get(GetFriendListRequestBuilder.REQUEST_PARAM_RELATIONSHIP),
				String.valueOf("all"));

		// Relationship FRIEND
		request = SteamWebApiRequestFactory.createGetFriendListRequest("12345",
				Relationship.FRIEND);

		assertNotNull(request);
		assertEquals(request.getApiInterface(),
				SteamWebApiInterface.I_STEAM_USER);
		assertEquals(request.getInterfaceMethod(),
				SteamWebApiInterfaceMethod.GET_FRIEND_LIST);
		assertEquals(request.getVersion(), SteamWebApiVersion.VERSION_ONE);

		parameters = request.getParameters();
		assertNotNull(parameters);
		assertEquals(
				parameters
						.get(GetFriendListRequestBuilder.REQUEST_PARAM_STEAM_ID),
				String.valueOf("12345"));
		assertEquals(
				parameters
						.get(GetFriendListRequestBuilder.REQUEST_PARAM_RELATIONSHIP),
				String.valueOf("friend"));
	}

	@Test
	public void testCreateGetPlayerAchievementsRequestOnlySteamIdAndAppId() {

		GetPlayerAchievementsRequest request = SteamWebApiRequestFactory
				.createGetPlayerAchievementsRequest(123, "12345");

		assertNotNull(request);
		assertEquals(request.getApiInterface(),
				SteamWebApiInterface.I_STEAM_USER_STATS);
		assertEquals(request.getInterfaceMethod(),
				SteamWebApiInterfaceMethod.GET_PLAYER_ACHIEVEMENTS);
		assertEquals(request.getVersion(), SteamWebApiVersion.VERSION_ONE);

		Map<String, String> parameters = request.getParameters();
		assertNotNull(parameters);
		assertEquals(
				parameters
						.get(GetPlayerAchievementsRequestBuilder.REQUEST_PARAM_APP_ID),
				String.valueOf("123"));
		assertEquals(
				parameters
						.get(GetPlayerAchievementsRequestBuilder.REQUEST_PARAM_STEAM_ID),
				String.valueOf("12345"));
	}

	@Test
	public void testCreateGetPlayerAchievementsRequestAllParameters() {

		GetPlayerAchievementsRequest request = SteamWebApiRequestFactory
				.createGetPlayerAchievementsRequest(123, "12345", "german");

		assertNotNull(request);
		assertEquals(request.getApiInterface(),
				SteamWebApiInterface.I_STEAM_USER_STATS);
		assertEquals(request.getInterfaceMethod(),
				SteamWebApiInterfaceMethod.GET_PLAYER_ACHIEVEMENTS);
		assertEquals(request.getVersion(), SteamWebApiVersion.VERSION_ONE);

		Map<String, String> parameters = request.getParameters();
		assertNotNull(parameters);
		assertEquals(
				parameters
						.get(GetPlayerAchievementsRequestBuilder.REQUEST_PARAM_APP_ID),
				String.valueOf("123"));
		assertEquals(
				parameters
						.get(GetPlayerAchievementsRequestBuilder.REQUEST_PARAM_STEAM_ID),
				String.valueOf("12345"));
		assertEquals(
				parameters
						.get(GetPlayerAchievementsRequestBuilder.REQUEST_PARAM_LANGUAGE),
				String.valueOf("german"));
	}

	@Test
	public void testCreateGetUserStatsForGameRequestOnlySteamIdAndAppId() {

		GetUserStatsForGameRequest request = SteamWebApiRequestFactory
				.createGetUserStatsForGameRequest(123, "12345");

		assertNotNull(request);
		assertEquals(request.getApiInterface(),
				SteamWebApiInterface.I_STEAM_USER_STATS);
		assertEquals(request.getInterfaceMethod(),
				SteamWebApiInterfaceMethod.GET_USER_STATS_FOR_GAME);
		assertEquals(request.getVersion(), SteamWebApiVersion.VERSION_TWO);

		Map<String, String> parameters = request.getParameters();
		assertNotNull(parameters);
		assertEquals(
				parameters
						.get(GetUserStatsForGameRequestBuilder.REQUEST_PARAM_APP_ID),
				String.valueOf("123"));
		assertEquals(
				parameters
						.get(GetUserStatsForGameRequestBuilder.REQUEST_PARAM_STEAM_ID),
				String.valueOf("12345"));
	}

	@Test
	public void testCreateGetUserStatsForGameRequestAllParameters() {

		GetUserStatsForGameRequest request = SteamWebApiRequestFactory
				.createGetUserStatsForGameRequest(123, "12345", "german");

		assertNotNull(request);
		assertEquals(request.getApiInterface(),
				SteamWebApiInterface.I_STEAM_USER_STATS);
		assertEquals(request.getInterfaceMethod(),
				SteamWebApiInterfaceMethod.GET_USER_STATS_FOR_GAME);
		assertEquals(request.getVersion(), SteamWebApiVersion.VERSION_TWO);

		Map<String, String> parameters = request.getParameters();
		assertNotNull(parameters);
		assertEquals(
				parameters
						.get(GetUserStatsForGameRequestBuilder.REQUEST_PARAM_APP_ID),
				String.valueOf("123"));
		assertEquals(
				parameters
						.get(GetUserStatsForGameRequestBuilder.REQUEST_PARAM_STEAM_ID),
				String.valueOf("12345"));
		assertEquals(
				parameters
						.get(GetUserStatsForGameRequestBuilder.REQUEST_PARAM_LANGUAGE),
				String.valueOf("german"));
	}

	@Test
	public void testCreateGetOwnedGamesRequestOnlySteamId()
			throws JsonParseException, JsonMappingException, IOException {

		GetOwnedGamesRequest request = SteamWebApiRequestFactory
				.createGetOwnedGamesRequest("12345");

		assertNotNull(request);
		assertEquals(request.getApiInterface(),
				SteamWebApiInterface.I_PLAYER_SERVICE);
		assertEquals(request.getInterfaceMethod(),
				SteamWebApiInterfaceMethod.GET_OWNED_GAMES);
		assertEquals(request.getVersion(), SteamWebApiVersion.VERSION_ONE);

		Map<String, String> parameters = request.getParameters();
		assertNotNull(parameters);
		assertNotNull(parameters
				.get(AbstractSteamWebApiServiceRequestBuilder.REQUEST_PARAM_INPUT_JSON));

		ObjectMapper mapper = new ObjectMapper();
		String serviceParamJson = parameters
				.get(AbstractSteamWebApiServiceRequestBuilder.REQUEST_PARAM_INPUT_JSON);
		GetOwnedGamesRequestServiceParameter serviceParam = mapper.readValue(
				serviceParamJson, GetOwnedGamesRequestServiceParameter.class);

		assertNotNull(serviceParam);
		assertEquals(serviceParam.getSteamId(), "12345");
	}

	@Test
	public void testCreateGetOwnedGamesRequestAllParameters()
			throws JsonParseException, JsonMappingException, IOException {

		List<Integer> appIdsFilter = new ArrayList<Integer>();
		appIdsFilter.add(10);
		appIdsFilter.add(20);

		GetOwnedGamesRequest request = SteamWebApiRequestFactory
				.createGetOwnedGamesRequest("12345", true, true, appIdsFilter);

		assertNotNull(request);
		assertEquals(request.getApiInterface(),
				SteamWebApiInterface.I_PLAYER_SERVICE);
		assertEquals(request.getInterfaceMethod(),
				SteamWebApiInterfaceMethod.GET_OWNED_GAMES);
		assertEquals(request.getVersion(), SteamWebApiVersion.VERSION_ONE);

		Map<String, String> parameters = request.getParameters();
		assertNotNull(parameters);
		assertNotNull(parameters
				.get(AbstractSteamWebApiServiceRequestBuilder.REQUEST_PARAM_INPUT_JSON));

		ObjectMapper mapper = new ObjectMapper();
		String serviceParamJson = parameters
				.get(AbstractSteamWebApiServiceRequestBuilder.REQUEST_PARAM_INPUT_JSON);
		GetOwnedGamesRequestServiceParameter serviceParam = mapper.readValue(
				serviceParamJson, GetOwnedGamesRequestServiceParameter.class);

		assertNotNull(serviceParam);
		assertEquals(serviceParam.getSteamId(), "12345");
		assertEquals(serviceParam.getIncludeAppInfo(), Integer.valueOf(1));
		assertEquals(serviceParam.getIncludePlayedFreeGames(),
				Integer.valueOf(1));
		assertEquals(serviceParam.getAppIdsFilter(), appIdsFilter);
	}

	@Test
	public void testCreateGetRecentlyPlayedGamesRequestOnlySteamId()
			throws JsonParseException, JsonMappingException, IOException {

		GetRecentlyPlayedGamesRequest request = SteamWebApiRequestFactory
				.createGetRecentlyPlayedGamesRequest("12345");

		assertNotNull(request);
		assertEquals(request.getApiInterface(),
				SteamWebApiInterface.I_PLAYER_SERVICE);
		assertEquals(request.getInterfaceMethod(),
				SteamWebApiInterfaceMethod.GET_RECENTLY_PLAYED_GAMES);
		assertEquals(request.getVersion(), SteamWebApiVersion.VERSION_ONE);

		Map<String, String> parameters = request.getParameters();
		assertNotNull(parameters);
		assertNotNull(parameters
				.get(AbstractSteamWebApiServiceRequestBuilder.REQUEST_PARAM_INPUT_JSON));

		ObjectMapper mapper = new ObjectMapper();
		String serviceParamJson = parameters
				.get(AbstractSteamWebApiServiceRequestBuilder.REQUEST_PARAM_INPUT_JSON);
		GetRecentlyPlayedGamesRequestServiceParameter serviceParam = mapper
				.readValue(serviceParamJson,
						GetRecentlyPlayedGamesRequestServiceParameter.class);

		assertNotNull(serviceParam);
		assertEquals(serviceParam.getSteamId(), "12345");
	}

	@Test
	public void testCreateGetRecentlyPlayedGamesRequestAllParameters()
			throws JsonParseException, JsonMappingException, IOException {

		GetRecentlyPlayedGamesRequest request = SteamWebApiRequestFactory
				.createGetRecentlyPlayedGamesRequest("12345",
						Integer.valueOf(5));

		assertNotNull(request);
		assertEquals(request.getApiInterface(),
				SteamWebApiInterface.I_PLAYER_SERVICE);
		assertEquals(request.getInterfaceMethod(),
				SteamWebApiInterfaceMethod.GET_RECENTLY_PLAYED_GAMES);
		assertEquals(request.getVersion(), SteamWebApiVersion.VERSION_ONE);

		Map<String, String> parameters = request.getParameters();
		assertNotNull(parameters);
		assertNotNull(parameters
				.get(AbstractSteamWebApiServiceRequestBuilder.REQUEST_PARAM_INPUT_JSON));

		ObjectMapper mapper = new ObjectMapper();
		String serviceParamJson = parameters
				.get(AbstractSteamWebApiServiceRequestBuilder.REQUEST_PARAM_INPUT_JSON);
		GetRecentlyPlayedGamesRequestServiceParameter serviceParam = mapper
				.readValue(serviceParamJson,
						GetRecentlyPlayedGamesRequestServiceParameter.class);

		assertNotNull(serviceParam);
		assertEquals(serviceParam.getSteamId(), "12345");
		assertEquals(serviceParam.getCount(), Integer.valueOf(5));
	}

	@Test
	public void testCreateIsPlayingSharedGameRequest()
			throws JsonParseException, JsonMappingException, IOException {

		IsPlayingSharedGameRequest request = SteamWebApiRequestFactory
				.createIsPlayingSharedGameRequest("12345", Integer.valueOf(20));

		assertNotNull(request);
		assertEquals(request.getApiInterface(),
				SteamWebApiInterface.I_PLAYER_SERVICE);
		assertEquals(request.getInterfaceMethod(),
				SteamWebApiInterfaceMethod.IS_PLAYING_SHARED_GAME);
		assertEquals(request.getVersion(), SteamWebApiVersion.VERSION_ONE);

		Map<String, String> parameters = request.getParameters();
		assertNotNull(parameters);
		assertNotNull(parameters
				.get(AbstractSteamWebApiServiceRequestBuilder.REQUEST_PARAM_INPUT_JSON));

		ObjectMapper mapper = new ObjectMapper();
		String serviceParamJson = parameters
				.get(AbstractSteamWebApiServiceRequestBuilder.REQUEST_PARAM_INPUT_JSON);
		IsPlayingSharedGameRequestServiceParameter serviceParam = mapper
				.readValue(serviceParamJson,
						IsPlayingSharedGameRequestServiceParameter.class);

		assertNotNull(serviceParam);
		assertEquals(serviceParam.getSteamId(), "12345");
		assertEquals(serviceParam.getAppIdPlaying(), Integer.valueOf(20));
	}

	@Test
	public void testCreateGetSchemaForGameRequest() throws JsonParseException,
			JsonMappingException, IOException {

		GetSchemaForGameRequest request = SteamWebApiRequestFactory
				.createGetSchemaForGameRequest(Integer.valueOf(123));

		assertNotNull(request);
		assertEquals(request.getApiInterface(),
				SteamWebApiInterface.I_STEAM_USER_STATS);
		assertEquals(request.getInterfaceMethod(),
				SteamWebApiInterfaceMethod.GET_SCHEMA_FOR_GAME);
		assertEquals(request.getVersion(), SteamWebApiVersion.VERSION_TWO);

		Map<String, String> parameters = request.getParameters();
		assertNotNull(parameters);
		assertNotNull(parameters
				.get(GetSchemaForGameRequestBuilder.REQUEST_PARAM_APP_ID));

		assertEquals(
				parameters
						.get(GetSchemaForGameRequestBuilder.REQUEST_PARAM_APP_ID),
				"123");
	}

	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testCreateGetPlayerBansRequestMissingSteamIds() {

		SteamWebApiRequestFactory.createGetPlayerBansRequest(Collections
				.<String> emptyList());
	}

	@Test
	public void testGetPlayerBansRequest() {

		List<String> steamIds = new ArrayList<String>();
		steamIds.add("123");
		steamIds.add("456");
		steamIds.add("789");

		GetPlayerBansRequest request = SteamWebApiRequestFactory
				.createGetPlayerBansRequest(steamIds);

		assertNotNull(request);

		assertEquals(request.getApiInterface(),
				SteamWebApiInterface.I_STEAM_USER);
		assertEquals(request.getInterfaceMethod(),
				SteamWebApiInterfaceMethod.GET_PLAYER_BANS);
		assertEquals(request.getVersion(), SteamWebApiVersion.VERSION_ONE);

		Map<String, String> parameters = request.getParameters();
		assertNotNull(parameters);
		assertEquals(
				parameters
						.get(GetPlayerBansRequestBuilder.REQUEST_PARAM_STEAM_IDS),
				String.valueOf("123,456,789"));
	}

	@Test
	public void testGetGameItemsRequest() {
		GetGameItemsRequest request = SteamWebApiRequestFactory.createGetGameItemsRequest();

		assertNotNull(request);

		assertEquals(request.getApiInterface(),SteamWebApiInterface.I_ECON_DOTA2);
		assertEquals(request.getInterfaceMethod(),SteamWebApiInterfaceMethod.GET_GAME_ITEMS);
		assertEquals(request.getVersion(), SteamWebApiVersion.VERSION_ONE);

		Map<String,String> parameters = request.getParameters();
		assertEquals(parameters.size(), 1);
	}

	@Test
	public void testGetHeroesRequest() {
		GetHeroesRequest request = SteamWebApiRequestFactory.createGetHeroesRequest();

		assertNotNull(request);

		assertEquals(request.getApiInterface(),SteamWebApiInterface.I_ECON_DOTA2);
		assertEquals(request.getInterfaceMethod(),SteamWebApiInterfaceMethod.GET_HEROES);
		assertEquals(request.getVersion(), SteamWebApiVersion.VERSION_ONE);

		Map<String,String> parameters = request.getParameters();
		assertEquals(parameters.size(), 2);
        assertEquals(parameters.get(GetHeroesRequestBuilder.REQUEST_PARAM_LANGUAGE),"en");
	}

	@Test
	public void testGetLeagueListingRequest() {
        GetLeagueListingRequest request = SteamWebApiRequestFactory.createGetLeagueListingRequest();

		assertNotNull(request);

		assertEquals(request.getApiInterface(),SteamWebApiInterface.I_DOTA2_MATCH);
		assertEquals(request.getInterfaceMethod(),SteamWebApiInterfaceMethod.GET_LEAGUE_LISTING);
		assertEquals(request.getVersion(), SteamWebApiVersion.VERSION_ONE);

        Map<String,String> parameters = request.getParameters();
        assertEquals(parameters.size(), 2);
        assertEquals(parameters.get(GetLeagueListingRequestBuilder.REQUEST_PARAM_LANGUAGE),"en");

    }

    @Test
	public void testGetMatchHistoryBySequenceNumRequest() {
		GetMatchHistoryBySequenceNumRequest request = SteamWebApiRequestFactory.createGetMatchHistoryBySequenceNumRequest(Long.valueOf(100),20);

		assertNotNull(request);

		assertEquals(request.getApiInterface(),SteamWebApiInterface.I_DOTA2_MATCH);
		assertEquals(request.getInterfaceMethod(),SteamWebApiInterfaceMethod.GET_MATCH_HISTORY_BY_SEQUENCE_NUM);
		assertEquals(request.getVersion(), SteamWebApiVersion.VERSION_ONE);

		Map<String,String> parameters = request.getParameters();
		assertEquals(parameters.size(), 3);
		assertEquals(parameters.get(GetMatchHistoryBySequenceNumRequest.GetMatchHistoryBySequenceNumRequestBuilder.REQUEST_PARAM_START_AT_MATCH_SEQ_NUM),"100");
        assertEquals(parameters.get(GetMatchHistoryBySequenceNumRequestBuilder.REQUEST_PARAM_MATCHES_REQUESTED),"20");

	}

	@Test
	public void testGetFantasyPlayerStatsRequestRequest() {
        final String leagueId = "2";
        final GregorianCalendar startDate = new GregorianCalendar(2016,12,01);
        final GregorianCalendar endDate = new GregorianCalendar(2016,12,31);
        final String playerAccountId = "123123";
        final Integer seriesId = 10;
        final String matchId = "123";

        GetFantasyPlayerStatsRequest request;

        Map<String, String> parameters;

	    // Simply by league
		request = SteamWebApiRequestFactory.createGetFantasyPlayerStatsRequest(leagueId);

		assertNotNull(request);

		assertEquals(request.getApiInterface(),SteamWebApiInterface.I_DOTA2_FANTASY);
		assertEquals(request.getInterfaceMethod(),SteamWebApiInterfaceMethod.GET_FANTASY_PLAYER_STATS);
		assertEquals(request.getVersion(), SteamWebApiVersion.VERSION_ONE);

        parameters = request.getParameters();
        assertNotNull(parameters);
        assertEquals(parameters.size(), 2);
        assertEquals(
                parameters
                        .get(GetFantasyPlayerStatsRequestBuilder.REQUEST_PARAM_FANTASY_LEAGUE_ID),
                "2");

		// By player and period

        request = SteamWebApiRequestFactory.createGetFantasyPlayerStatsRequest(leagueId, startDate.getTime(), endDate.getTime(), playerAccountId);

        assertNotNull(request);

        assertEquals(request.getApiInterface(),SteamWebApiInterface.I_DOTA2_FANTASY);
        assertEquals(request.getInterfaceMethod(),SteamWebApiInterfaceMethod.GET_FANTASY_PLAYER_STATS);
        assertEquals(request.getVersion(), SteamWebApiVersion.VERSION_ONE);

        parameters = request.getParameters();
        assertNotNull(parameters);
        assertEquals(parameters.size(), 5);
        assertEquals(
                parameters
                        .get(GetFantasyPlayerStatsRequestBuilder.REQUEST_PARAM_FANTASY_LEAGUE_ID),
                "2");
        assertEquals(parameters.get(GetFantasyPlayerStatsRequestBuilder.REQUEST_PARAM_START_TIME),String.valueOf(startDate.getTimeInMillis()/1000L));
		assertEquals(parameters.get(GetFantasyPlayerStatsRequestBuilder.REQUEST_PARAM_END_TIME),String.valueOf(endDate.getTimeInMillis()/1000L));
        assertEquals(parameters.get(GetFantasyPlayerStatsRequestBuilder.REQUEST_PARAM_PLAYER_ACCOUNT_ID),"123123");

        // By player and series

        request = SteamWebApiRequestFactory.createGetFantasyPlayerStatsRequest(leagueId,seriesId,playerAccountId);

        assertNotNull(request);

        assertEquals(request.getApiInterface(),SteamWebApiInterface.I_DOTA2_FANTASY);
        assertEquals(request.getInterfaceMethod(),SteamWebApiInterfaceMethod.GET_FANTASY_PLAYER_STATS);
        assertEquals(request.getVersion(), SteamWebApiVersion.VERSION_ONE);

        parameters = request.getParameters();
        assertNotNull(parameters);
        assertEquals(parameters.size(), 4);
        assertEquals(
                parameters
                        .get(GetFantasyPlayerStatsRequestBuilder.REQUEST_PARAM_FANTASY_LEAGUE_ID),
                "2");
        assertEquals(parameters.get(GetFantasyPlayerStatsRequestBuilder.REQUEST_PARAM_SERIES_ID),seriesId.toString());
        assertEquals(parameters.get(GetFantasyPlayerStatsRequestBuilder.REQUEST_PARAM_PLAYER_ACCOUNT_ID),"123123");

        // By player and match

        request = SteamWebApiRequestFactory.createGetFantasyPlayerStatsRequest(leagueId,matchId,playerAccountId);

        assertNotNull(request);

        assertEquals(request.getApiInterface(),SteamWebApiInterface.I_DOTA2_FANTASY);
        assertEquals(request.getInterfaceMethod(),SteamWebApiInterfaceMethod.GET_FANTASY_PLAYER_STATS);
        assertEquals(request.getVersion(), SteamWebApiVersion.VERSION_ONE);

        parameters = request.getParameters();
        assertNotNull(parameters);
        assertEquals(parameters.size(), 4);
        assertEquals(
                parameters
                        .get(GetFantasyPlayerStatsRequestBuilder.REQUEST_PARAM_FANTASY_LEAGUE_ID),
                "2");
        assertEquals(parameters.get(GetFantasyPlayerStatsRequestBuilder.REQUEST_PARAM_MATCH_ID), matchId);
        assertEquals(parameters.get(GetFantasyPlayerStatsRequestBuilder.REQUEST_PARAM_PLAYER_ACCOUNT_ID),"123123");
	}

	@Test
	public void testGetAppListRequest() {
		GetAppListRequest request = SteamWebApiRequestFactory.createGetAppListRequest();

		assertNotNull(request);

		assertEquals(request.getApiInterface(),SteamWebApiInterface.I_STEAM_APPS);
		assertEquals(request.getInterfaceMethod(),SteamWebApiInterfaceMethod.GET_APP_LIST);
		assertEquals(request.getVersion(), SteamWebApiVersion.VERSION_TWO);

		Map<String,String> parameters = request.getParameters();
		assertEquals(parameters.size(), 1);
	}

	@Test
	public void testResolveVanityUrlRequest() {
		ResolveVanityUrlRequest request = SteamWebApiRequestFactory.createResolveVanityUrlRequest("", null);

		assertNotNull(request);

		assertEquals(request.getApiInterface(),SteamWebApiInterface.I_STEAM_USER);
		assertEquals(request.getInterfaceMethod(),SteamWebApiInterfaceMethod.RESOLVE_VANITY_URL);
		assertEquals(request.getVersion(), SteamWebApiVersion.VERSION_ONE);

		Map<String,String> parameters = request.getParameters();
		assertEquals(parameters.size(), 2);
	}

	@Test
	public void testResolveVanityUrlRequestWithUrlType() {
		ResolveVanityUrlRequest request = SteamWebApiRequestFactory.createResolveVanityUrlRequest("", 1);

		assertNotNull(request);

		assertEquals(request.getApiInterface(),SteamWebApiInterface.I_STEAM_USER);
		assertEquals(request.getInterfaceMethod(),SteamWebApiInterfaceMethod.RESOLVE_VANITY_URL);
		assertEquals(request.getVersion(), SteamWebApiVersion.VERSION_ONE);

		Map<String,String> parameters = request.getParameters();
		assertEquals(parameters.size(), 3);
	}

	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testResolveVanityUrlRequestWithNullVanityUrl() {
		SteamWebApiRequestFactory.createResolveVanityUrlRequest(null, null);
	}
}
