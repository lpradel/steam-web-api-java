package com.lukaspradel.steamapi.webapi;

/**
 * Currently supported Steam Web API Interface Methods as listed on Valve's Dev
 * Wiki.
 *
 * @see <a
 *      href="https://developer.valvesoftware.com/wiki/Steam_Web_API">https://developer.valvesoftware.com/wiki/Steam_Web_API</a>
 *
 * @author lpradel
 *
 */
public enum SteamWebApiInterfaceMethod {

	GET_NEWS_FOR_APP("GetNewsForApp"), GET_GLOBAL_ACHIEVEMENT_PERCENTAGES_FOR_APP(
			"GetGlobalAchievementPercentagesForApp"), GET_PLAYER_SUMMARIES(
			"GetPlayerSummaries"), GET_FRIEND_LIST("GetFriendList"), GET_USER_STATS_FOR_GAME(
			"GetUserStatsForGame"), GET_OWNED_GAMES("GetOwnedGames"), GET_RECENTLY_PLAYED_GAMES(
			"GetRecentlyPlayedGames"), IS_PLAYING_SHARED_GAME(
			"IsPlayingSharedGame");

	private final String interfaceMethod;

	private SteamWebApiInterfaceMethod(String interfaceMethod) {
		this.interfaceMethod = interfaceMethod;
	}

	@Override
	public String toString() {
		return interfaceMethod;
	}
}
