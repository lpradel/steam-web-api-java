package com.lukaspradel.steamapi.webapi.request;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.stream.Collectors.joining;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Map;
import java.util.stream.Stream;

import com.lukaspradel.steamapi.core.SteamApiRequestHandler;
import com.lukaspradel.steamapi.core.exception.SteamApiException;
import com.lukaspradel.steamapi.core.exception.SteamApiKeyException;

public class SteamWebApiRequestHandler extends SteamApiRequestHandler {

	private static final int OK           = 200;
	private static final int UNAUTHORIZED = 401;

	public SteamWebApiRequestHandler(boolean useHttps, String key) {
		super(useHttps, key);
	}

	public String getWebApiResponse(SteamWebApiRequest request) throws SteamApiException {
		return getWebApiResponse(getRequestUrl(request));
	}

	URI getRequestUrl(SteamWebApiRequest request) throws SteamApiException {
		String scheme = getProtocol();
		String host = request.getBaseUrl();
		String path = getRequestPath(request);
		String query = getRequestQuery(request.getParameters());

		return getRequestUri(scheme, host, path, query);
	}

	String getRequestPath(SteamWebApiRequest request) {
		// creates "/ApiInterface/InterfaceMethod/Version"
		return Stream.of(
				request.getApiInterface(),
				request.getInterfaceMethod(),
				request.getVersion()
		)
		.map(Object::toString)
		.collect(joining("/", "/", ""));
	}

	String getRequestQuery(Map<String, String> parameters) throws SteamApiException {
		if (getKey() == null)
			throw new SteamApiKeyException("Steam API key is not present or null");

		for (var e : parameters.entrySet())
			if (e.getKey() == null)
				throw new SteamApiException("The key of the parameter with the value '" + e.getValue() + "' is null");

		parameters.put("key", getKey());
		return parameters.entrySet().stream()
				.map(e -> {
					String v = e.getValue(); // if the value of the parameter is null, we replace it with an empty String
					return e.getKey() + '=' + URLEncoder.encode(v == null ? "" : v, UTF_8);
				})
				.collect(joining("&"));
	}

	URI getRequestUri(String scheme, String host, String path, String query) throws SteamApiException {
		var uri = new StringBuilder();

		uri.append(scheme);
		uri.append("://");
		uri.append(host);
		uri.append(path);
		uri.append("?");
		uri.append(query);

		try {
			return new URI(uri.toString());
		} catch (URISyntaxException e) {
			throw new SteamApiException(
					"Failed to process the Web API request due to the following error: " + e.getMessage(), e);
		}
	}

	String getWebApiResponse(URI requestUrl) throws SteamApiException {
		HttpClient client = getHttpClient();
		HttpRequest getRequest = HttpRequest.newBuilder(requestUrl).build();

		try {
			var response = client.send(getRequest, BodyHandlers.ofString());
			int statusCode = response.statusCode();

			if (statusCode == OK)
				return response.body();

			if (statusCode == UNAUTHORIZED)
				throw new SteamApiException(SteamApiException.Cause.FORBIDDEN, statusCode);

			throw new SteamApiException(SteamApiException.Cause.HTTP_ERROR, statusCode);
		} catch (IOException | InterruptedException e) {
			throw new SteamApiException(
					"The Web API request failed due to the following error: " + e.getMessage(), e);
		}
	}

	HttpClient getHttpClient() {
		return HttpClient.newHttpClient();
	}
}
