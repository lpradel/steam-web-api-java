package com.lukaspradel.steamapi.webapi.request;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ClassicHttpResponse;
import org.apache.hc.core5.http.HttpStatus;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import org.apache.hc.core5.net.URIBuilder;

import com.lukaspradel.steamapi.core.SteamApiRequestHandler;
import com.lukaspradel.steamapi.core.exception.SteamApiException;

public class SteamWebApiRequestHandler extends SteamApiRequestHandler {

	public SteamWebApiRequestHandler(boolean useHttps, String key) {
		super(useHttps, key);
	}

	public String getWebApiResponse(SteamWebApiRequest request) throws SteamApiException {
		URI requestUrl = getRequestUrl(request);
		return getWebApiResponse(requestUrl);
	}

	URI getRequestUrl(SteamWebApiRequest request) throws SteamApiException {
		String scheme = getProtocol();
		String host = request.getBaseUrl();
		String path = getRequestPath(request);
		List<NameValuePair> parameters = getRequestParameters(request.getParameters());

		URI requestUrl = getRequestUri(scheme, host, path, parameters);

		return requestUrl;
	}

	String getRequestPath(SteamWebApiRequest request) {
		StringBuilder requestPath = new StringBuilder();

		requestPath.append("/");
		requestPath.append(request.getApiInterface().toString());
		requestPath.append("/");
		requestPath.append(request.getInterfaceMethod().toString());
		requestPath.append("/");
		requestPath.append(request.getVersion().toString());

		return requestPath.toString();
	}

	List<NameValuePair> getRequestParameters(Map<String, String> parametersMap) {
		List<NameValuePair> nvps = new ArrayList<>();

		nvps.add(new BasicNameValuePair("key", getKey()));
		parametersMap.entrySet().forEach(param -> nvps.add(new BasicNameValuePair(param.getKey(), param.getValue())));

		return nvps;
	}

	URI getRequestUri(String scheme, String host, String path, List<NameValuePair> parameters)
			throws SteamApiException {

		try {
			return new URIBuilder().setScheme(scheme).setHost(host).setPath(path).setParameters(parameters).build();
		} catch (URISyntaxException e) {
			throw new SteamApiException(
					"Failed to process the Web API request due to the following error: " + e.getMessage(), e);
		}
	}

	/**
	 * This method is based on the following example:
	 * https://github.com/apache/httpcomponents-client/blob/5.1.x/httpclient5/src/test/java/org/apache/hc/client5/http/examples/ClientWithResponseHandler.java
	 * 
	 * @author 41zu
	 */
	String getWebApiResponse(URI requestUrl) throws SteamApiException {

		try (CloseableHttpClient client = getHttpClient()) {
			final HttpGet getRequest = new HttpGet(requestUrl);

			// this execute method returns the response as a string from the anonymous class
			return client.execute(getRequest, response -> {
				final Integer statusCode = response.getCode();
				if (!statusCode.toString().startsWith("20")) {
					if (statusCode.equals(HttpStatus.SC_UNAUTHORIZED)) {
						throw new SteamApiException(SteamApiException.Cause.FORBIDDEN, statusCode,
								response.getReasonPhrase());
					}
					throw new SteamApiException(SteamApiException.Cause.HTTP_ERROR, statusCode,
							response.getReasonPhrase());
				}
				return getHttpResponseAsString(response);
			});
		} catch (IOException e) {
			throw new SteamApiException("The Web API request failed due to the following error: " + e.getMessage());
		}
	}

	CloseableHttpClient getHttpClient() {
		return HttpClients.createDefault();
	}

	String getHttpResponseAsString(ClassicHttpResponse response) throws ParseException, IOException {
		return EntityUtils.toString(response.getEntity());
	}
}