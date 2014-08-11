package com.lukaspradel.steamapi.webapi;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public abstract class SteamWebApiRequestBuilder {

	protected Map<String, String> parameters = new HashMap<String, String>();

	public Map<String, String> getParameters() {
		return Collections.unmodifiableMap(parameters);
	}
}
