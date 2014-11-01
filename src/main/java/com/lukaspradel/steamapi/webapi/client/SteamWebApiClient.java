package com.lukaspradel.steamapi.webapi.client;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lukaspradel.steamapi.core.exception.SteamApiException;
import com.lukaspradel.steamapi.webapi.request.SteamWebApiRequest;
import com.lukaspradel.steamapi.webapi.request.SteamWebApiRequestHandler;

/**
 * Create instances of this using {@link SteamWebApiClientBuilder}. This class
 * should be used to pass instances of {@link SteamWebApiRequest} to the Steam
 * servers and get the response-POJO.
 *
 * @author lpradel
 *
 */
public class SteamWebApiClient {

	private static final ObjectMapper MAPPER = new ObjectMapper();

	private final String key;

	private final boolean useHttps;

	private final SteamWebApiRequestHandler requestHandler;

	private SteamWebApiClient(SteamWebApiClientBuilder builder) {

		this.key = builder.key;
		this.useHttps = builder.useHttps;
		this.requestHandler = new SteamWebApiRequestHandler(useHttps, key);
	}

	@SuppressWarnings({ "unchecked" })
	public <T> T processRequest(SteamWebApiRequest request)
			throws SteamApiException {

		T result = null;
		String response = requestHandler.getWebApiResponse(request);

		try {
			result = (T) MAPPER.readValue(response, request.getResponseType());
		} catch (IOException e) {
			throw new SteamApiException(SteamApiException.Cause.MAPPING, e);
		}
		return result;
	}

	public String getKey() {
		return key;
	}

	public boolean isUseHttps() {
		return useHttps;
	}

	/**
	 * Builder class to create instances of {@link SteamWebApiClient}. Required
	 * parameters are
	 * <ul>
	 * <li>key : your Steam Web API Key</li>
	 * </ul>
	 * Optional parameters are
	 * <ul>
	 * <li>useHttps : whether to communicate with Steam via HTTPS or plain HTTP.
	 * Default is <strong>false</strong>.</li>
	 * </ul>
	 *
	 * @author lpradel
	 *
	 */
	public static class SteamWebApiClientBuilder {

		private final String key;

		private boolean useHttps = true;

		/**
		 * Creates an instance of this class using your Steam Web API Key. Usage
		 * of HTTPS will be <strong>disabled</strong> by default, i.e. HTTP will
		 * be used to communicate with Steam.
		 *
		 * @param key
		 *            Your Steam Web API Key
		 * @see <a
		 *      href="http://steamcommunity.com/dev">http://steamcommunity.com/dev</a>
		 */
		public SteamWebApiClientBuilder(String key) {

			this.key = key;
		}

		public SteamWebApiClientBuilder useHttps(boolean useHttps) {

			this.useHttps = useHttps;
			return this;
		}

		public SteamWebApiClient build() {

			return new SteamWebApiClient(this);
		}
	}
}
