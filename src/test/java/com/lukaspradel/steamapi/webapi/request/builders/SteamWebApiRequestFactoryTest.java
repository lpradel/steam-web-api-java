package com.lukaspradel.steamapi.webapi.request.builders;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import com.lukaspradel.steamapi.webapi.core.SteamWebApiInterface;
import com.lukaspradel.steamapi.webapi.core.SteamWebApiInterfaceMethod;
import com.lukaspradel.steamapi.webapi.core.SteamWebApiVersion;
import com.lukaspradel.steamapi.webapi.request.GetGlobalAchievementPercentagesForAppRequest;
import com.lukaspradel.steamapi.webapi.request.GetGlobalAchievementPercentagesForAppRequest.GetGlobalAchievementPercentagesForAppRequestBuilder;
import com.lukaspradel.steamapi.webapi.request.GetNewsForAppRequest;
import com.lukaspradel.steamapi.webapi.request.GetNewsForAppRequest.GetNewsForAppRequestBuilder;
import com.lukaspradel.steamapi.webapi.request.GetPlayerSummariesRequest;
import com.lukaspradel.steamapi.webapi.request.GetPlayerSummariesRequest.GetPlayerSummariesRequestBuilder;

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
}
