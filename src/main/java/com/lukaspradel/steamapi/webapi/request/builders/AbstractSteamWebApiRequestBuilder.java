package com.lukaspradel.steamapi.webapi.request.builders;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.lukaspradel.steamapi.webapi.SteamWebApiInterface;
import com.lukaspradel.steamapi.webapi.SteamWebApiInterfaceMethod;
import com.lukaspradel.steamapi.webapi.request.SteamWebApiRequest;
import com.lukaspradel.steamapi.webapi.request.SteamWebApiRequest.SteamWebApiRequestBuilder;
import com.lukaspradel.steamapi.webapi.SteamWebApiVersion;

/**
 * Skeleton of {@link SteamWebApiRequest} that holds (common) request parameters
 * and implements shared instantiation of {@link SteamWebApiRequest}.
 *
 * @author lpradel
 *
 */
public abstract class AbstractSteamWebApiRequestBuilder {

	protected Map<String, String> parameters = new HashMap<String, String>();

	static final String REQUEST_PARAM_FORMAT = "format";

	static final String REQUEST_PARAM_FORMAT_JSON = "json";

	public AbstractSteamWebApiRequestBuilder() {

		parameters.put(REQUEST_PARAM_FORMAT, REQUEST_PARAM_FORMAT_JSON);
	}

	protected abstract SteamWebApiInterface getInterface();

	protected abstract SteamWebApiInterfaceMethod getInterfaceMethod();

	protected abstract SteamWebApiVersion getVersion();

	protected void addParameter(String name, String value) {

		parameters.put(name, value);
	}

	protected void addParameter(String name, Integer value) {

		parameters.put(name, String.valueOf(value));
	}

	public Map<String, String> getParameters() {
		return Collections.unmodifiableMap(parameters);
	}

	public SteamWebApiRequest buildRequest() {

		SteamWebApiRequestBuilder builder = new SteamWebApiRequestBuilder(
				getInterface(), getInterfaceMethod(), getVersion(),
				getParameters());
		return builder.build();
	}
}
