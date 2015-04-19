package com.lukaspradel.steamapi.webapi.core;

import static org.testng.Assert.assertEquals;

import org.powermock.api.mockito.PowerMockito;
import org.powermock.reflect.Whitebox;
import org.testng.annotations.Test;

import com.lukaspradel.steamapi.BaseTest;

public class SteamWebApiInterfaceTest extends BaseTest {

	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testGetInterfaceMethodError() {

		SteamWebApiInterface.getInterfaceForMethod(null);
	}

	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testGetInterfaceMethodAdditionalUnknownEnumValue() {

		SteamWebApiInterfaceMethod[] values = SteamWebApiInterfaceMethod
				.values();
		SteamWebApiInterfaceMethod[] valuesAndAdditional = new SteamWebApiInterfaceMethod[values.length + 1];
		System.arraycopy(values, 0, valuesAndAdditional, 0, values.length);

		// create additional, unknown SteamWebApiInterfaceMethod
		SteamWebApiInterfaceMethod additionalSteamWebApiInterfaceMethod = PowerMockito
				.mock(SteamWebApiInterfaceMethod.class);
		Whitebox.setInternalState(additionalSteamWebApiInterfaceMethod, "name",
				"ADDITIONAL_STEAMWEBAPIINTERFACEMETHOD");
		Whitebox.setInternalState(additionalSteamWebApiInterfaceMethod,
				"ordinal", values.length);
		valuesAndAdditional[values.length] = additionalSteamWebApiInterfaceMethod;

		SteamWebApiInterface
				.getInterfaceForMethod(additionalSteamWebApiInterfaceMethod);
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
						.getInterfaceForMethod(SteamWebApiInterfaceMethod.GET_PLAYER_ACHIEVEMENTS),
				SteamWebApiInterface.I_STEAM_USER_STATS);

		assertEquals(
				SteamWebApiInterface
						.getInterfaceForMethod(SteamWebApiInterfaceMethod.IS_PLAYING_SHARED_GAME),
				SteamWebApiInterface.I_PLAYER_SERVICE);
	}
}
