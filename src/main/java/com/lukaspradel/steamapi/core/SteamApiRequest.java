package com.lukaspradel.steamapi.core;

import java.util.Map;

public abstract class SteamApiRequest {

	protected String baseUrl;

	protected Map<String, String> parameters;

	public String getBaseUrl() {
		return baseUrl;
	}

	public Map<String, String> getParameters() {
		return parameters;
	}
}
