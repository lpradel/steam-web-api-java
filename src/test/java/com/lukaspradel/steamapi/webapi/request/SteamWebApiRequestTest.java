package com.lukaspradel.steamapi.webapi.request;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.Collections;

import org.testng.annotations.Test;

import com.lukaspradel.steamapi.BaseTest;
import com.lukaspradel.steamapi.webapi.core.SteamWebApiInterface;
import com.lukaspradel.steamapi.webapi.core.SteamWebApiInterfaceMethod;
import com.lukaspradel.steamapi.webapi.core.SteamWebApiVersion;
import com.lukaspradel.steamapi.webapi.request.SteamWebApiRequest.SteamWebApiRequestBuilder;

public class SteamWebApiRequestTest extends BaseTest {

	@Test
	public void testBuilder() {

		SteamWebApiRequestBuilder builder = new SteamWebApiRequestBuilder(
				SteamWebApiInterface.I_STEAM_USER,
				SteamWebApiInterfaceMethod.GET_FRIEND_LIST,
				SteamWebApiVersion.VERSION_ONE,
				Collections.<String, String> emptyMap());

		SteamWebApiRequest request = builder.build();

		assertEquals(request.getBaseUrl(), SteamWebApiRequest.WEB_API_BASE_URL);
		assertEquals(request.getApiInterface(),
				SteamWebApiInterface.I_STEAM_USER);
		assertEquals(request.getInterfaceMethod(),
				SteamWebApiInterfaceMethod.GET_FRIEND_LIST);
		assertEquals(request.getVersion(), SteamWebApiVersion.VERSION_ONE);
		assertTrue(request.getParameters().isEmpty());
	}
}
