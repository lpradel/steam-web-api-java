package com.lukaspradel.steamapi.webapi.request.builders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lukaspradel.steamapi.webapi.request.SteamWebApiRequest.SteamWebApiRequestBuilder;
import com.lukaspradel.steamapi.webapi.request.SteamWebApiServiceParameter;

/**
 * Skeleton of {@link SteamWebApiRequestBuilder} for request builders for
 * requests that are sent to Steam Web API <b>services</b> which require a
 * specific JSON parameter. The JSON parameter or <b>service parameter</b> can
 * be passed to {@link #addServiceParameter(SteamWebApiServiceParameter)
 * addServiceParameter} which converts the POJO to a JSON string.
 *
 * @see <a href=
 *      "https://developer.valvesoftware.com/wiki/Steam_Web_API#Calling_Service_interfaces"
 *      >https://developer.valvesoftware.com/wiki/Steam_Web_API#Calling_Service_interfaces</a>
 * @author lpradel
 *
 */
public abstract class AbstractSteamWebApiServiceRequestBuilder extends
		AbstractSteamWebApiRequestBuilder {

	/**
	 * The request parameter name of the service-specific parameter.
	 */
	static final String REQUEST_PARAM_INPUT_JSON = "input_json";

	private final ObjectMapper mapper = new ObjectMapper();

	/**
	 * Converts the service parameter POJO to a JSON string and adds the JSON
	 * string as a normal GET parameter.
	 *
	 * @param serviceParameter
	 */
	protected void addServiceParameter(
			SteamWebApiServiceParameter serviceParameter) {

		try {
			String serviceParameterJson = mapper
					.writeValueAsString(serviceParameter);

			addParameter(REQUEST_PARAM_INPUT_JSON, serviceParameterJson);
		} catch (JsonProcessingException e) {
			throw new IllegalArgumentException(
					"The given request parameters cannot be mapped to JSON.", e);
		}
	}
}
