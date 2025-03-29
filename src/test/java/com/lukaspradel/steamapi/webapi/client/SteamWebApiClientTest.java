package com.lukaspradel.steamapi.webapi.client;

import com.lukaspradel.steamapi.BaseTest;
import com.lukaspradel.steamapi.core.exception.SteamApiException;
import com.lukaspradel.steamapi.data.json.achievementpercentages.GetGlobalAchievementPercentagesForApp;
import com.lukaspradel.steamapi.data.json.appnews.GetNewsForApp;
import com.lukaspradel.steamapi.data.json.dota2.fantasyplayerstats.GetFantasyPlayerStats;
import com.lukaspradel.steamapi.data.json.dota2.gameitems.GetGameItems;
import com.lukaspradel.steamapi.data.json.dota2.heroes.GetHeroes;
import com.lukaspradel.steamapi.data.json.dota2.leaguelisting.GetLeagueListing;
import com.lukaspradel.steamapi.data.json.dota2.liveleaguegames.GetLiveLeagueGames;
import com.lukaspradel.steamapi.data.json.dota2.matchdetails.GetMatchDetails;
import com.lukaspradel.steamapi.data.json.dota2.matchhistory.GetMatchHistory;
import com.lukaspradel.steamapi.data.json.dota2.matchhistorybysequencenum.GetMatchHistoryBySequenceNum;
import com.lukaspradel.steamapi.data.json.dota2.playerofficialinfo.GetPlayerOfficialInfo;
import com.lukaspradel.steamapi.data.json.dota2.proplayerlist.GetProPlayerList;
import com.lukaspradel.steamapi.data.json.dota2.teaminfobyteamid.GetTeamInfoByTeamID;
import com.lukaspradel.steamapi.data.json.friendslist.GetFriendList;
import com.lukaspradel.steamapi.data.json.getglobalstatsforgame.GetGlobalStatsForGame;
import com.lukaspradel.steamapi.data.json.getplayerbans.GetPlayerBans;
import com.lukaspradel.steamapi.data.json.getschemaforgame.GetSchemaForGame;
import com.lukaspradel.steamapi.data.json.isplayingsharedgame.IsPlayingSharedGame;
import com.lukaspradel.steamapi.data.json.ownedgames.GetOwnedGames;
import com.lukaspradel.steamapi.data.json.playerachievements.GetPlayerAchievements;
import com.lukaspradel.steamapi.data.json.playerstats.GetUserStatsForGame;
import com.lukaspradel.steamapi.data.json.playersummaries.GetPlayerSummaries;
import com.lukaspradel.steamapi.data.json.recentlyplayedgames.GetRecentlyPlayedGames;
import com.lukaspradel.steamapi.data.json.resolvevanityurl.ResolveVanityURL;
import com.lukaspradel.steamapi.data.json.resolvevanityurl.Response;
import com.lukaspradel.steamapi.data.json.tf2.getplayeritems.GetPlayerItems;
import com.lukaspradel.steamapi.data.json.tf2.getschemaitems.GetSchemaItems;
import com.lukaspradel.steamapi.data.json.tf2.getschemaoverview.GetSchemaOverview;
import com.lukaspradel.steamapi.webapi.request.GetAppListRequest;
import com.lukaspradel.steamapi.webapi.request.GetFriendListRequest;
import com.lukaspradel.steamapi.webapi.request.GetFriendListRequest.Relationship;
import com.lukaspradel.steamapi.webapi.request.GetGlobalAchievementPercentagesForAppRequest;
import com.lukaspradel.steamapi.webapi.request.GetGlobalStatsForGameRequest;
import com.lukaspradel.steamapi.webapi.request.GetNewsForAppRequest;
import com.lukaspradel.steamapi.webapi.request.GetOwnedGamesRequest;
import com.lukaspradel.steamapi.webapi.request.GetPlayerAchievementsRequest;
import com.lukaspradel.steamapi.webapi.request.GetPlayerBansRequest;
import com.lukaspradel.steamapi.webapi.request.GetPlayerSummariesRequest;
import com.lukaspradel.steamapi.webapi.request.GetRecentlyPlayedGamesRequest;
import com.lukaspradel.steamapi.webapi.request.GetSchemaForGameRequest;
import com.lukaspradel.steamapi.webapi.request.GetUserStatsForGameRequest;
import com.lukaspradel.steamapi.webapi.request.IsPlayingSharedGameRequest;
import com.lukaspradel.steamapi.webapi.request.ResolveVanityUrlRequest;
import com.lukaspradel.steamapi.webapi.request.SteamWebApiRequest;
import com.lukaspradel.steamapi.webapi.request.SteamWebApiRequestHandler;
import com.lukaspradel.steamapi.webapi.request.builders.SteamWebApiRequestFactory;
import com.lukaspradel.steamapi.webapi.request.dota2.GetFantasyPlayerStatsRequest;
import com.lukaspradel.steamapi.webapi.request.dota2.GetGameItemsRequest;
import com.lukaspradel.steamapi.webapi.request.dota2.GetHeroesRequest;
import com.lukaspradel.steamapi.webapi.request.dota2.GetLeagueListingRequest;
import com.lukaspradel.steamapi.webapi.request.dota2.GetLiveLeagueGamesRequest;
import com.lukaspradel.steamapi.webapi.request.dota2.GetMatchDetailsRequest;
import com.lukaspradel.steamapi.webapi.request.dota2.GetMatchHistoryBySequenceNumRequest;
import com.lukaspradel.steamapi.webapi.request.dota2.GetMatchHistoryRequest;
import com.lukaspradel.steamapi.webapi.request.dota2.GetPlayerOfficialInfoRequest;
import com.lukaspradel.steamapi.webapi.request.dota2.GetProPlayerListRequest;
import com.lukaspradel.steamapi.webapi.request.dota2.GetTeamInfoByTeamIDRequest;
import com.lukaspradel.steamapi.webapi.request.tf2.GetPlayerItemsRequest;
import com.lukaspradel.steamapi.webapi.request.tf2.GetSchemaItemsRequest;
import com.lukaspradel.steamapi.webapi.request.tf2.GetSchemaOverviewRequest;
import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

public class SteamWebApiClientTest extends BaseTest {

	@Mock
	private SteamWebApiRequest requestMock;

	@Mock
	private SteamWebApiRequestHandler requestHandlerMock;

	private SteamWebApiClient client;

	@BeforeMethod
	public void init() {
		client = spy(new SteamWebApiClient(requestHandlerMock));
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
						Integer.valueOf(404)));

		client.processRequest(requestMock);
	}

	@Test(expectedExceptions = SteamApiException.class)
	public void testProcessExceptionForbiddenError() throws SteamApiException {

		when(requestHandlerMock.getWebApiResponse(requestMock)).thenThrow(
				new SteamApiException(SteamApiException.Cause.FORBIDDEN,
						Integer.valueOf(403)));

		client.processRequest(requestMock);
	}

	@Test(expectedExceptions = SteamApiException.class)
	public void testProcessExceptionInternalError() throws SteamApiException {

		when(requestHandlerMock.getWebApiResponse(requestMock)).thenThrow(
				new SteamApiException(SteamApiException.Cause.INTERNAL_ERROR,
						Integer.valueOf(500)));

		client.processRequest(requestMock);
	}

	@Test(expectedExceptions = SteamApiException.class)
	public void testProcessExceptionUnexpectedStatusError()
			throws SteamApiException {

		when(requestHandlerMock.getWebApiResponse(requestMock)).thenThrow(
				new SteamApiException(SteamApiException.Cause.MAPPING, Integer
						.valueOf(0)));

		client.processRequest(requestMock);
	}

	@Test
	public void testProcessExceptionMessage() throws SteamApiException {

		when(requestHandlerMock.getWebApiResponse(requestMock)).thenThrow(
				new SteamApiException(SteamApiException.Cause.HTTP_ERROR,
						Integer.valueOf(404)));

		try {
			client.processRequest(requestMock);

			fail("An exception of type SteamApiException should have been thrown here!");
		} catch (SteamApiException e) {
			assertEquals(
					e.getMessage(),
					"The Web API request failed (status code: 404).");
		}
	}

	@Test(expectedExceptions = SteamApiException.class)
	public void testProcessIncorrectResponse() throws SteamApiException {
        GetNewsForAppRequest getNewsForAppRequest = SteamWebApiRequestFactory
                .createGetNewsForAppRequest(0);

        String mockAnswer = "incorrect answer";
		when(requestHandlerMock.getWebApiResponse(getNewsForAppRequest)).thenReturn(mockAnswer);

        client.<GetNewsForApp> processRequest(getNewsForAppRequest);
		fail("An exception of type SteamApiException should have been thrown here!");
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
				Long.valueOf(440));
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
	public void testProcessGetGlobalStatsForGameRequest()
			throws SteamApiException, IOException {

		int gameId = 400;
		int count = 1;

		List<String> names = new ArrayList<String>();
		names.add("global.map.emp_isle");

		GetGlobalStatsForGameRequest getGlobalStatsForGameRequest = SteamWebApiRequestFactory
				.createGetGlobalStatsForGameRequest(gameId, count, names);

		String mockAnswer = readResourceAsString("GetGlobalStatsForGame.json");

		when(requestHandlerMock.getWebApiResponse(getGlobalStatsForGameRequest))
				.thenReturn(mockAnswer);

		GetGlobalStatsForGame getGlobalStatsForGame = client
				.<GetGlobalStatsForGame> processRequest(getGlobalStatsForGameRequest);

		assertNotNull(getGlobalStatsForGame);
		assertTrue(getGlobalStatsForGame.getAdditionalProperties().isEmpty());
		assertNotNull(getGlobalStatsForGame.getResponse());
		assertEquals(getGlobalStatsForGame.getResponse().getResult(),
				Long.valueOf(count));
		assertNotNull(getGlobalStatsForGame.getResponse().getGlobalstats());

		assertFalse(getGlobalStatsForGame.getResponse().getGlobalstats()
				.getAdditionalProperties().isEmpty());
		Map<String, Object> additionalProps = getGlobalStatsForGame
				.getResponse().getGlobalstats().getAdditionalProperties();

		assertNotNull(additionalProps.get("global.map.emp_isle"));

		Map<?,?> achievementAdditionalProps = (Map<?,?>) additionalProps
				.get("global.map.emp_isle");
		assertNotNull(achievementAdditionalProps.get("total"));
		assertEquals(achievementAdditionalProps.get("total"), "11023654915");
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
				.getFriendSince(), Long.valueOf(1251433222));
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
				.get(1).getAchieved(), Long.valueOf(1));
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
				.getValue(), Long.valueOf(68));

		assertNotNull(getUserStatsForGame.getPlayerstats().getAchievements());
		assertEquals(getUserStatsForGame.getPlayerstats().getAchievements()
				.size(), 393);
		assertEquals(getUserStatsForGame.getPlayerstats().getAchievements()
				.get(1).getName(), "TF_PLAY_GAME_EVERYMAP");
		assertEquals(getUserStatsForGame.getPlayerstats().getAchievements()
				.get(1).getAchieved(), Long.valueOf(1));
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
				Long.valueOf(487));
		assertNotNull(getOwnedGames.getResponse().getGames());
		assertEquals(getOwnedGames.getResponse().getGames().size(), 487);

		assertEquals(getOwnedGames.getResponse().getGames().get(0).getAppid(),
				Long.valueOf(10));
		assertEquals(getOwnedGames.getResponse().getGames().get(0).getName(),
				"Counter-Strike");
		assertEquals(getOwnedGames.getResponse().getGames().get(0)
				.getPlaytimeForever(), Long.valueOf(32));
		assertEquals(getOwnedGames.getResponse().getGames().get(0)
				.getImgIconUrl(), "6b0312cda02f5f777efa2f3318c307ff9acafbb5");
		assertEquals(getOwnedGames.getResponse().getGames().get(0)
				.getImgLogoUrl(), "af890f848dd606ac2fd4415de3c3f5e7a66fcb9f");
		assertEquals(getOwnedGames.getResponse().getGames().get(0)
				.getHasCommunityVisibleStats(), Boolean.TRUE);
	}

	@Test
	public void testProcessGetRecentlyPlayedGamesRequest()
			throws SteamApiException, IOException {

		GetRecentlyPlayedGamesRequest getRecentlyPlayedGamesRequest = SteamWebApiRequestFactory
				.createGetRecentlyPlayedGamesRequest("76561197960435530");

		String mockAnswer = readResourceAsString("GetRecentlyPlayedGames.json");

		when(
				requestHandlerMock
						.getWebApiResponse(getRecentlyPlayedGamesRequest))
				.thenReturn(mockAnswer);

		GetRecentlyPlayedGames getRecentlyPlayedGames = client
				.<GetRecentlyPlayedGames> processRequest(getRecentlyPlayedGamesRequest);

		assertNotNull(getRecentlyPlayedGames);
		assertTrue(getRecentlyPlayedGames.getAdditionalProperties().isEmpty());
		assertNotNull(getRecentlyPlayedGames.getResponse());
		assertEquals(getRecentlyPlayedGames.getResponse().getTotalCount(),
				Long.valueOf(6));
		assertNotNull(getRecentlyPlayedGames.getResponse().getGames());
		assertEquals(getRecentlyPlayedGames.getResponse().getGames().size(), 3);

		assertEquals(getRecentlyPlayedGames.getResponse().getGames().get(0)
				.getAppid(), Long.valueOf(271590));
		assertEquals(getRecentlyPlayedGames.getResponse().getGames().get(0)
				.getName(), "Grand Theft Auto V");
		assertEquals(getRecentlyPlayedGames.getResponse().getGames().get(0)
				.getPlaytime2weeks(), Long.valueOf(190));
		assertEquals(getRecentlyPlayedGames.getResponse().getGames().get(0)
				.getPlaytimeForever(), Long.valueOf(190));
		assertEquals(getRecentlyPlayedGames.getResponse().getGames().get(0)
				.getImgIconUrl(), "1e72f87eb927fa1485e68aefaff23c7fd7178251");
		assertEquals(getRecentlyPlayedGames.getResponse().getGames().get(0)
				.getImgLogoUrl(), "e447e82f8b0c67f9e001498503c62f2a187bc609");
	}

	@Test
	public void testProcessIsPlayingSharedGameRequest()
			throws SteamApiException, IOException {

		IsPlayingSharedGameRequest getIsPlayingSharedGameRequest = SteamWebApiRequestFactory
				.createIsPlayingSharedGameRequest("76561197960435530", 20);

		String mockAnswer = readResourceAsString("IsPlayingSharedGame.json");

		when(
				requestHandlerMock
						.getWebApiResponse(getIsPlayingSharedGameRequest))
				.thenReturn(mockAnswer);

		IsPlayingSharedGame isPlayingSharedGame = client
				.<IsPlayingSharedGame> processRequest(getIsPlayingSharedGameRequest);

		assertNotNull(isPlayingSharedGame);
		assertTrue(isPlayingSharedGame.getAdditionalProperties().isEmpty());
		assertNotNull(isPlayingSharedGame.getResponse());

		assertNotNull(isPlayingSharedGame.getResponse().getLenderSteamid());
		assertEquals(isPlayingSharedGame.getResponse().getLenderSteamid(),
				"123");
	}

	@Test
	public void testProcessGetSchemaForGameRequest() throws SteamApiException,
			IOException {

		GetSchemaForGameRequest getSchemaForGameRequest = SteamWebApiRequestFactory
				.createGetSchemaForGameRequest(123);

		String mockAnswer = readResourceAsString("GetSchemaForGame.json");

		when(requestHandlerMock.getWebApiResponse(getSchemaForGameRequest))
				.thenReturn(mockAnswer);

		GetSchemaForGame getSchemaForGame = client
				.<GetSchemaForGame> processRequest(getSchemaForGameRequest);

		assertNotNull(getSchemaForGame);
		assertTrue(getSchemaForGame.getAdditionalProperties().isEmpty());
		assertNotNull(getSchemaForGame.getGame());

		assertEquals(getSchemaForGame.getGame().getGameName(), "PAYDAY 2");
		assertEquals(getSchemaForGame.getGame().getGameVersion(), "275");
		assertNotNull(getSchemaForGame.getGame().getAvailableGameStats());

		assertEquals(getSchemaForGame.getGame().getAvailableGameStats()
				.getAchievements().size(), 311);
		assertEquals(getSchemaForGame.getGame().getAvailableGameStats()
				.getAchievements().get(0).getName(), "hot_wheels");
		assertEquals(getSchemaForGame.getGame().getAvailableGameStats()
				.getAchievements().get(0).getDefaultvalue(), Long.valueOf(0));
		assertEquals(getSchemaForGame.getGame().getAvailableGameStats()
				.getAchievements().get(0).getDisplayName(), "Coming in Hot");
		assertEquals(getSchemaForGame.getGame().getAvailableGameStats()
				.getAchievements().get(0).getHidden(), Long.valueOf(0));
		assertEquals(
				getSchemaForGame.getGame().getAvailableGameStats()
						.getAchievements().get(0).getDescription(),
				"On day 1 of the Watchdogs job, don't let the cops shoot and destroy the escape vehicle.");
		assertEquals(
				getSchemaForGame.getGame().getAvailableGameStats()
						.getAchievements().get(0).getIcon(),
				"http://cdn.akamai.steamstatic.com/steamcommunity/public/images/apps/218620/f6ed9cd6ec9750bcd36193c74e6f16104f6c1267.jpg");
		assertEquals(
				getSchemaForGame.getGame().getAvailableGameStats()
						.getAchievements().get(0).getIcongray(),
				"http://cdn.akamai.steamstatic.com/steamcommunity/public/images/apps/218620/c336adacd88a21a6010c9b5596192322aecaf265.jpg");

		assertEquals(getSchemaForGame.getGame().getAvailableGameStats()
				.getStats().size(), 1100);
	}

	@Test
	public void testProcessGetPlayerBansRequest() throws SteamApiException,
			IOException {

		List<String> steamIds = new ArrayList<String>();
		steamIds.add("76561198051682777");
		steamIds.add("76561198051682776");

		GetPlayerBansRequest getPlayerBansRequest = SteamWebApiRequestFactory
				.createGetPlayerBansRequest(steamIds);

		String mockAnswer = readResourceAsString("GetPlayerBans.json");

		when(requestHandlerMock.getWebApiResponse(getPlayerBansRequest))
				.thenReturn(mockAnswer);

		GetPlayerBans getPlayerBans = client
				.<GetPlayerBans> processRequest(getPlayerBansRequest);

		assertNotNull(getPlayerBans);
		assertTrue(getPlayerBans.getAdditionalProperties().isEmpty());
		assertNotNull(getPlayerBans.getPlayers());

		assertEquals(getPlayerBans.getPlayers().size(), 2);
		assertEquals(getPlayerBans.getPlayers().get(0).getSteamId(),
				"76561198051682777");
		assertEquals(getPlayerBans.getPlayers().get(0).getCommunityBanned(),
				Boolean.FALSE);
		assertEquals(getPlayerBans.getPlayers().get(0).getVACBanned(),
				Boolean.FALSE);
		assertEquals(getPlayerBans.getPlayers().get(0).getNumberOfVACBans(),
				Long.valueOf(0));
		assertEquals(getPlayerBans.getPlayers().get(0).getDaysSinceLastBan(),
				Long.valueOf(0));
		assertEquals(getPlayerBans.getPlayers().get(0).getEconomyBan(), "none");
	}

	@Test
	public void testProcessFantasyPlayerStatsRequest() throws SteamApiException,
			IOException {
		GetFantasyPlayerStatsRequest request = SteamWebApiRequestFactory.createGetFantasyPlayerStatsRequest("2");

		String mockAnswer = readResourceAsString("dota2/GetFantasyPlayerStats.json");

		when(requestHandlerMock.getWebApiResponse(request))
				.thenReturn(mockAnswer);

		GetFantasyPlayerStats getFantasyPlayerStats = client.<GetFantasyPlayerStats> processRequest(request);

		assertNotNull(getFantasyPlayerStats);
		assertTrue(getFantasyPlayerStats.getAdditionalProperties().isEmpty());
		assertNotNull(getFantasyPlayerStats.getResult());
		assertTrue(getFantasyPlayerStats.getResult().getAdditionalProperties().isEmpty());
		assertFalse(getFantasyPlayerStats.getResult().getStatsList().isEmpty());

		assertNotNull(getFantasyPlayerStats.getResult().getStatsList().get(1).getAverageDeaths());
		assertNotNull(getFantasyPlayerStats.getResult().getStatsList().get(1).getAverageAssists());
		assertNotNull(getFantasyPlayerStats.getResult().getStatsList().get(1).getAverageGPM());
		assertNotNull(getFantasyPlayerStats.getResult().getStatsList().get(1).getAverageHealing());
		assertNotNull(getFantasyPlayerStats.getResult().getStatsList().get(1).getAverageKills());
		assertNotNull(getFantasyPlayerStats.getResult().getStatsList().get(1).getAverageLastHits());
		assertNotNull(getFantasyPlayerStats.getResult().getStatsList().get(1).getAverageDenies());
		assertNotNull(getFantasyPlayerStats.getResult().getStatsList().get(1).getAverageLevel());
		assertNotNull(getFantasyPlayerStats.getResult().getStatsList().get(1).getAverageRoshanKills());
		assertNotNull(getFantasyPlayerStats.getResult().getStatsList().get(1).getAverageStuns());
		assertNotNull(getFantasyPlayerStats.getResult().getStatsList().get(1).getAverageTowerKills());
		assertNotNull(getFantasyPlayerStats.getResult().getStatsList().get(1).getAverageXPPM());
		assertNotNull(getFantasyPlayerStats.getResult().getStatsList().get(1).getMatches());
		assertNotNull(getFantasyPlayerStats.getResult().getStatsList().get(1).getPlayerAccountID());
	}

	@Test
	public void testProcessGameItemsRequest() throws SteamApiException,
			IOException {
		GetGameItemsRequest request = SteamWebApiRequestFactory.createGetGameItemsRequest();

		String mockAnswer = readResourceAsString("dota2/GetGameItems.json");

		when(requestHandlerMock.getWebApiResponse(request))
				.thenReturn(mockAnswer);

		GetGameItems getGameItems = client.<GetGameItems> processRequest(request);

		assertNotNull(getGameItems);
		assertNotNull(getGameItems.getResult());
		assertTrue(getGameItems.getAdditionalProperties().isEmpty());

		assertNotNull(getGameItems.getResult().getStatus());
		assertFalse(getGameItems.getResult().getItems().isEmpty());

		assertNotNull(getGameItems.getResult().getItems().get(0).getId());
		assertNotNull(getGameItems.getResult().getItems().get(0).getCost());
		assertNotNull(getGameItems.getResult().getItems().get(0).getName());
		assertNotNull(getGameItems.getResult().getItems().get(0).getRecipe());
		assertNotNull(getGameItems.getResult().getItems().get(0).getSideShop());
	}

	@Test
	public void testProcessGetHeroesRequest()  throws SteamApiException,
			IOException{
		//GetHeroesRequest request = SteamWebApiRequestFactory.createGetHeroesRequest();
		GetHeroesRequest request = new GetHeroesRequest.GetHeroesRequestBuilder().language("en").buildRequest();


		String mockAnswer = readResourceAsString("dota2/GetHeroes.json");

		when(requestHandlerMock.getWebApiResponse(request))
				.thenReturn(mockAnswer);

		GetHeroes getHeroes = client.<GetHeroes> processRequest(request);

		assertNotNull(getHeroes);
		assertTrue(getHeroes.getAdditionalProperties().isEmpty());
		assertNotNull(getHeroes.getResult());

		assertNotNull(getHeroes.getResult().getStatus());
		assertEquals(getHeroes.getResult().getStatus(), Long.valueOf(200));
		assertEquals(getHeroes.getResult().getCount(),Long.valueOf(111));
		assertEquals(getHeroes.getResult().getHeroes().size(),111);
		assertNotNull(getHeroes.getResult().getHeroes().get(0).getId());
		assertNotNull(getHeroes.getResult().getHeroes().get(0).getName());

	}

	@Test
	public void testProcessGetLeagueListing () throws SteamApiException,
			IOException{
		GetLeagueListingRequest request = SteamWebApiRequestFactory.createGetLeagueListingRequest();

		String mockAnswer = readResourceAsString("dota2/GetLeagueListing.json");

		when(requestHandlerMock.getWebApiResponse(request))
				.thenReturn(mockAnswer);

		GetLeagueListing getLeagueListing = client.<GetLeagueListing> processRequest(request);

		assertNotNull(getLeagueListing);
		assertTrue(getLeagueListing.getAdditionalProperties().isEmpty());
		assertNotNull(getLeagueListing.getResult());

		assertFalse(getLeagueListing.getResult().getLeagues().isEmpty());
		assertNotNull(getLeagueListing.getResult().getLeagues().get(0).getDescription());
		assertNotNull(getLeagueListing.getResult().getLeagues().get(0).getItemdef());
		assertNotNull(getLeagueListing.getResult().getLeagues().get(0).getLeagueid());
		assertNotNull(getLeagueListing.getResult().getLeagues().get(0).getTournamentUrl());
		assertNotNull(getLeagueListing.getResult().getLeagues().get(0).getName());
	}

	@Test
	public void testProcessGetLiveLeagueGames () throws SteamApiException,
			IOException {
		GetLiveLeagueGamesRequest request = SteamWebApiRequestFactory.createGetLiveLeagueGamesRequest();

		String mockAnswer = readResourceAsString("dota2/GetLiveLeagueGames.json");

		when(requestHandlerMock.getWebApiResponse(request))
				.thenReturn(mockAnswer);

		GetLiveLeagueGames getLiveLeagueGames = client.<GetLiveLeagueGames> processRequest(request);

		assertNotNull(getLiveLeagueGames);
		assertTrue(getLiveLeagueGames.getAdditionalProperties().isEmpty());
		assertNotNull(getLiveLeagueGames.getResult());
		assertEquals(getLiveLeagueGames.getResult().getStatus(),Long.valueOf(200));
		assertFalse(getLiveLeagueGames.getResult().getGames().isEmpty());
		assertNotNull(getLiveLeagueGames.getResult().getGames().get(0).getLobbyId());
		assertNotNull(getLiveLeagueGames.getResult().getGames().get(0).getMatchId());
	}

	@Test
	public void testProcessGetMatchDetails() throws SteamApiException,
			IOException {
		GetMatchDetailsRequest request = SteamWebApiRequestFactory.createGetMatchDetailsRequest("2455787494");

		String mockAnswer = readResourceAsString("dota2/GetMatchDetails.json");

		when(requestHandlerMock.getWebApiResponse(request))
				.thenReturn(mockAnswer);

		GetMatchDetails getMatchDetails = client.<GetMatchDetails> processRequest(request);

		assertNotNull(getMatchDetails);
		assertTrue(getMatchDetails.getAdditionalProperties().isEmpty());
		assertNotNull(getMatchDetails.getResult());
	}

	@Test
	public void testProcessGetMatchHistory() throws SteamApiException, IOException {
		GetMatchHistoryRequest request = SteamWebApiRequestFactory.createGetMatchHistoryRequest("123", GetMatchHistoryRequest.GameMode.ALL_PICK);

		String mockAnswer = readResourceAsString("dota2/GetMatchHistory.json");

		when(requestHandlerMock.getWebApiResponse(request))
				.thenReturn(mockAnswer);

		client.<GetMatchHistory> processRequest(request);
	}

	@Test
	public void testProcessGetMatchHistoryBySequenceNum() throws SteamApiException, IOException {
		GetMatchHistoryBySequenceNumRequest request = SteamWebApiRequestFactory.createGetMatchHistoryBySequenceNumRequest(Long.valueOf(100), 20);

		String mockAnswer = readResourceAsString("dota2/GetMatchHistoryBySequenceNum.json");

		when(requestHandlerMock.getWebApiResponse(request))
				.thenReturn(mockAnswer);

		client.<GetMatchHistoryBySequenceNum> processRequest(request);
	}

	@Test
	public void testProcessGetPlayerOfficialInfoRequest() throws SteamApiException, IOException {
		GetPlayerOfficialInfoRequest request = SteamWebApiRequestFactory.createGetPlayerOfficialInfoRequest("3916428");

		String mockAnswer = readResourceAsString("dota2/GetPlayerOfficialInfo.json");

		when(requestHandlerMock.getWebApiResponse(request))
				.thenReturn(mockAnswer);

		client.<GetPlayerOfficialInfo> processRequest(request);
	}

	@Test
	public void testProcessGetProPlayerListRequest() throws SteamApiException, IOException {
		GetProPlayerListRequest request = SteamWebApiRequestFactory.createGetProPlayerListRequest();

		String mockAnswer = readResourceAsString("dota2/GetProPlayerList.json");

		when(requestHandlerMock.getWebApiResponse(request))
				.thenReturn(mockAnswer);

		client.<GetProPlayerList> processRequest(request);
	}

	@Test
	public void testProcessGetTeamInfoByTeamIDRequest() throws SteamApiException, IOException {
		GetTeamInfoByTeamIDRequest request = SteamWebApiRequestFactory.createGetTeamInfoByTeamIDRequest(983699L);

		String mockAnswer = readResourceAsString("dota2/GetTeamInfoByTeamID.json");

		when(requestHandlerMock.getWebApiResponse(request))
				.thenReturn(mockAnswer);

		client.<GetTeamInfoByTeamID> processRequest(request);
	}

	@Test
	public void testProcessGetAppList() throws SteamApiException, IOException {
		GetAppListRequest request = SteamWebApiRequestFactory.createGetAppListRequest();

		String mockAnswer = readResourceAsString("GetAppList.json");

		when(requestHandlerMock.getWebApiResponse(request))
				.thenReturn(mockAnswer);

		client.processRequest(request);
	}

	@Test
	public void testProcessResolveVanityUrlMatch() throws IOException, SteamApiException {
		ResolveVanityUrlRequest request = SteamWebApiRequestFactory.createResolveVanityUrlRequest("", null);

		String mockAnswer = readResourceAsString("ResolveVanityURLMatch.json");

		when(requestHandlerMock.getWebApiResponse(request))
				.thenReturn(mockAnswer);

		ResolveVanityURL result = client.<ResolveVanityURL>processRequest(request);
		assertNotNull(result);

		Response response = result.getResponse();
		assertNotNull(response);
		assertEquals(response.getSteamid(), "123456789");
		assertEquals(response.getSuccess(), 1);
		assertEquals(response.getMessage(), null);
	}

	@Test
	public void testProcessResolveVanityUrlNoMatch() throws IOException, SteamApiException {
		ResolveVanityUrlRequest request = SteamWebApiRequestFactory.createResolveVanityUrlRequest("", null);

		String mockAnswer = readResourceAsString("ResolveVanityURLNoMatch.json");

		when(requestHandlerMock.getWebApiResponse(request))
				.thenReturn(mockAnswer);

		ResolveVanityURL result = client.<ResolveVanityURL>processRequest(request);
		assertNotNull(result);

		Response response = result.getResponse();
		assertNotNull(response);
		assertEquals(response.getSteamid(), null);
		assertEquals(response.getSuccess(), 42);
		assertEquals(response.getMessage(), "No match");
	}

	@Test
	public void testProcessGetPlayerItemsRequest() throws SteamApiException, IOException {

		String steamId = "76561197960435530";

		GetPlayerItemsRequest getPlayerItemsRequest = SteamWebApiRequestFactory
				.createGetPlayerItemsRequest(steamId);

		String mockAnswer = readResourceAsString("tf2/GetPlayerItems.json");

		when(requestHandlerMock.getWebApiResponse(getPlayerItemsRequest))
				.thenReturn(mockAnswer);

		GetPlayerItems getPlayerItems = client
				.<GetPlayerItems> processRequest(getPlayerItemsRequest);

		assertNotNull(getPlayerItems);
		assertNotNull(getPlayerItems.getResult());
		assertEquals(getPlayerItems.getResult().getStatus(), 1);
		assertEquals(getPlayerItems.getResult().getNumBackpackSlots(), 1700);

		// Get Robin Walker's 'Valve' quality items
		var valveQualityItems = getPlayerItems.getResult().getItems()
												.stream()
												.filter(item -> item.getQuality().equals(Long.valueOf(8)))

												.collect(Collectors.toList());

		// He has 6 'Valve' quality items at the time of fetching his items
		assertEquals(valveQualityItems.size(), 6);

		// Find 'Valve Rocket Launcher'
		var valveRocketLauncher = valveQualityItems.stream()
												   .filter(item -> item.getId().equals(Long.valueOf(162307172)))

												   .findFirst();
		assertTrue(valveRocketLauncher.isPresent());
	}

	@Test
	public void testProcessGetSchemaItemsRequest() throws SteamApiException, IOException {

		String language = "en_US";

		GetSchemaItemsRequest getSchemaItemsRequest = SteamWebApiRequestFactory.createGetSchemaItemsRequest(language, null);

		String mockAnswer = readResourceAsString("tf2/GetSchemaItems.json");

		when(requestHandlerMock.getWebApiResponse(getSchemaItemsRequest))
				.thenReturn(mockAnswer);

		GetSchemaItems getSchemaItems = client.processRequest(getSchemaItemsRequest);

		assertNotNull(getSchemaItems);
		assertNotNull(getSchemaItems.getResult());
		assertEquals(getSchemaItems.getResult().getStatus(), 1);
		assertEquals(getSchemaItems.getResult().getItems().size(), 5);
		assertNotNull(getSchemaItems.getResult().getNext());
	}

	@Test
	public void testProcessGetSchemaOverviewRequest() throws SteamApiException, IOException {

		String language = "en_US";

		GetSchemaOverviewRequest getSchemaOverviewRequest = SteamWebApiRequestFactory.createGetSchemaOverviewRequest(language);

		String mockAnswer = readResourceAsString("tf2/GetSchemaOverview.json");

		when(requestHandlerMock.getWebApiResponse(getSchemaOverviewRequest))
				.thenReturn(mockAnswer);

		GetSchemaOverview getSchemaOverview = client.processRequest(getSchemaOverviewRequest);

		assertNotNull(getSchemaOverview);
		assertNotNull(getSchemaOverview.getResult());
		assertEquals(getSchemaOverview.getResult().getStatus(), 1L);
	}
}
