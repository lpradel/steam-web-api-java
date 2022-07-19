package com.lukaspradel.steamapi.webapi.request;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.hc.client5.http.ClientProtocolException;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.classic.methods.HttpUriRequest;
import org.apache.hc.core5.http.ClassicHttpResponse;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpStatus;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.testng.annotations.Test;

import com.lukaspradel.steamapi.BaseTest;
import com.lukaspradel.steamapi.core.exception.SteamApiException;
import com.lukaspradel.steamapi.webapi.core.SteamWebApiInterface;
import com.lukaspradel.steamapi.webapi.core.SteamWebApiInterfaceMethod;
import com.lukaspradel.steamapi.webapi.core.SteamWebApiVersion;

public class SteamWebApiRequestHandlerTest extends BaseTest {

	private String key = "12345";

	private SteamWebApiRequestHandler requestHandlerHttps = new SteamWebApiRequestHandler(
			true, key);

	private SteamWebApiRequestHandler requestHandlerHttpsSpy = spy(requestHandlerHttps);

	@Mock
	private SteamWebApiRequest requestMock;

	@Mock
	private HttpClient httpClientMock;

	@Mock
	private ClassicHttpResponse httpResponseMock;

	@Mock
	private HttpEntity httpEntityMock;

	@Test
	public void testGetRequestUrl() throws SteamApiException {

		URI uriMock = PowerMockito.mock(URI.class);
		Map<String, String> parameters = new HashMap<String, String>();
		List<NameValuePair> params = new ArrayList<NameValuePair>();

		when(requestMock.getBaseUrl()).thenReturn("api.steampowered.com");
		when(requestMock.getApiInterface()).thenReturn(
				SteamWebApiInterface.I_STEAM_NEWS);
		when(requestMock.getInterfaceMethod()).thenReturn(
				SteamWebApiInterfaceMethod.GET_NEWS_FOR_APP);
		when(requestMock.getVersion()).thenReturn(
				SteamWebApiVersion.VERSION_TWO);
		when(requestMock.getParameters()).thenReturn(parameters);
		when(requestHandlerHttpsSpy.getRequestParameters(parameters))
				.thenReturn(params);
		when(
				requestHandlerHttpsSpy.getRequestUri(any(String.class),
						any(String.class), any(String.class),
						ArgumentMatchers.anyList())).thenReturn(
				uriMock);

		URI actual = requestHandlerHttpsSpy.getRequestUrl(requestMock);
		verify(requestHandlerHttpsSpy).getRequestUri("https",
				"api.steampowered.com", "/ISteamNews/GetNewsForApp/v0002",
				params);
		assertEquals(actual, uriMock);
	}

	@Test
	public void testGetRequestPath() {

		when(requestMock.getBaseUrl()).thenReturn("api.steampowered.com");
		when(requestMock.getApiInterface()).thenReturn(
				SteamWebApiInterface.I_STEAM_NEWS);
		when(requestMock.getInterfaceMethod()).thenReturn(
				SteamWebApiInterfaceMethod.GET_NEWS_FOR_APP);
		when(requestMock.getVersion()).thenReturn(
				SteamWebApiVersion.VERSION_TWO);

		String actual = requestHandlerHttps.getRequestPath(requestMock);
		assertEquals(actual, "/ISteamNews/GetNewsForApp/v0002");
	}

	@Test
	public void testGetRequestParameters() {

		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("format", "json");
		parameters.put("test-parameter", "test-value");
		parameters.put("input_json", "{\"steamid\":\"76561198039505218\"}");

		List<NameValuePair> actual = requestHandlerHttps
				.getRequestParameters(parameters);
		assertEquals(actual.size(), 4);
		assertTrue(actual.contains(new BasicNameValuePair("key", "12345")));
		assertTrue(actual.contains(new BasicNameValuePair("format", "json")));
		assertTrue(actual.contains(new BasicNameValuePair("test-parameter",
				"test-value")));
		assertTrue(actual.contains(new BasicNameValuePair("input_json",
				"{\"steamid\":\"76561198039505218\"}")));
	}

	@Test
	public void testGetRequestUri() throws SteamApiException {

		String scheme = "https";
		String host = "api.steampowered.com";
		String path = "/IPlayerService/GetOwnedGames/v0001";
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		parameters.add(new BasicNameValuePair("key", "12345"));
		parameters.add(new BasicNameValuePair("format", "json"));
		parameters.add(new BasicNameValuePair("input_json",
				"{\"steamid\":\"76561198039505218\"}"));

		URI actual = requestHandlerHttpsSpy.getRequestUri(scheme, host, path,
				parameters);
		assertEquals(actual.getScheme(), "https");
		assertEquals(actual.getHost(), "api.steampowered.com");
		assertEquals(actual.getPath(), "/IPlayerService/GetOwnedGames/v0001");
		assertEquals(actual.getQuery(),
				"key=12345&format=json&input_json={\"steamid\":\"76561198039505218\"}");
		assertEquals(
				actual.toString(),
				"https://api.steampowered.com/IPlayerService/GetOwnedGames/v0001?key=12345&format=json&input_json=%7B%22steamid%22%3A%2276561198039505218%22%7D");
	}

	@Test(expectedExceptions = SteamApiException.class)
	public void testGetWebApiResponseUnauthorized()
			throws ClientProtocolException, IOException, SteamApiException {

		when(httpResponseMock.getCode()).thenReturn(
				HttpStatus.SC_UNAUTHORIZED);
		when(httpClientMock.execute(isNull(), any(HttpUriRequest.class))).thenReturn(
				httpResponseMock);
		when(requestHandlerHttpsSpy.getHttpClient()).thenReturn(httpClientMock);

		URI uriMock = PowerMockito.mock(URI.class);

		requestHandlerHttpsSpy.getWebApiResponse(uriMock);

		fail("An exception should be thrown in getWebApiResponse!");
	}

	@Test(expectedExceptions = SteamApiException.class)
	public void testGetWebApiResponseErrorCode()
			throws ClientProtocolException, IOException, SteamApiException,
			URISyntaxException {

		when(httpResponseMock.getCode()).thenReturn(
				HttpStatus.SC_INTERNAL_SERVER_ERROR);
		when(httpClientMock.execute(isNull(), any(HttpUriRequest.class))).thenReturn(
				httpResponseMock);
		when(requestHandlerHttpsSpy.getHttpClient()).thenReturn(httpClientMock);

		URI uriMock = PowerMockito.mock(URI.class);

		requestHandlerHttpsSpy.getWebApiResponse(uriMock);

		fail("An exception should be thrown in getWebApiResponse!");
	}

	@Test(expectedExceptions = SteamApiException.class)
	public void testGetWebApiResponseIOException()
			throws ClientProtocolException, IOException, SteamApiException {

		when(httpClientMock.execute(isNull(), any(HttpUriRequest.class))).thenThrow(
				new IOException("intended-io-exception"));
		when(requestHandlerHttpsSpy.getHttpClient()).thenReturn(httpClientMock);

		URI uriMock = PowerMockito.mock(URI.class);

		requestHandlerHttpsSpy.getWebApiResponse(uriMock);

		fail("An exception should be thrown in getWebApiResponse!");
	}

	@Test
	public void testGetWebApiResponse() throws ClientProtocolException,
			IOException, SteamApiException, ParseException {

		when(requestMock.getBaseUrl()).thenReturn("api.steampowered.com");
		when(requestMock.getApiInterface()).thenReturn(
				SteamWebApiInterface.I_STEAM_NEWS);
		when(requestMock.getInterfaceMethod()).thenReturn(
				SteamWebApiInterfaceMethod.GET_NEWS_FOR_APP);
		when(requestMock.getVersion()).thenReturn(
				SteamWebApiVersion.VERSION_TWO);

		when(httpResponseMock.getCode()).thenReturn(HttpStatus.SC_OK);
		when(httpResponseMock.getEntity()).thenReturn(httpEntityMock);
		when(httpClientMock.execute(isNull(), any(HttpUriRequest.class))).thenReturn(
				httpResponseMock);

		when(requestHandlerHttpsSpy.getHttpClient()).thenReturn(httpClientMock);

		requestHandlerHttpsSpy.getWebApiResponse(requestMock);
		verify(requestHandlerHttpsSpy)
				.getHttpResponseAsString(httpResponseMock);
	}
}
