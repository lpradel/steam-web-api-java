package com.lukaspradel.steamapi.webapi.request.builders;

import com.lukaspradel.steamapi.webapi.request.GetGlobalAchievementPercentagesForAppRequest;
import com.lukaspradel.steamapi.webapi.request.GetGlobalAchievementPercentagesForAppRequest.GetGlobalAchievementPercentagesForAppRequestBuilder;
import com.lukaspradel.steamapi.webapi.request.GetNewsForAppRequest;
import com.lukaspradel.steamapi.webapi.request.GetNewsForAppRequest.GetNewsForAppRequestBuilder;
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
}
