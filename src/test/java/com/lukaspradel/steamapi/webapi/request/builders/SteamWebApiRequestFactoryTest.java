package com.lukaspradel.steamapi.webapi.request.builders;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;

import java.util.Map;

import org.testng.annotations.Test;

import com.lukaspradel.steamapi.webapi.SteamWebApiInterface;
import com.lukaspradel.steamapi.webapi.SteamWebApiInterfaceMethod;
import com.lukaspradel.steamapi.webapi.SteamWebApiVersion;
import com.lukaspradel.steamapi.webapi.request.GetNewsForAppRequest;
import com.lukaspradel.steamapi.webapi.request.GetNewsForAppRequest.GetNewsForAppRequestBuilder;

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
}
