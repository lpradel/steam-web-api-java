package com.lukaspradel.steamapi.webapi.request;

import com.lukaspradel.steamapi.webapi.core.SteamWebApiInterface;
import com.lukaspradel.steamapi.webapi.core.SteamWebApiInterfaceMethod;
import com.lukaspradel.steamapi.webapi.core.SteamWebApiVersion;
import com.lukaspradel.steamapi.webapi.request.builders.AbstractSteamWebApiRequestBuilder;

/**
 * Steam Web API Request GetUserStatsForGame
 *
 * @see <a href=
 *      "https://developer.valvesoftware.com/wiki/Steam_Web_API#GetUserStatsForGame_.28v0002.29"
 *      >https://developer.valvesoftware.com/wiki/Steam_Web_API#GetUserStatsForGame_.28v0002.29</a>
 * @author lpradel
 *
 */
public class GetUserStatsForGameRequest extends SteamWebApiRequest {

	private GetUserStatsForGameRequest(SteamWebApiRequestBuilder builder) {
		super(builder);
	}

	/**
	 * Builder object to create instances of {@link SteamWebApiRequest} for
	 * request type {@link GetUserStatsForGameRequest}.
	 *
	 * @author lpradel
	 *
	 */
	public static class GetUserStatsForGameRequestBuilder extends
			AbstractSteamWebApiRequestBuilder {

		private final String steamId;

		private final Integer appId;

		private String language = null;

		public static final String REQUEST_PARAM_STEAM_ID = "steamid";

		public static final String REQUEST_PARAM_APP_ID = "appid";

		public static final String REQUEST_PARAM_LANGUAGE = "l";

		public GetUserStatsForGameRequestBuilder(String steamId, Integer appId) {

			this.steamId = steamId;
			this.appId = appId;
		}

		public GetUserStatsForGameRequestBuilder language(String language) {

			this.language = language;
			return this;
		}

		@Override
		protected SteamWebApiInterface getInterface() {

			return SteamWebApiInterface.I_STEAM_USER_STATS;
		}

		@Override
		protected SteamWebApiInterfaceMethod getInterfaceMethod() {

			return SteamWebApiInterfaceMethod.GET_USER_STATS_FOR_GAME;
		}

		@Override
		protected SteamWebApiVersion getVersion() {

			return SteamWebApiVersion.VERSION_TWO;
		}

		@Override
		public GetUserStatsForGameRequest buildRequest() {

			addParameter(REQUEST_PARAM_APP_ID, appId);
			addParameter(REQUEST_PARAM_STEAM_ID, steamId);

			if (language != null) {
				addParameter(REQUEST_PARAM_LANGUAGE, language);
			}

			return new GetUserStatsForGameRequest(this);
		}
	}
}
