package com.lukaspradel.steamapi.webapi.core;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import com.lukaspradel.steamapi.BaseTest;

public class SteamWebApiInterfaceTest extends BaseTest {

	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testGetInterfaceMethodError() {

		SteamWebApiInterface.getInterfaceForMethod(null);
	}

	@Test
	public void testGetInterfaceMethod() {

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
						.getInterfaceForMethod(SteamWebApiInterfaceMethod.IS_PLAYING_SHARED_GAME),
				SteamWebApiInterface.I_PLAYER_SERVICE);
	}
}
