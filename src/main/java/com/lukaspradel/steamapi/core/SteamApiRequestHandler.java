package com.lukaspradel.steamapi.core;

/**
 * Generic request handler for common properties of all APIs provided by Steam.
 *
 * @author lpradel
 *
 */
public abstract class SteamApiRequestHandler {

	private static final String PROTOCOL_HTTPS = "https";

	private static final String PROTOCOL_HTTP = "http";

	private boolean useHttps;

	private String key;

	public SteamApiRequestHandler(boolean useHttps, String key) {

		this.useHttps = useHttps;
		this.key = key;
	}

	public boolean isUseHttps() {
		return useHttps;
	}

	public String getKey() {
		return key;
	}

	public String getProtocol() {

		return isUseHttps() ? PROTOCOL_HTTPS : PROTOCOL_HTTP;
	}
}
