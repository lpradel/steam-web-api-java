package com.lukaspradel.steamapi.webapi.request;

import com.lukaspradel.steamapi.webapi.core.SteamWebApiInterface;
import com.lukaspradel.steamapi.webapi.core.SteamWebApiInterfaceMethod;
import com.lukaspradel.steamapi.webapi.core.SteamWebApiVersion;
import com.lukaspradel.steamapi.webapi.request.builders.AbstractSteamWebApiRequestBuilder;

/**
 * Steam Web API Request GetGlobalAchievementPercentagesForApp
 *
 * @see <a href=
 *      "https://developer.valvesoftware.com/wiki/Steam_Web_API#GetGlobalAchievementPercentagesForApp_.28v0002.29"
 *      >https://developer.valvesoftware.com/wiki/Steam_Web_API#GetGlobalAchievementPercentagesForApp_.28v0002.29</a>
 * @author lpradel
 *
 */
public class GetGlobalAchievementPercentagesForAppRequest extends
		SteamWebApiRequest {

	private GetGlobalAchievementPercentagesForAppRequest(
			SteamWebApiRequestBuilder builder) {
		super(builder);
	}

	/**
	 * Builder object to create instances of {@link SteamWebApiRequest} for
	 * request type {@link GetGlobalAchievementPercentagesForAppRequest}.
	 *
	 * @author lpradel
	 *
	 */
	public static class GetGlobalAchievementPercentagesForAppRequestBuilder
			extends AbstractSteamWebApiRequestBuilder {

		private final Integer gameId;

		public static final String REQUEST_PARAM_GAME_ID = "gameid";

		public GetGlobalAchievementPercentagesForAppRequestBuilder(
				Integer gameId) {

			this.gameId = gameId;
		}

		@Override
		protected SteamWebApiInterface getInterface() {

			return SteamWebApiInterface.I_STEAM_USER_STATS;
		}

		@Override
		protected SteamWebApiInterfaceMethod getInterfaceMethod() {

			return SteamWebApiInterfaceMethod.GET_GLOBAL_ACHIEVEMENT_PERCENTAGES_FOR_APP;
		}

		@Override
		protected SteamWebApiVersion getVersion() {

			return SteamWebApiVersion.VERSION_TWO;
		}

		@Override
		public GetGlobalAchievementPercentagesForAppRequest buildRequest() {

			addParameter(REQUEST_PARAM_GAME_ID, gameId);

			return new GetGlobalAchievementPercentagesForAppRequest(this);
		}
	}
}
