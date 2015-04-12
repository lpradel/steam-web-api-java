package com.lukaspradel.steamapi.webapi.request;

import java.util.List;

import com.lukaspradel.steamapi.webapi.core.SteamWebApiInterface;
import com.lukaspradel.steamapi.webapi.core.SteamWebApiInterfaceMethod;
import com.lukaspradel.steamapi.webapi.core.SteamWebApiVersion;
import com.lukaspradel.steamapi.webapi.request.builders.AbstractSteamWebApiRequestBuilder;

/**
 * Steam Web API Request GetPlayerSummaries
 *
 * @see <a href=
 *      "https://developer.valvesoftware.com/wiki/Steam_Web_API#GetPlayerSummaries_.28v0002.29"
 *      >https://developer.valvesoftware.com/wiki/Steam_Web_API#GetPlayerSummaries_.28v0002.29</a>
 * @author lpradel
 *
 */
public class GetPlayerSummariesRequest extends SteamWebApiRequest {

	private GetPlayerSummariesRequest(SteamWebApiRequestBuilder builder) {
		super(builder);
	}

	/**
	 * Builder object to create instances of {@link SteamWebApiRequest} for
	 * request type {@link GetPlayerSummariesRequest}.
	 *
	 * @author lpradel
	 *
	 */
	public static class GetPlayerSummariesRequestBuilder extends
			AbstractSteamWebApiRequestBuilder {

		private final List<String> steamIds;

		public static final String REQUEST_PARAM_STEAM_IDS = "steamids";

		public GetPlayerSummariesRequestBuilder(List<String> steamIds) {

			if (steamIds.isEmpty()) {
				throw new IllegalArgumentException(
						"You must supply at least 1 Steam ID!");
			}

			this.steamIds = steamIds;
		}

		@Override
		protected SteamWebApiInterface getInterface() {

			return SteamWebApiInterface.I_STEAM_USER;
		}

		@Override
		protected SteamWebApiInterfaceMethod getInterfaceMethod() {

			return SteamWebApiInterfaceMethod.GET_PLAYER_SUMMARIES;
		}

		@Override
		protected SteamWebApiVersion getVersion() {

			return SteamWebApiVersion.VERSION_TWO;
		}

		@Override
		public GetPlayerSummariesRequest buildRequest() {

			StringBuilder steamIdsParam = new StringBuilder();

			// Steam IDs are transmitted as comma-separated list
			for (String steamId : steamIds) {
				steamIdsParam.append(steamId);
				steamIdsParam.append(",");
			}
			steamIdsParam.setLength(steamIdsParam.length() - 1);

			addParameter(REQUEST_PARAM_STEAM_IDS, steamIdsParam.toString());

			return new GetPlayerSummariesRequest(this);
		}
	}
}
