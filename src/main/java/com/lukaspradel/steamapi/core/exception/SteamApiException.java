package com.lukaspradel.steamapi.core.exception;

import com.lukaspradel.steamapi.webapi.request.HttpStatus;

public class SteamApiException extends Exception {

	private static final long serialVersionUID = 6414882632273395318L;

	private final String message;
	private final Integer statusCode;

	public SteamApiException(Throwable exceptionCause) {
		super(exceptionCause);

		this.message = "The Web API request failed due to an unexpected error: " + exceptionCause.getMessage();
		this.statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
	}

	public SteamApiException(int statusCode) {
		this.statusCode = statusCode;
		this.message = getMessageByStatusCode(statusCode);
	}

	@Override
	public String getMessage() {
		if (this.message == null) {
			return super.getMessage();
		} else {
			return this.message;
		}
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	private static String getMessageByStatusCode(int statusCode) {

		if      (statusCode == HttpStatus.BAD_REQUEST) { return "The Web API request failed. Wrong data format was provided."; }
		else if (statusCode == HttpStatus.FORBIDDEN) { return "The Web API request failed for security reasons. The supplied Web API key was rejected by Steam. Ensure that the supplied Web API key is valid."; }
		else if (statusCode == HttpStatus.NOT_FOUND) { return "Failed to found requested resource."; }
		else if (statusCode == HttpStatus.TOO_MANY_REQUESTS) { return "The Web API request rejected. Because too many requests were processed."; }
		else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR) { return "The Web API request failed with an internal error."; }
		else if (statusCode == HttpStatus.BAD_GATEWAY) { return "The Web API gateway failed to route request."; }
		else if (statusCode == HttpStatus.SERVICE_UNAVAILABLE) { return "The Web API service unavailable."; }

		return "";
	}
}
