package com.lukaspradel.steamapi.webapi.request;

import java.util.List;

import com.lukaspradel.steamapi.webapi.core.SteamWebApiInterface;
import com.lukaspradel.steamapi.webapi.core.SteamWebApiInterfaceMethod;
import com.lukaspradel.steamapi.webapi.core.SteamWebApiVersion;
import com.lukaspradel.steamapi.webapi.request.builders.AbstractSteamWebApiRequestBuilder;

/**
 * Steam Web API Request GetGlobalStatsForGame
 *
 * @see <a href=
 *      "https://developer.valvesoftware.com/wiki/Steam_Web_API#GetGlobalStatsForGame_.28v0001.29"
 *      >https://developer.valvesoftware.com/wiki/Steam_Web_API#GetGlobalStatsForGame_.28v0001.29</a>
 * @author lpradel
 *
 */
public class GetGlobalStatsForGameRequest extends SteamWebApiRequest {

	private GetGlobalStatsForGameRequest(SteamWebApiRequestBuilder builder) {
		super(builder);
	}

	/**
	 * Builder object to create instances of {@link SteamWebApiRequest} for
	 * request type {@link GetGlobalStatsForGameRequest}.
	 *
	 * @author lpradel
	 *
	 */
	public static class GetGlobalStatsForGameRequestBuilder extends
			AbstractSteamWebApiRequestBuilder {

		private final Integer gameId;

		private final Integer count;

		private final List<String> names;

		public static final String REQUEST_PARAM_GAME_ID = "gameid";

		public static final String REQUEST_PARAM_COUNT = "count";

		public static final String REQUEST_PARAM_NAME = "name";

		public GetGlobalStatsForGameRequestBuilder(Integer gameId,
				Integer count, List<String> names) {

			if (names.isEmpty()) {
				throw new IllegalArgumentException(
						"You must supply at least 1 Steam Achievement Name!");
			}

			if (!count.equals(names.size())) {
				throw new IllegalArgumentException(
						"You must supply exactly as many Steam Achievement Names as indicated in count!");
			}

			this.gameId = gameId;
			this.count = count;
			this.names = names;
		}

		@Override
		protected SteamWebApiInterface getInterface() {

			return SteamWebApiInterface.I_STEAM_USER_STATS;
		}

		@Override
		protected SteamWebApiInterfaceMethod getInterfaceMethod() {

			return SteamWebApiInterfaceMethod.GET_GLOBAL_STATS_FOR_GAME;
		}

		@Override
		protected SteamWebApiVersion getVersion() {

			return SteamWebApiVersion.VERSION_ONE;
		}

		@Override
		public GetGlobalStatsForGameRequest buildRequest() {

			addParameter(REQUEST_PARAM_GAME_ID, gameId);
			addParameter(REQUEST_PARAM_COUNT, count);
			addArrayParameter(REQUEST_PARAM_NAME, names);

			return new GetGlobalStatsForGameRequest(this);
		}
	}
}
