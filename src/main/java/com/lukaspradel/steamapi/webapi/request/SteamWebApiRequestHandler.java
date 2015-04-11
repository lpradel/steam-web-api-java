package com.lukaspradel.steamapi.webapi.request;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.lukaspradel.steamapi.core.SteamApiRequestHandler;
import com.lukaspradel.steamapi.core.exception.SteamApiException;

public class SteamWebApiRequestHandler extends SteamApiRequestHandler {

	public SteamWebApiRequestHandler(boolean useHttps, String key) {
		super(useHttps, key);
	}

	public String getWebApiResponse(SteamWebApiRequest request)
			throws SteamApiException {

		String requestUrl = getRequestUrl(request);
		return getWebApiResponse(requestUrl);
	}

	String getRequestUrl(SteamWebApiRequest request) {

		String protocol = getProtocol();
		String parametersUrl = getParametersUrl(request.getParameters());
		String requestUrl = getRequestUrl(protocol, request, parametersUrl);

		return requestUrl;
	}

	String getParametersUrl(Map<String, String> parameters) {

		Map<String, String> parametersMap = new HashMap<String, String>(
				parameters);
		parametersMap.put("key", getKey());

		StringBuilder params = new StringBuilder();

		boolean first = true;
		for (Map.Entry<String, String> param : parametersMap.entrySet()) {

			if (first) {
				first = false;
			} else {
				params.append("&");
			}

			params.append(param.getKey());
			params.append("=");
			params.append(param.getValue());
		}

		return params.toString();
	}

	String getRequestUrl(String protocol, SteamWebApiRequest request,
			String parametersUrl) {

		StringBuilder requestUrl = new StringBuilder();

		requestUrl.append(protocol);
		requestUrl.append(request.getBaseUrl());
		requestUrl.append("/");
		requestUrl.append(request.getApiInterface());
		requestUrl.append("/");
		requestUrl.append(request.getInterfaceMethod());
		requestUrl.append("/");
		requestUrl.append(request.getVersion());
		requestUrl.append("/");
		requestUrl.append("?");
		requestUrl.append(parametersUrl);

		return requestUrl.toString();
	}

	String getWebApiResponse(String requestUrl) throws SteamApiException {

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
