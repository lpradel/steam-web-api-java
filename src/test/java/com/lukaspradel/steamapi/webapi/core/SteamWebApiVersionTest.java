package com.lukaspradel.steamapi.webapi.core;

import com.lukaspradel.steamapi.BaseTest;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class SteamWebApiVersionTest extends BaseTest {

	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testGetCurrentVersionForWebApiInterfaceMethodError() {

		SteamWebApiVersion.getCurrentVersionForWebApiInterfaceMethod(null);
	}

	@Test
	public void testGetCurrentVersionForWebApiInterfaceMethod() {

		assertEquals(
				SteamWebApiVersion
						.getCurrentVersionForWebApiInterfaceMethod(SteamWebApiInterfaceMethod.GET_FRIEND_LIST),
				SteamWebApiVersion.VERSION_ONE);

		assertEquals(
				SteamWebApiVersion
						.getCurrentVersionForWebApiInterfaceMethod(SteamWebApiInterfaceMethod.GET_GLOBAL_ACHIEVEMENT_PERCENTAGES_FOR_APP),
				SteamWebApiVersion.VERSION_TWO);

		assertEquals(
				SteamWebApiVersion
						.getCurrentVersionForWebApiInterfaceMethod(SteamWebApiInterfaceMethod.GET_GLOBAL_STATS_FOR_GAME),
				SteamWebApiVersion.VERSION_ONE);

		assertEquals(
				SteamWebApiVersion
						.getCurrentVersionForWebApiInterfaceMethod(SteamWebApiInterfaceMethod.GET_NEWS_FOR_APP),
				SteamWebApiVersion.VERSION_TWO);

		assertEquals(
				SteamWebApiVersion
						.getCurrentVersionForWebApiInterfaceMethod(SteamWebApiInterfaceMethod.GET_OWNED_GAMES),
				SteamWebApiVersion.VERSION_ONE);

		assertEquals(
				SteamWebApiVersion
						.getCurrentVersionForWebApiInterfaceMethod(SteamWebApiInterfaceMethod.GET_PLAYER_ACHIEVEMENTS),
				SteamWebApiVersion.VERSION_ONE);

		assertEquals(
				SteamWebApiVersion
						.getCurrentVersionForWebApiInterfaceMethod(SteamWebApiInterfaceMethod.GET_PLAYER_SUMMARIES),
				SteamWebApiVersion.VERSION_TWO);

		assertEquals(
				SteamWebApiVersion
						.getCurrentVersionForWebApiInterfaceMethod(SteamWebApiInterfaceMethod.GET_RECENTLY_PLAYED_GAMES),
				SteamWebApiVersion.VERSION_ONE);

		assertEquals(
				SteamWebApiVersion
						.getCurrentVersionForWebApiInterfaceMethod(SteamWebApiInterfaceMethod.GET_USER_STATS_FOR_GAME),
				SteamWebApiVersion.VERSION_TWO);

		assertEquals(
				SteamWebApiVersion
						.getCurrentVersionForWebApiInterfaceMethod(SteamWebApiInterfaceMethod.IS_PLAYING_SHARED_GAME),
				SteamWebApiVersion.VERSION_ONE);

		assertEquals(
				SteamWebApiVersion
						.getCurrentVersionForWebApiInterfaceMethod(SteamWebApiInterfaceMethod.GET_SCHEMA_FOR_GAME),
				SteamWebApiVersion.VERSION_TWO);

		assertEquals(
				SteamWebApiVersion
						.getCurrentVersionForWebApiInterfaceMethod(SteamWebApiInterfaceMethod.GET_PLAYER_BANS),
				SteamWebApiVersion.VERSION_ONE);
		assertEquals(
				SteamWebApiVersion
						.getCurrentVersionForWebApiInterfaceMethod(SteamWebApiInterfaceMethod.GET_GAME_ITEMS),
				SteamWebApiVersion.VERSION_ONE);

		assertEquals(
				SteamWebApiVersion
						.getCurrentVersionForWebApiInterfaceMethod(SteamWebApiInterfaceMethod.GET_HEROES),
				SteamWebApiVersion.VERSION_ONE);

		assertEquals(
				SteamWebApiVersion
						.getCurrentVersionForWebApiInterfaceMethod(SteamWebApiInterfaceMethod.GET_LEAGUE_LISTING),
				SteamWebApiVersion.VERSION_ONE);

		assertEquals(
				SteamWebApiVersion
						.getCurrentVersionForWebApiInterfaceMethod(SteamWebApiInterfaceMethod.GET_LIVE_LEAGUE_GAMES),
				SteamWebApiVersion.VERSION_ONE);

		assertEquals(
				SteamWebApiVersion
						.getCurrentVersionForWebApiInterfaceMethod(SteamWebApiInterfaceMethod.GET_MATCH_DETAILS),
				SteamWebApiVersion.VERSION_ONE);

		assertEquals(
				SteamWebApiVersion
						.getCurrentVersionForWebApiInterfaceMethod(SteamWebApiInterfaceMethod.GET_MATCH_HISTORY),
				SteamWebApiVersion.VERSION_ONE);

		assertEquals(
				SteamWebApiVersion
						.getCurrentVersionForWebApiInterfaceMethod(SteamWebApiInterfaceMethod.GET_MATCH_HISTORY_BY_SEQUENCE_NUM),
				SteamWebApiVersion.VERSION_ONE);

		assertEquals(
				SteamWebApiVersion
						.getCurrentVersionForWebApiInterfaceMethod(SteamWebApiInterfaceMethod.GET_TEAM_INFO_BY_TEAM_ID),
				SteamWebApiVersion.VERSION_ONE);

		assertEquals(
				SteamWebApiVersion
						.getCurrentVersionForWebApiInterfaceMethod(SteamWebApiInterfaceMethod.GET_APP_LIST),
				SteamWebApiVersion.VERSION_TWO);
	}

	@Test
	public void testGetCurrentVersionForWebApiInterfaceMethodNoMissingValue() {

		SteamWebApiInterfaceMethod[] valuesArray = SteamWebApiInterfaceMethod
				.values();
		List<SteamWebApiInterfaceMethod> values = Arrays.asList(valuesArray);

		for (SteamWebApiInterfaceMethod value : values) {

			SteamWebApiVersion.getCurrentVersionForWebApiInterfaceMethod(value);
		}

		// An exception will be raised in the default case if any of the enum
		// values are not implemented in the switch statement!
	}
}
