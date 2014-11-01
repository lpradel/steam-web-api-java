package com.lukaspradel.steamapi.webapi.client;

import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.mockito.Mock;
import org.powermock.reflect.Whitebox;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.lukaspradel.steamapi.BaseTest;
import com.lukaspradel.steamapi.core.exception.SteamApiException;
import com.lukaspradel.steamapi.data.json.appnews.GetNewsForApp;
import com.lukaspradel.steamapi.webapi.request.GetNewsForAppRequest;
import com.lukaspradel.steamapi.webapi.request.SteamWebApiRequestHandler;
import com.lukaspradel.steamapi.webapi.request.builders.SteamWebApiRequestFactory;

public class SteamWebApiClientTest extends BaseTest {

	private static final String KEY_MOCK = "12345";

	private SteamWebApiClient client = new SteamWebApiClient.SteamWebApiClientBuilder(
			KEY_MOCK).build();

	@Mock
	private SteamWebApiRequestHandler requestHandlerMock = new SteamWebApiRequestHandler(
			true, KEY_MOCK);

	@BeforeMethod
	public void init() {

		Whitebox.setInternalState(client, "requestHandler", requestHandlerMock);
	}

	@Test
	public void testProcessRequest() throws SteamApiException, IOException {

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
}
