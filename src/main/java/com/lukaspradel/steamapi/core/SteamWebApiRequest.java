package com.lukaspradel.steamapi.core;

/**
 * Encapsulates a request to the Steam Web API with all its required and
 * optional properties.
 *
 * @author lpradel
 *
 */
public class SteamWebApiRequest extends SteamApiRequest {

	private static final String WEB_API_BASE_URL = "https://api.steampowered.com/";

	private final SteamWebApiInterface apiInterface;

	private final SteamWebApiInterfaceMethod interfaceMethod;

	private final SteamWebApiVersion version;

	private SteamWebApiRequest(SteamWebApiRequestBuilder builder) {

		this.baseUrl = WEB_API_BASE_URL;

		this.apiInterface = builder.apiInterface;
		this.interfaceMethod = builder.interfaceMethod;
		this.version = builder.version;
	}

	public SteamWebApiInterface getApiInterface() {
		return apiInterface;
	}

	public SteamWebApiInterfaceMethod getInterfaceMethod() {
		return interfaceMethod;
	}

	public SteamWebApiVersion getVersion() {
		return version;
	}

	public static class SteamWebApiRequestBuilder {

		private final SteamWebApiInterface apiInterface;

		private final SteamWebApiInterfaceMethod interfaceMethod;

		private final SteamWebApiVersion version;

		public SteamWebApiRequestBuilder(SteamWebApiInterface apiInterface,
				SteamWebApiInterfaceMethod interfaceMethod,
				SteamWebApiVersion version) {

			this.apiInterface = apiInterface;
			this.interfaceMethod = interfaceMethod;
			this.version = version;
		}
	}
}
