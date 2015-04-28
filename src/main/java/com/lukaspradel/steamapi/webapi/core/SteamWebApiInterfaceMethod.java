package com.lukaspradel.steamapi.webapi.core;

import com.lukaspradel.steamapi.data.json.achievementpercentages.GetGlobalAchievementPercentagesForApp;
import com.lukaspradel.steamapi.data.json.appnews.GetNewsForApp;
import com.lukaspradel.steamapi.data.json.friendslist.GetFriendList;
import com.lukaspradel.steamapi.data.json.getplayerbans.GetPlayerBans;
import com.lukaspradel.steamapi.data.json.getschemaforgame.GetSchemaForGame;
import com.lukaspradel.steamapi.data.json.isplayingsharedgame.IsPlayingSharedGame;
import com.lukaspradel.steamapi.data.json.ownedgames.GetOwnedGames;
import com.lukaspradel.steamapi.data.json.playerachievements.GetPlayerAchievements;
import com.lukaspradel.steamapi.data.json.playerstats.GetUserStatsForGame;
import com.lukaspradel.steamapi.data.json.playersummaries.GetPlayerSummaries;
import com.lukaspradel.steamapi.data.json.recentlyplayedgames.GetRecentlyPlayedGames;

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

	GET_NEWS_FOR_APP("GetNewsForApp", GetNewsForApp.class), GET_GLOBAL_ACHIEVEMENT_PERCENTAGES_FOR_APP(
			"GetGlobalAchievementPercentagesForApp",
			GetGlobalAchievementPercentagesForApp.class), GET_PLAYER_SUMMARIES(
			"GetPlayerSummaries", GetPlayerSummaries.class), GET_FRIEND_LIST(
			"GetFriendList", GetFriendList.class), GET_PLAYER_ACHIEVEMENTS(
			"GetPlayerAchievements", GetPlayerAchievements.class), GET_USER_STATS_FOR_GAME(
			"GetUserStatsForGame", GetUserStatsForGame.class), GET_OWNED_GAMES(
			"GetOwnedGames", GetOwnedGames.class), GET_RECENTLY_PLAYED_GAMES(
			"GetRecentlyPlayedGames", GetRecentlyPlayedGames.class), IS_PLAYING_SHARED_GAME(
			"IsPlayingSharedGame", IsPlayingSharedGame.class), GET_SCHEMA_FOR_GAME(
			"GetSchemaForGame", GetSchemaForGame.class), GET_PLAYER_BANS(
			"GetPlayerBans", GetPlayerBans.class);

	private final String interfaceMethod;

	private final Class<?> responseType;

	private SteamWebApiInterfaceMethod(String interfaceMethod,
			Class<?> responseType) {

		this.interfaceMethod = interfaceMethod;
		this.responseType = responseType;
	}

	public Class<?> getReponseType() {
		return responseType;
	}

	@Override
	public String toString() {
		return interfaceMethod;
	}
}
