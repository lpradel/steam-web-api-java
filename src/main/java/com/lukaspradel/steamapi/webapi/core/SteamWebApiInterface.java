package com.lukaspradel.steamapi.webapi.core;

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

	I_STEAM_NEWS("ISteamNews"),
	I_PLAYER_SERVICE("IPlayerService"),
	I_STEAM_USER("ISteamUser"),
	I_STEAM_USER_STATS("ISteamUserStats"),
	I_ECON_DOTA2("IEconDOTA2_570"),
	I_DOTA2_MATCH("IDOTA2Match_570"),
	I_DOTA2_FANTASY("IDOTA2Fantasy_570"),
	I_ECON_TF2("IEconItems_440"),
	I_STEAM_APPS("ISteamApps");

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

		if (interfaceMethod == null) {
			throw new IllegalArgumentException(
					"Unsupported Web API Interface method!");
		}

		switch (interfaceMethod) {

		case GET_FRIEND_LIST:
			return I_STEAM_USER;
		case GET_GLOBAL_ACHIEVEMENT_PERCENTAGES_FOR_APP:
			return I_STEAM_USER_STATS;
		case GET_GLOBAL_STATS_FOR_GAME:
			return I_STEAM_USER_STATS;
		case GET_NEWS_FOR_APP:
			return I_STEAM_NEWS;
		case GET_OWNED_GAMES:
			return I_PLAYER_SERVICE;
		case GET_PLAYER_ACHIEVEMENTS:
			return I_STEAM_USER_STATS;
		case GET_PLAYER_SUMMARIES:
			return I_STEAM_USER;
		case GET_RECENTLY_PLAYED_GAMES:
			return I_PLAYER_SERVICE;
		case GET_USER_STATS_FOR_GAME:
			return I_STEAM_USER_STATS;
		case IS_PLAYING_SHARED_GAME:
			return I_PLAYER_SERVICE;
		case GET_SCHEMA_FOR_GAME:
			return I_STEAM_USER_STATS;
		case GET_PLAYER_BANS:
			return I_STEAM_USER;
		case GET_HEROES:
			return I_ECON_DOTA2;
		case GET_MATCH_HISTORY:
			return I_DOTA2_MATCH;
		case GET_MATCH_DETAILS:
			return I_DOTA2_MATCH;
		case GET_LEAGUE_LISTING:
			return I_DOTA2_MATCH;
		case GET_LIVE_LEAGUE_GAMES:
			return I_DOTA2_MATCH;
		case GET_MATCH_HISTORY_BY_SEQUENCE_NUM:
			return I_DOTA2_MATCH;
		case GET_TEAM_INFO_BY_TEAM_ID:
			return I_DOTA2_MATCH;
		case GET_GAME_ITEMS:
			return I_ECON_DOTA2;
		case GET_PRO_PLAYER_LIST:
			return I_DOTA2_FANTASY;
		case GET_PLAYER_OFFICIAL_INFO:
			return I_DOTA2_FANTASY;
		case GET_FANTASY_PLAYER_STATS:
			return I_DOTA2_FANTASY;
		case GET_APP_LIST:
			return I_STEAM_APPS;
		case RESOLVE_VANITY_URL:
			return I_STEAM_USER;
		case GET_PLAYER_ITEMS:
			return I_ECON_TF2;
		case GET_SCHEMA_ITEMS:
			return I_ECON_TF2;
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
