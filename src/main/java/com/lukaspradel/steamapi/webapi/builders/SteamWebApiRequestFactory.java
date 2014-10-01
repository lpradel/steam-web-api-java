package com.lukaspradel.steamapi.webapi.builders;

import com.lukaspradel.steamapi.webapi.SteamWebApiRequest;

public abstract class SteamWebApiRequestFactory {

	public static SteamWebApiRequest createGetNewsForAppRequest(int appId) {

		return new GetNewsForAppRequestBuilder(appId).buildRequest();
	}

	public static SteamWebApiRequest createGetNewsForAppRequest(int appId,
			int count, int maxLength) {

		return new GetNewsForAppRequestBuilder(appId).count(count)
				.maxLength(maxLength).buildRequest();
	}
}
