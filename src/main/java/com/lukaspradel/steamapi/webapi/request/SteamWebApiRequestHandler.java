package com.lukaspradel.steamapi.webapi.request;

import com.lukaspradel.steamapi.core.SteamApiRequestHandler;
import com.lukaspradel.steamapi.core.exception.SteamApiException;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Map;
import java.util.stream.Stream;

import static com.lukaspradel.steamapi.core.exception.SteamApiException.Cause.INTERNAL_ERROR;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.stream.Collectors.joining;

public class SteamWebApiRequestHandler extends SteamApiRequestHandler {

	private static final int UNAUTHORIZED = 401;

	public SteamWebApiRequestHandler(boolean useHttps, String key) {
		super(useHttps, key);
	}

	public String getWebApiResponse(SteamWebApiRequest request) throws SteamApiException {
		return getWebApiResponse(getRequestUrl(request));
	}

	URI getRequestUrl(SteamWebApiRequest request) {
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

	String getRequestQuery(Map<String, String> parameters) {
		if (getKey() == null) {
			throw new IllegalArgumentException("Steam API key is not present or null");
		}

		for (var e : parameters.entrySet()) {
			if (e.getKey() == null) {
				throw new IllegalArgumentException("The key of the parameter with the value '" + e.getValue() + "' is null");
			}
		}

		parameters.put("key", getKey());
		return parameters.entrySet().stream()
				.map(e -> {
					String v = e.getValue(); // if the value of the parameter is null, we replace it with an empty String
					return e.getKey() + '=' + URLEncoder.encode(v == null ? "" : v, UTF_8);
				})
				.collect(joining("&"));
	}

	URI getRequestUri(String scheme, String host, String path, String query) {
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
			throw new IllegalArgumentException(
					"Failed to construct a valid request URI due to the following error: " + e.getMessage(), e);
		}
	}

	String getWebApiResponse(URI requestUrl) throws SteamApiException {
		HttpClient client = getHttpClient();
		HttpRequest getRequest = HttpRequest.newBuilder(requestUrl).build();

		try {
			var response = client.send(getRequest, BodyHandlers.ofString());
			int statusCode = response.statusCode();

			if (Integer.toString(statusCode).startsWith("20")) {
				return response.body();
			} else if (statusCode == UNAUTHORIZED) {
				throw new SteamApiException(SteamApiException.Cause.FORBIDDEN, statusCode);
			} else {
				throw new SteamApiException(SteamApiException.Cause.HTTP_ERROR, statusCode);
			}
		} catch (IOException | InterruptedException e) {
			throw new SteamApiException(INTERNAL_ERROR, e);
		}
	}

	HttpClient getHttpClient() {
		return HttpClient.newHttpClient();
	}
}
