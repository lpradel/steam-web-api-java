package com.lukaspradel.steamapi.core.exception;

public class SteamApiKeyException extends SteamApiException {

	private static final long serialVersionUID = 5154813503049374498L;

	public SteamApiKeyException(String message) {
		super(message, 400);
	}
}
