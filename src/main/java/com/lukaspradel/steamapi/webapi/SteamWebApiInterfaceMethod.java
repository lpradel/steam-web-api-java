package com.lukaspradel.steamapi.webapi;

import com.lukaspradel.steamapi.data.json.achievementpercentages.AchievementpercentagesSchema;
import com.lukaspradel.steamapi.data.json.appnews.AppnewsSchema;
import com.lukaspradel.steamapi.data.json.friendslist.FriendslistSchema;
import com.lukaspradel.steamapi.data.json.isplayingsharedgame.IsplayingsharedgameSchema;
import com.lukaspradel.steamapi.data.json.ownedgames.OwnedgamesSchema;
import com.lukaspradel.steamapi.data.json.playerstats.PlayerstatsSchema;
import com.lukaspradel.steamapi.data.json.playersummaries.PlayersummariesSchema;
import com.lukaspradel.steamapi.data.json.recentlyplayedgames.RecentlyplayedgamesSchema;

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

	GET_NEWS_FOR_APP("GetNewsForApp", AppnewsSchema.class), GET_GLOBAL_ACHIEVEMENT_PERCENTAGES_FOR_APP(
			"GetGlobalAchievementPercentagesForApp",
			AchievementpercentagesSchema.class), GET_PLAYER_SUMMARIES(
			"GetPlayerSummaries", PlayersummariesSchema.class), GET_FRIEND_LIST(
			"GetFriendList", FriendslistSchema.class), GET_USER_STATS_FOR_GAME(
			"GetUserStatsForGame", PlayerstatsSchema.class), GET_OWNED_GAMES(
			"GetOwnedGames", OwnedgamesSchema.class), GET_RECENTLY_PLAYED_GAMES(
			"GetRecentlyPlayedGames", RecentlyplayedgamesSchema.class), IS_PLAYING_SHARED_GAME(
			"IsPlayingSharedGame", IsplayingsharedgameSchema.class);

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
