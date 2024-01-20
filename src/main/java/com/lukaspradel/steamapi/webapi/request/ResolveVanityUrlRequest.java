package com.lukaspradel.steamapi.webapi.request;

import com.lukaspradel.steamapi.webapi.core.SteamWebApiInterface;
import com.lukaspradel.steamapi.webapi.core.SteamWebApiInterfaceMethod;
import com.lukaspradel.steamapi.webapi.core.SteamWebApiVersion;
import com.lukaspradel.steamapi.webapi.request.builders.AbstractSteamWebApiRequestBuilder;

/**
 * @author 41zu
 */
public class ResolveVanityUrlRequest extends SteamWebApiRequest {

	/**
	 * Steam Web API Request ResolveVanityURL
	 *
	 * @see <a href=
	 *      "https://partner.steamgames.com/doc/webapi/ISteamUser#ResolveVanityURL"
	 *      >https://partner.steamgames.com/doc/webapi/ISteamUser#ResolveVanityURL</a>
	 */
	protected ResolveVanityUrlRequest(SteamWebApiRequestBuilder builder) {
		super(builder);
	}

	public static class ResolveVanityUrlRequestBuilder extends
			AbstractSteamWebApiRequestBuilder {

		public static final String REQUEST_PARAM_VANITY_URL = "vanityurl";

		public static final String REQUEST_PARAM_URL_TYPE = "url_type";

		private final String vanityUrl;
		private Integer urlType;

		/**
		 * @param vanityUrl The vanity URL to get a SteamID for (Can simply be the Steam user name) (NOT NULL)
		 */
		public ResolveVanityUrlRequestBuilder(String vanityUrl) {

			// Validate parameter, otherwise the wrong SteamID gets returned
			if (vanityUrl == null) {
				throw new IllegalArgumentException("vanityUrl must be NOT null");
			}

			this.vanityUrl = vanityUrl;
		}

		/**
		 * @param urlType The type of vanity URL<br>1 (default): Individual profile<br>2: Group<br>3: Official game group
		 */
		public ResolveVanityUrlRequestBuilder urlType(Integer urlType) {

			this.urlType = urlType;
			return this;
		}

		@Override
		protected SteamWebApiInterface getInterface() {

			return SteamWebApiInterface.I_STEAM_USER;
		}

		@Override
		protected SteamWebApiInterfaceMethod getInterfaceMethod() {

			return SteamWebApiInterfaceMethod.RESOLVE_VANITY_URL;
		}

		@Override
		protected SteamWebApiVersion getVersion() {

			return SteamWebApiVersion.VERSION_ONE;
		}

		@Override
		public ResolveVanityUrlRequest buildRequest() {

			addParameter(REQUEST_PARAM_VANITY_URL, vanityUrl);

			if (urlType != null) {
				addParameter(REQUEST_PARAM_URL_TYPE, urlType);
			}

			return new ResolveVanityUrlRequest(this);
		}
	}
}
