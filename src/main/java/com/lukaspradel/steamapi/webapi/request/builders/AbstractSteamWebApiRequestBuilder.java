package com.lukaspradel.steamapi.webapi.request.builders;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.lukaspradel.steamapi.webapi.request.SteamWebApiRequest;
import com.lukaspradel.steamapi.webapi.request.SteamWebApiRequest.SteamWebApiRequestBuilder;

/**
 * Skeleton of {@link SteamWebApiRequestBuilder} that holds (common) request
 * parameters and implements shared instantiation of {@link SteamWebApiRequest}.
 *
 * @author lpradel
 *
 */
public abstract class AbstractSteamWebApiRequestBuilder extends
		SteamWebApiRequestBuilder {

	protected Map<String, String> parameters = new HashMap<String, String>();

	static final String REQUEST_PARAM_FORMAT = "format";

	static final String REQUEST_PARAM_FORMAT_JSON = "json";

	public AbstractSteamWebApiRequestBuilder() {

		parameters.put(REQUEST_PARAM_FORMAT, REQUEST_PARAM_FORMAT_JSON);
	}

	public abstract SteamWebApiRequest buildRequest();

	protected void addParameter(String name, String value) {

		parameters.put(name, value);
	}

	protected void addParameter(String name, Integer value) {

		parameters.put(name, String.valueOf(value));
	}

	@Override
	public Map<String, String> getParameters() {
		return Collections.unmodifiableMap(parameters);
	}
}
