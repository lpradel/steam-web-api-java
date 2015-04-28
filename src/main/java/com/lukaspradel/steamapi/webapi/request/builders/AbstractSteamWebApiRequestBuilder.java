package com.lukaspradel.steamapi.webapi.request.builders;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
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

	/**
	 * Adds List-parameter as comma-separated strings
	 *
	 * @param name
	 *            Name of the List-parameter
	 * @param valueList
	 *            List of the comma-separated strings
	 */
	protected void addListParameter(String name, List<String> valueList) {

		StringBuilder paramValue = new StringBuilder();

		for (String value : valueList) {
			paramValue.append(value);
			paramValue.append(",");
		}
		paramValue.setLength(paramValue.length() - 1);

		addParameter(name, paramValue.toString());
	}

	@Override
	public Map<String, String> getParameters() {
		return Collections.unmodifiableMap(parameters);
	}
}
