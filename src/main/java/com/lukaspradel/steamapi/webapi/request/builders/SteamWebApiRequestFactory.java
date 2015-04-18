package com.lukaspradel.steamapi.webapi.request.builders;

import java.util.List;

import com.lukaspradel.steamapi.webapi.request.GetFriendListRequest;
import com.lukaspradel.steamapi.webapi.request.GetFriendListRequest.GetFriendListRequestBuilder;
import com.lukaspradel.steamapi.webapi.request.GetFriendListRequest.Relationship;
import com.lukaspradel.steamapi.webapi.request.GetGlobalAchievementPercentagesForAppRequest;
import com.lukaspradel.steamapi.webapi.request.GetGlobalAchievementPercentagesForAppRequest.GetGlobalAchievementPercentagesForAppRequestBuilder;
import com.lukaspradel.steamapi.webapi.request.GetNewsForAppRequest;
import com.lukaspradel.steamapi.webapi.request.GetNewsForAppRequest.GetNewsForAppRequestBuilder;
import com.lukaspradel.steamapi.webapi.request.GetPlayerAchievementsRequest;
import com.lukaspradel.steamapi.webapi.request.GetPlayerAchievementsRequest.GetPlayerAchievementsRequestBuilder;
import com.lukaspradel.steamapi.webapi.request.GetPlayerSummariesRequest;
import com.lukaspradel.steamapi.webapi.request.GetPlayerSummariesRequest.GetPlayerSummariesRequestBuilder;
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
}
