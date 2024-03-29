package com.lukaspradel.steamapi.core;

import java.util.HashMap;
import java.util.Map;

/**
 * General purpose request class for requests to some Steam API.
 *
 * @author lpradel
 *
 */
public abstract class SteamApiRequest {

	protected final String baseUrl;

	protected final Map<String, String> parameters;

	protected static final String REQUEST_PARAM_BOOLEAN_TRUE = "1";

	public SteamApiRequest(String baseUrl, Map<String, String> parameters) {

		this.baseUrl = baseUrl;
		this.parameters = parameters;
	}

	public String getBaseUrl() {
		return baseUrl;
	}

	public Map<String, String> getParameters() {
		return new HashMap<>(parameters);
	}
}
