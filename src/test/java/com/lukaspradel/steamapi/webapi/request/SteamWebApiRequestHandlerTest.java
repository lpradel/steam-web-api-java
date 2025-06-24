package com.lukaspradel.steamapi.webapi.request;

import com.lukaspradel.steamapi.webapi.core.BaseTest;
import com.lukaspradel.steamapi.core.exception.SteamApiException;
import com.lukaspradel.steamapi.core.exception.SteamApiKeyException;
import com.lukaspradel.steamapi.webapi.core.SteamWebApiInterface;
import com.lukaspradel.steamapi.webapi.core.SteamWebApiInterfaceMethod;
import com.lukaspradel.steamapi.webapi.core.SteamWebApiVersion;
import org.mockito.ArgumentMatcher;
import org.mockito.Mock;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandler;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

public class SteamWebApiRequestHandlerTest extends BaseTest {

	private static final int OK = 200;
	private static final int UNAUTHORIZED = 401;
	private static final int INTERNAL_SERVER_ERROR = 500;

	private String key = "12345";

	private URI uri = URI.create("http://localhost:80");

	private SteamWebApiRequestHandler requestHandlerHttps = new SteamWebApiRequestHandler(
			true, key);

	private SteamWebApiRequestHandler requestHandlerHttpsSpy = spy(requestHandlerHttps);

	@Mock
	private SteamWebApiRequest requestMock;

	@Mock
	private HttpClient httpClientMock;

	@Mock
	private HttpResponse<String> httpResponseMock;

	private ArgumentMatcher<BodyHandler<String>> bodyHandlerMatcher = arg -> arg != null ? true : false;

	@Test
	public void testGetRequestUrl() throws SteamApiException {

		Map<String, String> parameters = new HashMap<String, String>();

		when(requestMock.getBaseUrl()).thenReturn("api.steampowered.com");
		when(requestMock.getApiInterface()).thenReturn(
				SteamWebApiInterface.I_STEAM_NEWS);
		when(requestMock.getInterfaceMethod()).thenReturn(
				SteamWebApiInterfaceMethod.GET_NEWS_FOR_APP);
		when(requestMock.getVersion()).thenReturn(
				SteamWebApiVersion.VERSION_TWO);
		when(requestMock.getParameters()).thenReturn(parameters);
		when(requestHandlerHttpsSpy.getRequestQuery(parameters))
				.thenReturn("");
		when(
				requestHandlerHttpsSpy.getRequestUri(any(String.class),
						any(String.class), any(String.class),
						any(String.class))).thenReturn(
				uri);

		URI actual = requestHandlerHttpsSpy.getRequestUrl(requestMock);
		verify(requestHandlerHttpsSpy).getRequestUri("https",
				"api.steampowered.com", "/ISteamNews/GetNewsForApp/v0002",
				"");
		assertEquals(actual, uri);
	}

	@Test
	public void testGetRequestQuery() throws SteamApiException {
		var parameters = new LinkedHashMap<String, String>();
		parameters.put("key", null); // prepopulate the key, it gets set in the getRequestQuery method
		parameters.put("test-parameter", "test-value");
		parameters.put("format", "json");
		parameters.put("input_json", "{\"steamid\":\"76561198039505218\"}");

		String query = requestHandlerHttps.getRequestQuery(parameters);

		assertEquals(query, "key=12345&test-parameter=test-value&format=json&input_json=%7B%22steamid%22%3A%2276561198039505218%22%7D");
	}

	@Test(expectedExceptions = SteamApiKeyException.class)
	public void testGetRequestQueryApiKeyIsNull() throws SteamApiException {
		var reqHandler = new SteamWebApiRequestHandler(true, null);
		reqHandler.getRequestQuery(null);
	}

	@Test(expectedExceptions = SteamApiException.class)
	public void testGetRequestQueryParameterKeyIsNull() throws SteamApiException {
		var params = new HashMap<String, String>();
		params.put(null, "test-value");

		try {
			requestHandlerHttps.getRequestQuery(params);
		} catch (SteamApiException e) {
			assertEquals(e.getMessage(), "The key of the parameter with the value 'test-value' is null");
			throw e;
		}
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
	public void testGetRequestUri() throws SteamApiException {
		String scheme = "https";
		String host = "api.steampowered.com";
		String path = "/IPlayerService/GetOwnedGames/v0001";
		var parameters = new LinkedHashMap<String, String>();
		parameters.put("key", "12345");
		parameters.put("format", "json");
		parameters.put("input_json", "{\"steamid\":\"76561198039505218\"}");

		String query = requestHandlerHttps.getRequestQuery(parameters);
		URI actual = requestHandlerHttps.getRequestUri(scheme, host, path, query);

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
	public void testGetRequestUriWithInvalidUri() throws SteamApiException {
		String scheme = "";
		String host = "api.steampowered.com";
		String path = "/IPlayerService/GetOwnedGames/v0001";
		var parameters = new LinkedHashMap<String, String>();
		parameters.put("key", "12345");
		parameters.put("format", "json");
		parameters.put("input_json", "{\"steamid\":\"76561198039505218\"}");

		String query = requestHandlerHttps.getRequestQuery(parameters);
		requestHandlerHttps.getRequestUri(scheme, host, path, query);
	}

	@Test(expectedExceptions = SteamApiException.class)
	public void testGetWebApiResponseUnauthorized()
			throws IOException, SteamApiException, InterruptedException {

		when(httpResponseMock.statusCode()).thenReturn(UNAUTHORIZED);
		when(httpClientMock.send(any(HttpRequest.class), argThat(bodyHandlerMatcher))).thenReturn(
				httpResponseMock);
		when(requestHandlerHttpsSpy.getHttpClient()).thenReturn(httpClientMock);

		requestHandlerHttpsSpy.getWebApiResponse(uri);

		fail("An exception should be thrown in getWebApiResponse!");
	}

	@Test(expectedExceptions = SteamApiException.class)
	public void testGetWebApiResponseErrorCode()
			throws IOException, SteamApiException, InterruptedException{

		when(httpResponseMock.statusCode()).thenReturn(INTERNAL_SERVER_ERROR);
		when(httpClientMock.send(any(HttpRequest.class), argThat(bodyHandlerMatcher))).thenReturn(
				httpResponseMock);
		when(requestHandlerHttpsSpy.getHttpClient()).thenReturn(httpClientMock);

		requestHandlerHttpsSpy.getWebApiResponse(uri);

		fail("An exception should be thrown in getWebApiResponse!");
	}

	@Test(expectedExceptions = SteamApiException.class)
	public void testGetWebApiResponseIOException()
			throws IOException, SteamApiException, InterruptedException {

		when(httpClientMock.send(any(HttpRequest.class), argThat(bodyHandlerMatcher))).thenThrow(
				new IOException("intended-io-exception"));
		when(requestHandlerHttpsSpy.getHttpClient()).thenReturn(httpClientMock);

		requestHandlerHttpsSpy.getWebApiResponse(uri);

		fail("An exception should be thrown in getWebApiResponse!");
	}

	@Test
	public void testGetWebApiResponse() throws IOException, SteamApiException, InterruptedException {

		when(requestMock.getBaseUrl()).thenReturn("api.steampowered.com");
		when(requestMock.getApiInterface()).thenReturn(
				SteamWebApiInterface.I_STEAM_NEWS);
		when(requestMock.getInterfaceMethod()).thenReturn(
				SteamWebApiInterfaceMethod.GET_NEWS_FOR_APP);
		when(requestMock.getVersion()).thenReturn(
				SteamWebApiVersion.VERSION_TWO);

		when(httpResponseMock.statusCode()).thenReturn(OK);
		when(httpClientMock.send(any(HttpRequest.class), argThat(bodyHandlerMatcher))).thenReturn(
				httpResponseMock);

		when(requestHandlerHttpsSpy.getHttpClient()).thenReturn(httpClientMock);

		requestHandlerHttpsSpy.getWebApiResponse(requestMock);

		verify(httpResponseMock).body();
	}
}
