package com.lukaspradel.steamapi.webapi.request;

import java.util.List;

import com.lukaspradel.steamapi.webapi.core.SteamWebApiInterface;
import com.lukaspradel.steamapi.webapi.core.SteamWebApiInterfaceMethod;
import com.lukaspradel.steamapi.webapi.core.SteamWebApiVersion;
import com.lukaspradel.steamapi.webapi.request.builders.AbstractSteamWebApiRequestBuilder;

/**
 * Steam Web API Request GetPlayerBans
 *
 * @see <a href=
 *      "https://developer.valvesoftware.com/wiki/Steam_Web_API#GetPlayerBans_.28v1.29"
 *      >https://developer.valvesoftware.com/wiki/Steam_Web_API#GetPlayerBans_.28v1.29</a>
 * @author lpradel
 *
 */
public class GetPlayerBansRequest extends SteamWebApiRequest {

	private GetPlayerBansRequest(SteamWebApiRequestBuilder builder) {
		super(builder);
	}

	/**
	 * Builder object to create instances of {@link SteamWebApiRequest} for
	 * request type {@link GetPlayerBansRequest}.
	 *
	 * @author lpradel
	 *
	 */
	public static class GetPlayerBansRequestBuilder extends
			AbstractSteamWebApiRequestBuilder {

		private final List<String> steamIds;

		public static final String REQUEST_PARAM_STEAM_IDS = "steamids";

		public GetPlayerBansRequestBuilder(List<String> steamIds) {

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

			return SteamWebApiInterfaceMethod.GET_PLAYER_BANS;
		}

		@Override
		protected SteamWebApiVersion getVersion() {

			return SteamWebApiVersion.VERSION_ONE;
		}

		@Override
		public GetPlayerBansRequest buildRequest() {

			addListParameter(REQUEST_PARAM_STEAM_IDS, steamIds);

			return new GetPlayerBansRequest(this);
		}
	}
}
