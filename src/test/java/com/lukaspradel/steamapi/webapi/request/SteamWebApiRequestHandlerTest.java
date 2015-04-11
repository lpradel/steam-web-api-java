package com.lukaspradel.steamapi.webapi.request;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.mockito.Mock;
import org.mockito.Mockito;
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
	private HttpResponse httpResponseMock;

	@Mock
	private StatusLine statusLineMock;

	@Mock
	private HttpEntity httpEntityMock;

	@Test
	public void testGetRequestUrl() {

		when(requestMock.getBaseUrl()).thenReturn("api.steampowered.com");
		when(requestMock.getApiInterface()).thenReturn(
				SteamWebApiInterface.I_STEAM_NEWS);
		when(requestMock.getInterfaceMethod()).thenReturn(
				SteamWebApiInterfaceMethod.GET_NEWS_FOR_APP);
		when(requestMock.getVersion()).thenReturn(
				SteamWebApiVersion.VERSION_TWO);

		String actual = requestHandlerHttps.getRequestUrl("https://",
				requestMock, "format=json");
		assertEquals(actual,
				"https://api.steampowered.com/ISteamNews/GetNewsForApp/v0002/?format=json");
	}

	@Test
	public void testGetParametersUrl() {

		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("format", "json");
		parameters.put("test-parameter", "test-value");

		String actual = requestHandlerHttps.getParametersUrl(parameters);
		assertTrue(actual.contains("test-parameter=test-value"));
		assertTrue(actual.contains("format=json"));
		assertTrue(actual.contains("key=12345"));
		assertTrue(actual.contains("&"));
	}

	@Test
	public void testGetRequestUrlByRequest() {

		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("format", "json");
		parameters.put("test-parameter", "test-value");

		when(requestMock.getBaseUrl()).thenReturn("api.steampowered.com");
		when(requestMock.getApiInterface()).thenReturn(
				SteamWebApiInterface.I_STEAM_NEWS);
		when(requestMock.getInterfaceMethod()).thenReturn(
				SteamWebApiInterfaceMethod.GET_NEWS_FOR_APP);
		when(requestMock.getVersion()).thenReturn(
				SteamWebApiVersion.VERSION_TWO);
		when(requestMock.getParameters()).thenReturn(parameters);

		when(requestHandlerHttpsSpy.getParametersUrl(parameters)).thenReturn(
				"test-parameter=test-value&format=json&key=12345");
		Mockito.doReturn(
				"https://api.steampowered.com/ISteamNews/GetNewsForApp/v0002/?test-parameter=test-value&format=json&key=12345")
				.when(requestHandlerHttpsSpy)
				.getRequestUrl("https://", requestMock,
						"test-parameter=test-value&format=json&key=12345");

		String actual = requestHandlerHttpsSpy.getRequestUrl(requestMock);
		assertEquals(
				actual,
				"https://api.steampowered.com/ISteamNews/GetNewsForApp/v0002/?test-parameter=test-value&format=json&key=12345");
	}

	@Test(expectedExceptions = SteamApiException.class)
	public void testGetWebApiResponseUnauthorized()
			throws ClientProtocolException, IOException, SteamApiException {

		when(statusLineMock.getStatusCode()).thenReturn(
				HttpStatus.SC_UNAUTHORIZED);
		when(httpResponseMock.getStatusLine()).thenReturn(statusLineMock);
		when(httpClientMock.execute(any(HttpUriRequest.class))).thenReturn(
				httpResponseMock);
		when(requestHandlerHttpsSpy.getHttpClient()).thenReturn(httpClientMock);

		requestHandlerHttpsSpy.getWebApiResponse("requestUrl");
	}

	@Test(expectedExceptions = SteamApiException.class)
	public void testGetWebApiResponseErrorCode()
			throws ClientProtocolException, IOException, SteamApiException {

		when(statusLineMock.getStatusCode()).thenReturn(
				HttpStatus.SC_INTERNAL_SERVER_ERROR);
		when(httpResponseMock.getStatusLine()).thenReturn(statusLineMock);
		when(httpClientMock.execute(any(HttpUriRequest.class))).thenReturn(
				httpResponseMock);
		when(requestHandlerHttpsSpy.getHttpClient()).thenReturn(httpClientMock);

		requestHandlerHttpsSpy.getWebApiResponse("requestUrl");
	}

	@Test(expectedExceptions = SteamApiException.class)
	public void testGetWebApiResponseIOException()
			throws ClientProtocolException, IOException, SteamApiException {

		when(httpClientMock.execute(any(HttpUriRequest.class))).thenThrow(
				new IOException("intended-io-exception"));
		when(requestHandlerHttpsSpy.getHttpClient()).thenReturn(httpClientMock);

		requestHandlerHttpsSpy.getWebApiResponse("requestUrl");
	}

	@Test
	public void testGetWebApiResponse() throws ClientProtocolException,
			IOException, SteamApiException {

		when(statusLineMock.getStatusCode()).thenReturn(HttpStatus.SC_OK);
		when(httpResponseMock.getStatusLine()).thenReturn(statusLineMock);
		when(httpResponseMock.getEntity()).thenReturn(httpEntityMock);
		when(httpClientMock.execute(any(HttpUriRequest.class))).thenReturn(
				httpResponseMock);

		when(requestHandlerHttpsSpy.getHttpClient()).thenReturn(httpClientMock);

		requestHandlerHttpsSpy.getWebApiResponse(requestMock);
		verify(requestHandlerHttpsSpy)
				.getHttpResponseAsString(httpResponseMock);
	}
}
