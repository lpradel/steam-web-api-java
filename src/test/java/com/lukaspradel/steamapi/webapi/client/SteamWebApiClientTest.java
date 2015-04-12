package com.lukaspradel.steamapi.webapi.client;

import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.mockito.Mock;
import org.powermock.reflect.Whitebox;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.lukaspradel.steamapi.BaseTest;
import com.lukaspradel.steamapi.core.exception.SteamApiException;
import com.lukaspradel.steamapi.data.json.achievementpercentages.GetGlobalAchievementPercentagesForApp;
import com.lukaspradel.steamapi.data.json.appnews.GetNewsForApp;
import com.lukaspradel.steamapi.data.json.playersummaries.GetPlayerSummaries;
import com.lukaspradel.steamapi.webapi.request.GetGlobalAchievementPercentagesForAppRequest;
import com.lukaspradel.steamapi.webapi.request.GetNewsForAppRequest;
import com.lukaspradel.steamapi.webapi.request.GetPlayerSummariesRequest;
import com.lukaspradel.steamapi.webapi.request.SteamWebApiRequest;
import com.lukaspradel.steamapi.webapi.request.SteamWebApiRequestHandler;
import com.lukaspradel.steamapi.webapi.request.builders.SteamWebApiRequestFactory;

public class SteamWebApiClientTest extends BaseTest {

	private static final String KEY_MOCK = "12345";

	private SteamWebApiClient client = new SteamWebApiClient.SteamWebApiClientBuilder(
			KEY_MOCK).build();

	@Mock
	private SteamWebApiRequest requestMock;

	@Mock
	private SteamWebApiRequestHandler requestHandlerMock = new SteamWebApiRequestHandler(
			true, KEY_MOCK);

	@BeforeMethod
	public void init() {

		Whitebox.setInternalState(client, "requestHandler", requestHandlerMock);
	}

	@Test(expectedExceptions = SteamApiException.class)
	public void testProcessExceptionMapping() throws SteamApiException {

		when(requestHandlerMock.getWebApiResponse(requestMock)).thenThrow(
				new SteamApiException(SteamApiException.Cause.MAPPING,
						new Throwable()));

		client.processRequest(requestMock);
	}

	@Test(expectedExceptions = SteamApiException.class)
	public void testProcessExceptionUnexpectedError() throws SteamApiException {

		when(requestHandlerMock.getWebApiResponse(requestMock)).thenThrow(
				new SteamApiException(SteamApiException.Cause.INTERNAL_ERROR,
						new Throwable()));

		client.processRequest(requestMock);
	}

	@Test(expectedExceptions = SteamApiException.class)
	public void testProcessExceptionHttpError() throws SteamApiException {

		when(requestHandlerMock.getWebApiResponse(requestMock)).thenThrow(
				new SteamApiException(SteamApiException.Cause.HTTP_ERROR,
						Integer.valueOf(404), "message"));

		client.processRequest(requestMock);
	}

	@Test(expectedExceptions = SteamApiException.class)
	public void testProcessExceptionForbiddenError() throws SteamApiException {

		when(requestHandlerMock.getWebApiResponse(requestMock)).thenThrow(
				new SteamApiException(SteamApiException.Cause.FORBIDDEN,
						Integer.valueOf(403), "message"));

		client.processRequest(requestMock);
	}

	@Test(expectedExceptions = SteamApiException.class)
	public void testProcessExceptionInternalError() throws SteamApiException {

		when(requestHandlerMock.getWebApiResponse(requestMock)).thenThrow(
				new SteamApiException(SteamApiException.Cause.INTERNAL_ERROR,
						Integer.valueOf(500), "message"));

		client.processRequest(requestMock);
	}

	@Test(expectedExceptions = SteamApiException.class)
	public void testProcessExceptionUnexpectedStatusError()
			throws SteamApiException {

		when(requestHandlerMock.getWebApiResponse(requestMock)).thenThrow(
				new SteamApiException(SteamApiException.Cause.MAPPING, Integer
						.valueOf(0), "message"));

		client.processRequest(requestMock);
	}

	@Test
	public void testProcessExceptionMessage() throws SteamApiException {

		when(requestHandlerMock.getWebApiResponse(requestMock)).thenThrow(
				new SteamApiException(SteamApiException.Cause.HTTP_ERROR,
						Integer.valueOf(404), "message"));

		try {
			client.processRequest(requestMock);

			fail("An exception of type SteamApiException should have been thrown here!");
		} catch (SteamApiException e) {
			assertEquals(
					e.getMessage(),
					"The Web API request failed with the following HTTP error: message (status code: 404).");
		}
	}

	@Test
	public void testProcessGetNewsForAppRequest() throws SteamApiException,
			IOException {

		GetNewsForAppRequest getNewsForAppRequest = SteamWebApiRequestFactory
				.createGetNewsForAppRequest(0);

		String mockAnswer = readResourceAsString("GetNewsForApp.json");

		when(requestHandlerMock.getWebApiResponse(getNewsForAppRequest))
				.thenReturn(mockAnswer);

		GetNewsForApp getNewsForApp = client
				.<GetNewsForApp> processRequest(getNewsForAppRequest);

		assertNotNull(getNewsForApp);
		assertTrue(getNewsForApp.getAdditionalProperties().isEmpty());
		assertNotNull(getNewsForApp.getAppnews());
		assertEquals(getNewsForApp.getAppnews().getAppid(),
				Integer.valueOf(440));
		assertEquals(getNewsForApp.getAppnews().getNewsitems().size(), 3);
	}

	@Test
	public void testProcessGetGlobalAchievementPercentagesForAppRequest()
			throws SteamApiException, IOException {

		GetGlobalAchievementPercentagesForAppRequest getGlobalAchievementPercentagesForAppRequest = SteamWebApiRequestFactory
				.createGetGlobalAchievementPercentagesForAppRequest(0);

		String mockAnswer = readResourceAsString("GetGlobalAchievementPercentagesForApp.json");

		when(
				requestHandlerMock
						.getWebApiResponse(getGlobalAchievementPercentagesForAppRequest))
				.thenReturn(mockAnswer);

		GetGlobalAchievementPercentagesForApp getGlobalAchievementPercentagesForApp = client
				.<GetGlobalAchievementPercentagesForApp> processRequest(getGlobalAchievementPercentagesForAppRequest);

		assertNotNull(getGlobalAchievementPercentagesForApp);
		assertTrue(getGlobalAchievementPercentagesForApp
				.getAdditionalProperties().isEmpty());
		assertNotNull(getGlobalAchievementPercentagesForApp
				.getAchievementpercentages());
		assertEquals(
				getGlobalAchievementPercentagesForApp
						.getAchievementpercentages().getAchievements().get(0)
						.getName(), "no_one_cared_who_i_was");
		assertEquals(getGlobalAchievementPercentagesForApp
				.getAchievementpercentages().getAchievements().size(), 316);
	}

	@Test
	public void testProcessGetPlayerSummariesRequest()
			throws SteamApiException, IOException {

		List<String> steamIds = new ArrayList<String>();
		steamIds.add("123");
		steamIds.add("456");
		steamIds.add("789");

		GetPlayerSummariesRequest getPlayerSummariesRequest = SteamWebApiRequestFactory
				.createGetPlayerSummariesRequest(steamIds);

		String mockAnswer = readResourceAsString("GetPlayerSummaries.json");

		when(requestHandlerMock.getWebApiResponse(getPlayerSummariesRequest))
				.thenReturn(mockAnswer);

		GetPlayerSummaries getPlayerSummaries = client
				.<GetPlayerSummaries> processRequest(getPlayerSummariesRequest);

		assertNotNull(getPlayerSummaries);
		assertTrue(getPlayerSummaries.getAdditionalProperties().isEmpty());
		assertNotNull(getPlayerSummaries.getResponse());
		assertNotNull(getPlayerSummaries.getResponse().getPlayers());
		assertEquals(getPlayerSummaries.getResponse().getPlayers().size(), 1);
		assertEquals(getPlayerSummaries.getResponse().getPlayers().get(0)
				.getSteamid(), "76561197960435530");
	}
}
