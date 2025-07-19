package com.lukaspradel.steamapi.core.exception;

import com.lukaspradel.steamapi.webapi.request.HttpStatus;

public class SteamApiException extends Exception {

	private static final long serialVersionUID = 6414882632273395318L;

	public enum Cause {
		BAD_REQUEST,
		FORBIDDEN,
		NOT_FOUND,
		TOO_MANY_REQUESTS,
		INTERNAL_ERROR,
		BAD_GATEWAY,
		SERVICE_UNAVAILABLE,
		MAPPING
	}

	private final String message;
	private final Integer statusCode;

	public SteamApiException(Cause cause, Throwable exceptionCause) {

		super(exceptionCause);

		String message = getMessageByCause(cause);
		Integer statusCode = getStatusCodeByCause(cause);

		this.message = (message != null) ? message : exceptionCause.getMessage();
		this.statusCode = (statusCode != null) ? statusCode : HttpStatus.INTERNAL_SERVER_ERROR;
	}

	public SteamApiException(Cause cause, Integer statusCode) {

		this.statusCode = statusCode;

		String message = getMessageByCause(cause);

		if(message != null) {
			this.message = message;
		} else {
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

	public Integer getStatusCode() {
		return statusCode;
	}

	public static Cause getCauseByStatusCode(Integer statusCode) {

		if      (statusCode == HttpStatus.BAD_REQUEST) { return Cause.BAD_REQUEST; }
		else if (statusCode == HttpStatus.FORBIDDEN) { return Cause.FORBIDDEN; }
		else if (statusCode == HttpStatus.NOT_FOUND) { return Cause.NOT_FOUND; }
		else if (statusCode == HttpStatus.TOO_MANY_REQUESTS) { return Cause.TOO_MANY_REQUESTS; }
		else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR) { return Cause.INTERNAL_ERROR; }
		else if (statusCode == HttpStatus.BAD_GATEWAY) { return Cause.BAD_GATEWAY; }
		else if (statusCode == HttpStatus.SERVICE_UNAVAILABLE) { return Cause.SERVICE_UNAVAILABLE; }

		return null;
	}

	private static Integer getStatusCodeByCause(Cause cause) {
		if (cause == Cause.BAD_REQUEST) { return HttpStatus.BAD_REQUEST; }
		else if (cause == Cause.FORBIDDEN) { return HttpStatus.FORBIDDEN; }
		else if (cause == Cause.NOT_FOUND) { return HttpStatus.NOT_FOUND; }
		else if (cause == Cause.TOO_MANY_REQUESTS) { return HttpStatus.TOO_MANY_REQUESTS; }
		else if (cause == Cause.INTERNAL_ERROR) { return HttpStatus.INTERNAL_SERVER_ERROR; }
		else if (cause == Cause.BAD_GATEWAY) { return HttpStatus.BAD_GATEWAY; }
		else if (cause == Cause.SERVICE_UNAVAILABLE) { return HttpStatus.SERVICE_UNAVAILABLE; }

		return null;
	}

	private static String getMessageByCause(Cause cause) {

		if      (cause == Cause.BAD_REQUEST) { return "The Web API request failed. Wrong data format was provided."; }
		else if (cause == Cause.FORBIDDEN) { return "The Web API request failed for security reasons. The supplied Web API key was rejected by Steam. Ensure that the supplied Web API key is valid."; }
		else if (cause == Cause.NOT_FOUND) { return "Failed to found requested resource."; }
		else if (cause == Cause.TOO_MANY_REQUESTS) { return "The Web API request rejected. Because too many requests were processed."; }
		else if (cause == Cause.INTERNAL_ERROR) { return "The Web API request failed with an internal error."; }
		else if (cause == Cause.BAD_GATEWAY) { return "The Web API gateway failed to route request."; }
		else if (cause == Cause.SERVICE_UNAVAILABLE) { return "The Web API service unavailable."; }
		else if (cause == Cause.MAPPING) {return "The JSON response could not be parsed or mapped to the designated POJO. The most likely cause for this is that"
				+ " the Steam API itself changed. Check for newer versions of this library to compensate for this."; }

		return null;
	}
}
