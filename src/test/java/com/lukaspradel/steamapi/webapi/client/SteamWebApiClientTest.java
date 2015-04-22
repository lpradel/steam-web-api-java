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
import com.lukaspradel.steamapi.data.json.friendslist.GetFriendList;
import com.lukaspradel.steamapi.data.json.ownedgames.GetOwnedGames;
import com.lukaspradel.steamapi.data.json.playerachievements.GetPlayerAchievements;
import com.lukaspradel.steamapi.data.json.playerstats.GetUserStatsForGame;
import com.lukaspradel.steamapi.data.json.playersummaries.GetPlayerSummaries;
import com.lukaspradel.steamapi.webapi.request.GetFriendListRequest;
import com.lukaspradel.steamapi.webapi.request.GetFriendListRequest.Relationship;
import com.lukaspradel.steamapi.webapi.request.GetGlobalAchievementPercentagesForAppRequest;
import com.lukaspradel.steamapi.webapi.request.GetNewsForAppRequest;
import com.lukaspradel.steamapi.webapi.request.GetOwnedGamesRequest;
import com.lukaspradel.steamapi.webapi.request.GetPlayerAchievementsRequest;
import com.lukaspradel.steamapi.webapi.request.GetPlayerSummariesRequest;
import com.lukaspradel.steamapi.webapi.request.GetUserStatsForGameRequest;
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

	@Test
	public void testProcessGetFriendListRequest() throws SteamApiException,
			IOException {

		GetFriendListRequest getFriendListRequest = SteamWebApiRequestFactory
				.createGetFriendListRequest("76561197960435530");

		String mockAnswer = readResourceAsString("GetFriendList.json");

		when(requestHandlerMock.getWebApiResponse(getFriendListRequest))
				.thenReturn(mockAnswer);

		GetFriendList getFriendList = client
				.<GetFriendList> processRequest(getFriendListRequest);

		assertNotNull(getFriendList);
		assertTrue(getFriendList.getAdditionalProperties().isEmpty());
		assertNotNull(getFriendList.getFriendslist());
		assertNotNull(getFriendList.getFriendslist().getFriends());
		assertEquals(getFriendList.getFriendslist().getFriends().size(), 291);
		assertEquals(getFriendList.getFriendslist().getFriends().get(5)
				.getSteamid(), "76561197960268093");
		assertEquals(getFriendList.getFriendslist().getFriends().get(5)
				.getRelationship(), Relationship.FRIEND.toString());
		assertEquals(getFriendList.getFriendslist().getFriends().get(5)
				.getFriendSince(), Integer.valueOf(1251433222));
	}

	@Test
	public void testProcessGetPlayerAchievementsRequest()
			throws SteamApiException, IOException {

		GetPlayerAchievementsRequest getPlayerAchievementsRequest = SteamWebApiRequestFactory
				.createGetPlayerAchievementsRequest(123, "76561197960435530");

		String mockAnswer = readResourceAsString("GetPlayerAchievements.json");

		when(requestHandlerMock.getWebApiResponse(getPlayerAchievementsRequest))
				.thenReturn(mockAnswer);

		GetPlayerAchievements getPlayerAchievements = client
				.<GetPlayerAchievements> processRequest(getPlayerAchievementsRequest);

		assertNotNull(getPlayerAchievements);
		assertTrue(getPlayerAchievements.getAdditionalProperties().isEmpty());
		assertNotNull(getPlayerAchievements.getPlayerstats());
		assertEquals(getPlayerAchievements.getPlayerstats().getGameName(),
				"Team Fortress 2");
		assertEquals(getPlayerAchievements.getPlayerstats().getSteamID(),
				"76561197972495328");
		assertEquals(getPlayerAchievements.getPlayerstats().getSuccess(),
				Boolean.TRUE);
		assertNotNull(getPlayerAchievements.getPlayerstats().getAchievements());
		assertEquals(getPlayerAchievements.getPlayerstats().getAchievements()
				.size(), 513);
		assertEquals(getPlayerAchievements.getPlayerstats().getAchievements()
				.get(1).getApiname(), "TF_PLAY_GAME_EVERYMAP");
		assertEquals(getPlayerAchievements.getPlayerstats().getAchievements()
				.get(1).getAchieved(), Integer.valueOf(1));
	}

	@Test
	public void testProcessGetUserStatsForGameRequest()
			throws SteamApiException, IOException {

		GetUserStatsForGameRequest getUserStatsForGameRequest = SteamWebApiRequestFactory
				.createGetUserStatsForGameRequest(123, "76561197960435530");

		String mockAnswer = readResourceAsString("GetUserStatsForGame.json");

		when(requestHandlerMock.getWebApiResponse(getUserStatsForGameRequest))
				.thenReturn(mockAnswer);

		GetUserStatsForGame getUserStatsForGame = client
				.<GetUserStatsForGame> processRequest(getUserStatsForGameRequest);

		assertNotNull(getUserStatsForGame);
		assertTrue(getUserStatsForGame.getAdditionalProperties().isEmpty());
		assertNotNull(getUserStatsForGame.getPlayerstats());
		assertEquals(getUserStatsForGame.getPlayerstats().getGameName(),
				"Team Fortress 2");
		assertEquals(getUserStatsForGame.getPlayerstats().getSteamID(),
				"76561197972495328");

		assertNotNull(getUserStatsForGame.getPlayerstats().getStats());
		assertEquals(getUserStatsForGame.getPlayerstats().getStats().size(),
				459);
		assertEquals(getUserStatsForGame.getPlayerstats().getStats().get(2)
				.getName(), "Scout.accum.iDominations");
		assertEquals(getUserStatsForGame.getPlayerstats().getStats().get(2)
				.getValue(), Integer.valueOf(68));

		assertNotNull(getUserStatsForGame.getPlayerstats().getAchievements());
		assertEquals(getUserStatsForGame.getPlayerstats().getAchievements()
				.size(), 393);
		assertEquals(getUserStatsForGame.getPlayerstats().getAchievements()
				.get(1).getName(), "TF_PLAY_GAME_EVERYMAP");
		assertEquals(getUserStatsForGame.getPlayerstats().getAchievements()
				.get(1).getAchieved(), Integer.valueOf(1));
	}

	@Test
	public void testProcessGetOwnedGamesRequest() throws SteamApiException,
			IOException {

		GetOwnedGamesRequest getOwnedGamesRequest = SteamWebApiRequestFactory
				.createGetOwnedGamesRequest("76561197960435530");

		String mockAnswer = readResourceAsString("GetOwnedGames.json");

		when(requestHandlerMock.getWebApiResponse(getOwnedGamesRequest))
				.thenReturn(mockAnswer);

		GetOwnedGames getOwnedGames = client
				.<GetOwnedGames> processRequest(getOwnedGamesRequest);

		assertNotNull(getOwnedGames);
		assertTrue(getOwnedGames.getAdditionalProperties().isEmpty());
		assertNotNull(getOwnedGames.getResponse());
		assertEquals(getOwnedGames.getResponse().getGameCount(),
				Integer.valueOf(487));
		assertNotNull(getOwnedGames.getResponse().getGames());
		assertEquals(getOwnedGames.getResponse().getGames().size(), 487);

		assertEquals(getOwnedGames.getResponse().getGames().get(0).getAppid(),
				Integer.valueOf(10));
		assertEquals(getOwnedGames.getResponse().getGames().get(0).getName(),
				"Counter-Strike");
		assertEquals(getOwnedGames.getResponse().getGames().get(0)
				.getPlaytimeForever(), Integer.valueOf(32));
		assertEquals(getOwnedGames.getResponse().getGames().get(0)
				.getImgIconUrl(), "6b0312cda02f5f777efa2f3318c307ff9acafbb5");
		assertEquals(getOwnedGames.getResponse().getGames().get(0)
				.getImgLogoUrl(), "af890f848dd606ac2fd4415de3c3f5e7a66fcb9f");
		assertEquals(getOwnedGames.getResponse().getGames().get(0)
				.getHasCommunityVisibleStats(), Boolean.TRUE);
	}
}
