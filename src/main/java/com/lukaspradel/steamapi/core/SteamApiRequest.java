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

	protected final Map<String, String> parameters;

	protected static final String REQUEST_PARAM_BOOLEAN_TRUE = "1";

	public SteamApiRequest(Map<String, String> parameters) {

		this.parameters = parameters;
	}

	public Map<String, String> getParameters() {
		return new HashMap<>(parameters);
	}
}
