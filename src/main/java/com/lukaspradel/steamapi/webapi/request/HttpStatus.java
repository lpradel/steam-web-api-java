package com.lukaspradel.steamapi.webapi.request;

public class HttpStatus{
	public static final int OK = 200;
	public static final int CREATED = 201;
	public static final int ACCEPTED = 202;
	public static final int NO_CONTENT = 204;
	public static final int BAD_REQUEST = 400;
	public static final int FORBIDDEN = 403;
	public static final int NOT_FOUND = 404;
	public static final int TOO_MANY_REQUESTS = 429;
	public static final int INTERNAL_SERVER_ERROR = 500;
	public static final int BAD_GATEWAY = 502;
	public static final int SERVICE_UNAVAILABLE = 503;


	public static boolean Is2xxStatus(int statusCode) {
		return statusCode >= 200 && statusCode < 300;
	}

	public static boolean Is4xxStatus(int statusCode) {
		return statusCode >= 400 && statusCode < 500;
	}

	public static boolean Is5xxStatus(int statusCode) {
		return statusCode >= 500 && statusCode < 600;
	}
}
