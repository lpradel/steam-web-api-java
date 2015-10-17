package com.lukaspradel.steamapi.webapi.request;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.lukaspradel.steamapi.core.SteamApiRequestHandler;
import com.lukaspradel.steamapi.core.exception.SteamApiException;

public class SteamWebApiRequestHandler extends SteamApiRequestHandler {

	public SteamWebApiRequestHandler(boolean useHttps, String key) {
		super(useHttps, key);
	}

	public String getWebApiResponse(SteamWebApiRequest request)
			throws SteamApiException {

		URI requestUrl = getRequestUrl(request);
		return getWebApiResponse(requestUrl);
	}

	URI getRequestUrl(SteamWebApiRequest request) throws SteamApiException {

		String scheme = getProtocol();
		String host = request.getBaseUrl();
		String path = getRequestPath(request);
		List<NameValuePair> parameters = getRequestParameters(request
				.getParameters());

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

		List<NameValuePair> nvps = new ArrayList<NameValuePair>();

		nvps.add(new BasicNameValuePair("key", getKey()));

		for (Map.Entry<String, String> param : parametersMap.entrySet()) {

			nvps.add(new BasicNameValuePair(param.getKey(), param.getValue()));
		}

		return nvps;
	}

	URI getRequestUri(String scheme, String host, String path,
			List<NameValuePair> parameters) throws SteamApiException {

		try {
			URI requestUri = new URIBuilder().setScheme(scheme).setHost(host)
					.setPath(path).setParameters(parameters).build();
			return requestUri;
		} catch (URISyntaxException e) {
			throw new SteamApiException(
					"Failed to process the Web API request due to the following error: "
							+ e.getMessage(), e);
		}
	}

	String getWebApiResponse(URI requestUrl) throws SteamApiException {

		HttpClient client = getHttpClient();
		HttpGet getRequest = new HttpGet(requestUrl);

		try {
			HttpResponse response = client.execute(getRequest);

			Integer statusCode = response.getStatusLine().getStatusCode();

			if (!statusCode.toString().startsWith("20")) {
				if (statusCode.equals(HttpStatus.SC_UNAUTHORIZED)) {
					throw new SteamApiException(
							SteamApiException.Cause.FORBIDDEN, statusCode,
							response.getStatusLine().getReasonPhrase());
				}
				throw new SteamApiException(SteamApiException.Cause.HTTP_ERROR,
						statusCode, response.getStatusLine().getReasonPhrase());
			}

			return getHttpResponseAsString(response);
		} catch (IOException e) {
			throw new SteamApiException(
					"The Web API request failed due to the following error: "
							+ e.getMessage(), e);
		} finally {
			getRequest.releaseConnection();
		}
	}

	HttpClient getHttpClient() {

		return HttpClientBuilder.create().build();
	}

	String getHttpResponseAsString(HttpResponse response)
			throws ParseException, IOException {

		return EntityUtils.toString(response.getEntity());
	}
}
