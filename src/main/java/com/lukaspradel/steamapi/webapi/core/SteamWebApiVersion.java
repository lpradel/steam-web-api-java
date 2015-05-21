package com.lukaspradel.steamapi.webapi.core;

/**
 * Wraps the currently supported versions of the various Web API Interface
 * methods.
 *
 * @author lpradel
 *
 */
public enum SteamWebApiVersion {

	VERSION_ONE("v0001"), VERSION_TWO("v0002");

	private final String version;

	private SteamWebApiVersion(String version) {
		this.version = version;
	}

	/**
	 * Returns the most current version of the given Web API Interface method.
	 *
	 * @param interfaceMethod
	 *            The Web API Interface method.
	 * @return The most current version of the given Web API Interface method.
	 */
	public static SteamWebApiVersion getCurrentVersionForWebApiInterfaceMethod(
			SteamWebApiInterfaceMethod interfaceMethod) {

		if (interfaceMethod == null) {
			throw new IllegalArgumentException(
					"Unsupported Web API Interface method!");
		}

		switch (interfaceMethod) {

		case GET_FRIEND_LIST:
			return VERSION_ONE;
		case GET_GLOBAL_ACHIEVEMENT_PERCENTAGES_FOR_APP:
			return VERSION_TWO;
		case GET_GLOBAL_STATS_FOR_GAME:
			return VERSION_ONE;
		case GET_NEWS_FOR_APP:
			return VERSION_TWO;
		case GET_OWNED_GAMES:
			return VERSION_ONE;
		case GET_PLAYER_ACHIEVEMENTS:
			return VERSION_ONE;
		case GET_PLAYER_SUMMARIES:
			return VERSION_TWO;
		case GET_RECENTLY_PLAYED_GAMES:
			return VERSION_ONE;
		case GET_USER_STATS_FOR_GAME:
			return VERSION_TWO;
		case IS_PLAYING_SHARED_GAME:
			return VERSION_ONE;
		case GET_SCHEMA_FOR_GAME:
			return VERSION_TWO;
		case GET_PLAYER_BANS:
			return VERSION_ONE;
		default:
			throw new IllegalArgumentException(
					"Unsupported Web API Interface method!");
		}
	}

	@Override
	public String toString() {
		return version;
	}
}
