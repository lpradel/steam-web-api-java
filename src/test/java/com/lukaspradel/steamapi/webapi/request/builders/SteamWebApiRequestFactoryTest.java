package com.lukaspradel.steamapi.webapi.request.builders;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lukaspradel.steamapi.webapi.core.SteamWebApiInterface;
import com.lukaspradel.steamapi.webapi.core.SteamWebApiInterfaceMethod;
import com.lukaspradel.steamapi.webapi.core.SteamWebApiVersion;
import com.lukaspradel.steamapi.webapi.request.GetFriendListRequest;
import com.lukaspradel.steamapi.webapi.request.GetFriendListRequest.GetFriendListRequestBuilder;
import com.lukaspradel.steamapi.webapi.request.GetFriendListRequest.Relationship;
import com.lukaspradel.steamapi.webapi.request.GetGlobalAchievementPercentagesForAppRequest;
import com.lukaspradel.steamapi.webapi.request.GetGlobalAchievementPercentagesForAppRequest.GetGlobalAchievementPercentagesForAppRequestBuilder;
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
}
