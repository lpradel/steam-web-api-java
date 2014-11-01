package com.lukaspradel.steamapi.webapi.request;

import com.lukaspradel.steamapi.webapi.core.SteamWebApiInterface;
import com.lukaspradel.steamapi.webapi.core.SteamWebApiInterfaceMethod;
import com.lukaspradel.steamapi.webapi.core.SteamWebApiVersion;
import com.lukaspradel.steamapi.webapi.request.builders.AbstractSteamWebApiRequestBuilder;

/**
 * Steam Web API Request GetNewsForApp
 *
 * @see <a href=
 *      "https://developer.valvesoftware.com/wiki/Steam_Web_API#GetNewsForApp_.28v0002.29"
 *      >https://developer.valvesoftware.com/wiki/Steam_Web_API#GetNewsForApp_.28v0002.29</a>
 * @author lpradel
 *
 */
public class GetNewsForAppRequest extends SteamWebApiRequest {

	private GetNewsForAppRequest(SteamWebApiRequestBuilder builder) {
		super(builder);
	}

	/**
	 * Builder object to create instances of {@link SteamWebApiRequest} for
	 * request type {@link GetNewsForAppRequest}.
	 *
	 * @author lpradel
	 *
	 */
	public static class GetNewsForAppRequestBuilder extends
			AbstractSteamWebApiRequestBuilder {

		private final Integer appId;

		private Integer count;

		private Integer maxLength;

		public static final String REQUEST_PARAM_APP_ID = "appid";

		public static final String REQUEST_PARAM_COUNT = "count";

		public static final String REQUEST_PARAM_MAX_LENGTH = "maxlength";

		public GetNewsForAppRequestBuilder(Integer appId) {

			this.appId = appId;
		}

		public GetNewsForAppRequestBuilder count(Integer count) {

			this.count = count;
			return this;
		}

		public GetNewsForAppRequestBuilder maxLength(Integer maxLength) {

			this.maxLength = maxLength;
			return this;
		}

		@Override
		protected SteamWebApiInterface getInterface() {

			return SteamWebApiInterface.I_STEAM_NEWS;
		}

		@Override
		protected SteamWebApiInterfaceMethod getInterfaceMethod() {

			return SteamWebApiInterfaceMethod.GET_NEWS_FOR_APP;
		}

		@Override
		protected SteamWebApiVersion getVersion() {

			return SteamWebApiVersion.VERSION_TWO;
		}

		@Override
		public GetNewsForAppRequest buildRequest() {

			addParameter(REQUEST_PARAM_APP_ID, appId);

			if (count != null) {
				addParameter(REQUEST_PARAM_COUNT, count);
			}

			if (maxLength != null) {
				addParameter(REQUEST_PARAM_MAX_LENGTH, maxLength);
			}

			return new GetNewsForAppRequest(this);
		}
	}
}
