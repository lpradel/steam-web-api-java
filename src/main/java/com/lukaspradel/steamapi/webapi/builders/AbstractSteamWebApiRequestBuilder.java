package com.lukaspradel.steamapi.webapi.builders;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.lukaspradel.steamapi.webapi.SteamWebApiInterface;
import com.lukaspradel.steamapi.webapi.SteamWebApiInterfaceMethod;
import com.lukaspradel.steamapi.webapi.SteamWebApiRequest;
import com.lukaspradel.steamapi.webapi.SteamWebApiVersion;
import com.lukaspradel.steamapi.webapi.SteamWebApiRequest.SteamWebApiRequestBuilder;

public abstract class AbstractSteamWebApiRequestBuilder {

	protected Map<String, String> parameters = new HashMap<String, String>();

	public AbstractSteamWebApiRequestBuilder() {

		parameters.put("format", "json");
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
