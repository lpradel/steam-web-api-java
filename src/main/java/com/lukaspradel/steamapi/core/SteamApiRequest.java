package com.lukaspradel.steamapi.core;

import java.util.Collections;
import java.util.Map;

public abstract class SteamApiRequest {

	protected final String baseUrl;

	protected final Map<String, String> parameters;

	public SteamApiRequest(String baseUrl, Map<String, String> parameters) {

		this.baseUrl = baseUrl;
		this.parameters = parameters;
	}

	public String getBaseUrl() {
		return baseUrl;
	}

	public Map<String, String> getParameters() {
		return Collections.unmodifiableMap(parameters);
	}
}
