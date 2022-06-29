package com.lukaspradel.steamapi.core.exception;

public class SteamApiException extends RuntimeException {

	private static final long serialVersionUID = 6414882632273395318L;

	public enum Cause {
		HTTP_ERROR, FORBIDDEN, INTERNAL_ERROR, MAPPING
	}

	private String message;

	public SteamApiException(String message) {
		super(message);
	}

	public SteamApiException(String message, Throwable cause) {
		super(message, cause);
	}

	public SteamApiException(Cause cause, Throwable exceptionCause) {

		super(exceptionCause);

		switch (cause) {
		case MAPPING:
			this.message = "The JSON response could not be parsed or mapped to the designated POJO. The most likely cause for this is that"
					+ " the Steam API itself changed. Check for newer versions of this library to compensate for this.";
			break;
		default:
			this.message = "The Web API request failed due to an unexpected error: "
					+ exceptionCause.getMessage();
		}
	}

	public SteamApiException(Cause cause, Integer statusCode, String message) {

		switch (cause) {
		case HTTP_ERROR:
			this.message = "The Web API request failed with the following HTTP error: "
					+ message + " (status code: " + statusCode + ").";
			break;
		case FORBIDDEN:
			this.message = "The Web API request failed for security reasons. The supplied Web API key was rejected by Steam. Ensure that the supplied Web API key is valid.";
			break;
		case INTERNAL_ERROR:
			this.message = "The Web API request failed with the following internal error: "
					+ message + " (status code: " + statusCode + ").";
			break;
		default:
			this.message = "The Web API request failed due to an unexpected error.";
		}
	}

	@Override
	public String getMessage() {
		if (this.message == null) {
			return super.getMessage();
		} else {
			return this.message;
		}
	}
}
