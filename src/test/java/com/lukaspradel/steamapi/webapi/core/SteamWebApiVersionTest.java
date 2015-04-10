package com.lukaspradel.steamapi.webapi.core;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import com.lukaspradel.steamapi.BaseTest;

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
						.getCurrentVersionForWebApiInterfaceMethod(SteamWebApiInterfaceMethod.GET_NEWS_FOR_APP),
				SteamWebApiVersion.VERSION_TWO);

		assertEquals(
				SteamWebApiVersion
						.getCurrentVersionForWebApiInterfaceMethod(SteamWebApiInterfaceMethod.GET_OWNED_GAMES),
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
	}
}
