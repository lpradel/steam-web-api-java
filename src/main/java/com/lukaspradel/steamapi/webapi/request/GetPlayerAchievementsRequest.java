package com.lukaspradel.steamapi.webapi.request;

import com.lukaspradel.steamapi.webapi.core.SteamWebApiInterface;
import com.lukaspradel.steamapi.webapi.core.SteamWebApiInterfaceMethod;
import com.lukaspradel.steamapi.webapi.core.SteamWebApiVersion;
import com.lukaspradel.steamapi.webapi.request.builders.AbstractSteamWebApiRequestBuilder;

/**
 * Steam Web API Request GetPlayerAchievements
 *
 * @see <a href=
 *      "https://developer.valvesoftware.com/wiki/Steam_Web_API#GetPlayerAchievements_.28v0001.29"
 *      >https://developer.valvesoftware.com/wiki/Steam_Web_API#GetPlayerAchievements_.28v0001.29</a>
 * @author lpradel
 *
 */
public class GetPlayerAchievementsRequest extends SteamWebApiRequest {

	private GetPlayerAchievementsRequest(SteamWebApiRequestBuilder builder) {
		super(builder);
	}

	/**
	 * Builder object to create instances of {@link SteamWebApiRequest} for
	 * request type {@link GetPlayerAchievementsRequest}.
	 *
	 * @author lpradel
	 *
	 */
	public static class GetPlayerAchievementsRequestBuilder extends
			AbstractSteamWebApiRequestBuilder {

		private final String steamId;

		private final Integer appId;

		private String language = null;

		public static final String REQUEST_PARAM_STEAM_ID = "steamid";

		public static final String REQUEST_PARAM_APP_ID = "appid";

		public static final String REQUEST_PARAM_LANGUAGE = "l";

		public GetPlayerAchievementsRequestBuilder(String steamId, Integer appId) {

			this.steamId = steamId;
			this.appId = appId;
		}

		public GetPlayerAchievementsRequestBuilder language(String language) {

			this.language = language;
			return this;
		}

		@Override
		protected SteamWebApiInterface getInterface() {

			return SteamWebApiInterface.I_STEAM_USER_STATS;
		}

		@Override
		protected SteamWebApiInterfaceMethod getInterfaceMethod() {

			return SteamWebApiInterfaceMethod.GET_PLAYER_ACHIEVEMENTS;
		}

		@Override
		protected SteamWebApiVersion getVersion() {

			return SteamWebApiVersion.VERSION_ONE;
		}

		@Override
		public GetPlayerAchievementsRequest buildRequest() {

			addParameter(REQUEST_PARAM_APP_ID, appId);
			addParameter(REQUEST_PARAM_STEAM_ID, steamId);

			if (language != null) {
				addParameter(REQUEST_PARAM_LANGUAGE, language);
			}

			return new GetPlayerAchievementsRequest(this);
		}
	}
}
