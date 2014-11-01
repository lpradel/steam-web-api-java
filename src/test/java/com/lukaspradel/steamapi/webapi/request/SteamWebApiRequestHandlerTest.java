package com.lukaspradel.steamapi.webapi.request;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.testng.annotations.Test;

import com.lukaspradel.steamapi.BaseTest;
import com.lukaspradel.steamapi.webapi.SteamWebApiInterface;
import com.lukaspradel.steamapi.webapi.SteamWebApiInterfaceMethod;
import com.lukaspradel.steamapi.webapi.SteamWebApiVersion;

public class SteamWebApiRequestHandlerTest extends BaseTest {

	private String key = "12345";

	private SteamWebApiRequestHandler requestHandlerHttps = new SteamWebApiRequestHandler(
			true, key);

	private SteamWebApiRequestHandler requestHandlerHttpsSpy = spy(requestHandlerHttps);

	@Mock
	private SteamWebApiRequest request;

	@Test
	public void testGetRequestUrl() {

		when(request.getBaseUrl()).thenReturn("api.steampowered.com");
		when(request.getApiInterface()).thenReturn(
				SteamWebApiInterface.I_STEAM_NEWS);
		when(request.getInterfaceMethod()).thenReturn(
				SteamWebApiInterfaceMethod.GET_NEWS_FOR_APP);
		when(request.getVersion()).thenReturn(SteamWebApiVersion.VERSION_TWO);

		String actual = requestHandlerHttps.getRequestUrl("https://", request,
				"format=json");
		assertEquals(actual,
				"https://api.steampowered.com/ISteamNews/GetNewsForApp/v0002/?format=json");
	}

	@Test
	public void testGetParametersUrl() {

		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("format", "json");
		parameters.put("test-parameter", "test-value");

		String actual = requestHandlerHttps.getParametersUrl(parameters);
		assertEquals(actual, "test-parameter=test-value&format=json&key=12345");
	}

	@Test
	public void testGetRequestUrlByRequest() {

		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("format", "json");
		parameters.put("test-parameter", "test-value");

		when(request.getBaseUrl()).thenReturn("api.steampowered.com");
		when(request.getApiInterface()).thenReturn(
				SteamWebApiInterface.I_STEAM_NEWS);
		when(request.getInterfaceMethod()).thenReturn(
				SteamWebApiInterfaceMethod.GET_NEWS_FOR_APP);
		when(request.getVersion()).thenReturn(SteamWebApiVersion.VERSION_TWO);
		when(request.getParameters()).thenReturn(parameters);

		when(requestHandlerHttpsSpy.getParametersUrl(parameters)).thenReturn(
				"test-parameter=test-value&format=json&key=12345");
		Mockito.doReturn(
				"https://api.steampowered.com/ISteamNews/GetNewsForApp/v0002/?test-parameter=test-value&format=json&key=12345")
				.when(requestHandlerHttpsSpy)
				.getRequestUrl("https://", request,
						"test-parameter=test-value&format=json&key=12345");

		String actual = requestHandlerHttpsSpy.getRequestUrl(request);
		assertEquals(
				actual,
				"https://api.steampowered.com/ISteamNews/GetNewsForApp/v0002/?test-parameter=test-value&format=json&key=12345");
	}
}
