package com.lukaspradel.steamapi.webapi.request;

import com.lukaspradel.steamapi.webapi.core.SteamWebApiInterface;
import com.lukaspradel.steamapi.webapi.core.SteamWebApiInterfaceMethod;
import com.lukaspradel.steamapi.webapi.core.SteamWebApiVersion;
import com.lukaspradel.steamapi.webapi.request.builders.AbstractSteamWebApiRequestBuilder;

/**
 * Steam Web API Request GetSchemaForGame
 *
 * @see <a href=
 *      "https://developer.valvesoftware.com/wiki/Steam_Web_API#GetSchemaForGame_.28v2.29"
 *      >https://developer.valvesoftware.com/wiki/Steam_Web_API#GetSchemaForGame_.28v2.29</a>
 * @author lpradel
 *
 */
public class GetSchemaForGameRequest extends SteamWebApiRequest {

	private GetSchemaForGameRequest(SteamWebApiRequestBuilder builder) {
		super(builder);
	}

	/**
	 * Builder object to create instances of {@link SteamWebApiRequest} for
	 * request type {@link GetSchemaForGameRequest}.
	 *
	 * @author lpradel
	 *
	 */
	public static class GetSchemaForGameRequestBuilder extends
			AbstractSteamWebApiRequestBuilder {

		private final Integer appId;

		public static final String REQUEST_PARAM_APP_ID = "appid";

		public GetSchemaForGameRequestBuilder(Integer appId) {

			this.appId = appId;
		}

		@Override
		protected SteamWebApiInterface getInterface() {

			return SteamWebApiInterface.I_STEAM_USER_STATS;
		}

		@Override
		protected SteamWebApiInterfaceMethod getInterfaceMethod() {

			return SteamWebApiInterfaceMethod.GET_SCHEMA_FOR_GAME;
		}

		@Override
		protected SteamWebApiVersion getVersion() {

			return SteamWebApiVersion.VERSION_TWO;
		}

		@Override
		public GetSchemaForGameRequest buildRequest() {

			addParameter(REQUEST_PARAM_APP_ID, appId);

			return new GetSchemaForGameRequest(this);
		}
	}
}
