package com.lukaspradel.steamapi.webapi.request.builders;

import java.util.List;

import com.lukaspradel.steamapi.webapi.request.GetFriendListRequest;
import com.lukaspradel.steamapi.webapi.request.GetFriendListRequest.GetFriendListRequestBuilder;
import com.lukaspradel.steamapi.webapi.request.GetFriendListRequest.Relationship;
import com.lukaspradel.steamapi.webapi.request.GetGlobalAchievementPercentagesForAppRequest;
import com.lukaspradel.steamapi.webapi.request.GetGlobalAchievementPercentagesForAppRequest.GetGlobalAchievementPercentagesForAppRequestBuilder;
import com.lukaspradel.steamapi.webapi.request.GetGlobalStatsForGameRequest;
import com.lukaspradel.steamapi.webapi.request.GetGlobalStatsForGameRequest.GetGlobalStatsForGameRequestBuilder;
import com.lukaspradel.steamapi.webapi.request.GetNewsForAppRequest;
import com.lukaspradel.steamapi.webapi.request.GetNewsForAppRequest.GetNewsForAppRequestBuilder;
import com.lukaspradel.steamapi.webapi.request.GetOwnedGamesRequest;
import com.lukaspradel.steamapi.webapi.request.GetOwnedGamesRequest.GetOwnedGamesRequestBuilder;
import com.lukaspradel.steamapi.webapi.request.GetPlayerAchievementsRequest;
import com.lukaspradel.steamapi.webapi.request.GetPlayerAchievementsRequest.GetPlayerAchievementsRequestBuilder;
import com.lukaspradel.steamapi.webapi.request.GetPlayerBansRequest;
import com.lukaspradel.steamapi.webapi.request.GetPlayerBansRequest.GetPlayerBansRequestBuilder;
import com.lukaspradel.steamapi.webapi.request.GetPlayerSummariesRequest;
import com.lukaspradel.steamapi.webapi.request.GetPlayerSummariesRequest.GetPlayerSummariesRequestBuilder;
import com.lukaspradel.steamapi.webapi.request.GetRecentlyPlayedGamesRequest;
import com.lukaspradel.steamapi.webapi.request.GetRecentlyPlayedGamesRequest.GetRecentlyPlayedGamesRequestBuilder;
import com.lukaspradel.steamapi.webapi.request.GetSchemaForGameRequest;
import com.lukaspradel.steamapi.webapi.request.GetSchemaForGameRequest.GetSchemaForGameRequestBuilder;
import com.lukaspradel.steamapi.webapi.request.GetUserStatsForGameRequest;
import com.lukaspradel.steamapi.webapi.request.GetUserStatsForGameRequest.GetUserStatsForGameRequestBuilder;
import com.lukaspradel.steamapi.webapi.request.IsPlayingSharedGameRequest;
import com.lukaspradel.steamapi.webapi.request.IsPlayingSharedGameRequest.IsPlayingSharedGameRequestBuilder;
import com.lukaspradel.steamapi.webapi.request.SteamWebApiRequest;

/**
 * Convenience class to create instances of {@link SteamWebApiRequest}.
 * Alternatively use the *RequestBuilder builders.
 *
 * @author lpradel
 *
 */
public abstract class SteamWebApiRequestFactory {

	public static GetNewsForAppRequest createGetNewsForAppRequest(int appId) {

		return new GetNewsForAppRequestBuilder(appId).buildRequest();
	}

	public static GetNewsForAppRequest createGetNewsForAppRequest(int appId,
			int count, int maxLength) {

		return new GetNewsForAppRequestBuilder(appId).count(count)
				.maxLength(maxLength).buildRequest();
	}

	public static GetGlobalAchievementPercentagesForAppRequest createGetGlobalAchievementPercentagesForAppRequest(
			int gameId) {

		return new GetGlobalAchievementPercentagesForAppRequestBuilder(gameId)
				.buildRequest();
	}

	public static GetGlobalStatsForGameRequest createGetGlobalStatsForGameRequest(
			int gameId, int count, List<String> names) {

		return new GetGlobalStatsForGameRequestBuilder(gameId, count, names)
				.buildRequest();
	}

	public static GetPlayerSummariesRequest createGetPlayerSummariesRequest(
			List<String> steamIds) {

		return new GetPlayerSummariesRequestBuilder(steamIds).buildRequest();
	}

	public static GetFriendListRequest createGetFriendListRequest(String steamId) {

		return new GetFriendListRequestBuilder(steamId).buildRequest();
	}

	public static GetFriendListRequest createGetFriendListRequest(
			String steamId, Relationship relationship) {

		return new GetFriendListRequestBuilder(steamId).relationship(
				relationship).buildRequest();
	}

	public static GetPlayerAchievementsRequest createGetPlayerAchievementsRequest(
			int appId, String steamId) {

		return new GetPlayerAchievementsRequestBuilder(steamId, appId)
				.buildRequest();
	}

	public static GetPlayerAchievementsRequest createGetPlayerAchievementsRequest(
			int appId, String steamId, String language) {

		return new GetPlayerAchievementsRequestBuilder(steamId, appId)
				.language(language).buildRequest();
	}

	public static GetUserStatsForGameRequest createGetUserStatsForGameRequest(
			int appId, String steamId) {

		return new GetUserStatsForGameRequestBuilder(steamId, appId)
				.buildRequest();
	}

	public static GetUserStatsForGameRequest createGetUserStatsForGameRequest(
			int appId, String steamId, String language) {

		return new GetUserStatsForGameRequestBuilder(steamId, appId).language(
				language).buildRequest();
	}

	public static GetOwnedGamesRequest createGetOwnedGamesRequest(String steamId) {

		return new GetOwnedGamesRequestBuilder(steamId).buildRequest();
	}

	public static GetOwnedGamesRequest createGetOwnedGamesRequest(
			String steamId, boolean includeAppInfo,
			boolean includePlayedFreeGames, List<Integer> appIdsFilter) {

		return new GetOwnedGamesRequestBuilder(steamId)
				.includeAppInfo(includeAppInfo)
				.includePlayedFreeGames(includePlayedFreeGames)
				.appIdsFilter(appIdsFilter).buildRequest();
	}

	public static GetRecentlyPlayedGamesRequest createGetRecentlyPlayedGamesRequest(
			String steamId) {

		return new GetRecentlyPlayedGamesRequestBuilder(steamId).buildRequest();
	}

	public static GetRecentlyPlayedGamesRequest createGetRecentlyPlayedGamesRequest(
			String steamId, Integer count) {

		return new GetRecentlyPlayedGamesRequestBuilder(steamId).count(count)
				.buildRequest();
	}

	public static IsPlayingSharedGameRequest createIsPlayingSharedGameRequest(
			String steamId, Integer appIdPlaying) {

		return new IsPlayingSharedGameRequestBuilder(steamId, appIdPlaying)
				.buildRequest();
	}

	public static GetSchemaForGameRequest createGetSchemaForGameRequest(
			Integer appId) {

		return new GetSchemaForGameRequestBuilder(appId).buildRequest();
	}

	public static GetPlayerBansRequest createGetPlayerBansRequest(
			List<String> steamIds) {

		return new GetPlayerBansRequestBuilder(steamIds).buildRequest();
	}
}
