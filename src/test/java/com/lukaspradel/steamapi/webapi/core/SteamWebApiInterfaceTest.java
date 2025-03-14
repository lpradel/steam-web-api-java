package com.lukaspradel.steamapi.webapi.core;

import com.lukaspradel.steamapi.BaseTest;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class SteamWebApiInterfaceTest extends BaseTest {

	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testGetInterfaceForMethodError() {

		SteamWebApiInterface.getInterfaceForMethod(null);
	}

	@Test
	public void testGetInterfaceForMethod() {

		assertEquals(
				SteamWebApiInterface
						.getInterfaceForMethod(SteamWebApiInterfaceMethod.GET_FRIEND_LIST),
				SteamWebApiInterface.I_STEAM_USER);

		assertEquals(
				SteamWebApiInterface
						.getInterfaceForMethod(SteamWebApiInterfaceMethod.GET_GLOBAL_ACHIEVEMENT_PERCENTAGES_FOR_APP),
				SteamWebApiInterface.I_STEAM_USER_STATS);

		assertEquals(
				SteamWebApiInterface
						.getInterfaceForMethod(SteamWebApiInterfaceMethod.GET_GLOBAL_STATS_FOR_GAME),
				SteamWebApiInterface.I_STEAM_USER_STATS);

		assertEquals(
				SteamWebApiInterface
						.getInterfaceForMethod(SteamWebApiInterfaceMethod.GET_NEWS_FOR_APP),
				SteamWebApiInterface.I_STEAM_NEWS);

		assertEquals(
				SteamWebApiInterface
						.getInterfaceForMethod(SteamWebApiInterfaceMethod.GET_OWNED_GAMES),
				SteamWebApiInterface.I_PLAYER_SERVICE);

		assertEquals(
				SteamWebApiInterface
						.getInterfaceForMethod(SteamWebApiInterfaceMethod.GET_PLAYER_SUMMARIES),
				SteamWebApiInterface.I_STEAM_USER);

		assertEquals(
				SteamWebApiInterface
						.getInterfaceForMethod(SteamWebApiInterfaceMethod.GET_RECENTLY_PLAYED_GAMES),
				SteamWebApiInterface.I_PLAYER_SERVICE);

		assertEquals(
				SteamWebApiInterface
						.getInterfaceForMethod(SteamWebApiInterfaceMethod.GET_USER_STATS_FOR_GAME),
				SteamWebApiInterface.I_STEAM_USER_STATS);

		assertEquals(
				SteamWebApiInterface
						.getInterfaceForMethod(SteamWebApiInterfaceMethod.GET_PLAYER_ACHIEVEMENTS),
				SteamWebApiInterface.I_STEAM_USER_STATS);

		assertEquals(
				SteamWebApiInterface
						.getInterfaceForMethod(SteamWebApiInterfaceMethod.IS_PLAYING_SHARED_GAME),
				SteamWebApiInterface.I_PLAYER_SERVICE);

		assertEquals(
				SteamWebApiInterface
						.getInterfaceForMethod(SteamWebApiInterfaceMethod.GET_SCHEMA_FOR_GAME),
				SteamWebApiInterface.I_STEAM_USER_STATS);

		assertEquals(
				SteamWebApiInterface
						.getInterfaceForMethod(SteamWebApiInterfaceMethod.GET_PLAYER_BANS),
				SteamWebApiInterface.I_STEAM_USER);

		assertEquals(
				SteamWebApiInterface
						.getInterfaceForMethod(SteamWebApiInterfaceMethod.GET_APP_LIST),
				SteamWebApiInterface.I_STEAM_APPS);

		assertEquals(
				SteamWebApiInterface
						.getInterfaceForMethod(SteamWebApiInterfaceMethod.RESOLVE_VANITY_URL),
				SteamWebApiInterface.I_STEAM_USER);

		assertEquals(
				SteamWebApiInterface
						.getInterfaceForMethod(SteamWebApiInterfaceMethod.GET_PLAYER_ITEMS),
				SteamWebApiInterface.I_ECON_TF2);
	}

	@Test
	public void testGetInterfaceForMethodNoMissingValue() {

		SteamWebApiInterfaceMethod[] valuesArray = SteamWebApiInterfaceMethod
				.values();
		List<SteamWebApiInterfaceMethod> values = Arrays.asList(valuesArray);

		for (SteamWebApiInterfaceMethod value : values) {

			SteamWebApiInterface.getInterfaceForMethod(value);
		}

		// An exception will be raised in the default case if any of the enum
		// values are not implemented in the switch statement!
	}
}
