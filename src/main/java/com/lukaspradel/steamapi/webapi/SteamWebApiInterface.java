package com.lukaspradel.steamapi.webapi;

/**
 * Currently supported Steam Web API Interfaces as listed on Valve's Dev Wiki.
 *
 * @see <a
 *      href="https://developer.valvesoftware.com/wiki/Steam_Web_API">https://developer.valvesoftware.com/wiki/Steam_Web_API</a>
 *
 * @author lpradel
 *
 */
public enum SteamWebApiInterface {

	I_STEAM_NEWS("ISteamNews"), I_PLAYER_SERVICE("IPlayerService"), I_STEAM_USER(
			"ISteamUser"), I_STEAM_USER_STATS("ISteamUserStats");

	private final String apiInterface;

	private SteamWebApiInterface(String apiInterface) {
		this.apiInterface = apiInterface;
	}

	/**
	 * Returns the interface associated with the given Web Api Interface method.
	 *
	 * @param interfaceMethod
	 *            The Web API Interface method.
	 * @return The interface associated with the given Web Api Interface method.
	 */
	public static SteamWebApiInterface getInterfaceForMethod(
			SteamWebApiInterfaceMethod interfaceMethod) {

		switch (interfaceMethod) {

		case GET_FRIEND_LIST:
			return I_STEAM_USER;
		case GET_GLOBAL_ACHIEVEMENT_PERCENTAGES_FOR_APP:
			return I_STEAM_USER_STATS;
		case GET_NEWS_FOR_APP:
			return I_STEAM_NEWS;
		case GET_OWNED_GAMES:
			return I_PLAYER_SERVICE;
		case GET_PLAYER_SUMMARIES:
			return I_STEAM_USER;
		case GET_RECENTLY_PLAYED_GAMES:
			return I_PLAYER_SERVICE;
		case GET_USER_STATS_FOR_GAME:
			return I_STEAM_USER_STATS;
		case IS_PLAYING_SHARED_GAME:
			return I_PLAYER_SERVICE;
		default:
			throw new IllegalArgumentException(
					"Unsupported Web API Interface method!");
		}
	}

	@Override
	public String toString() {
		return apiInterface;
	}
}
