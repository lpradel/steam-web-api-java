package com.lukaspradel.steamapi.webapi.core;

import static org.testng.Assert.assertEquals;

import org.powermock.api.mockito.PowerMockito;
import org.powermock.reflect.Whitebox;
import org.testng.annotations.Test;

import com.lukaspradel.steamapi.BaseTest;

public class SteamWebApiVersionTest extends BaseTest {

	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testGetCurrentVersionForWebApiInterfaceMethodError() {

		SteamWebApiVersion.getCurrentVersionForWebApiInterfaceMethod(null);
	}

	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testGetCurrentVersionForWebApiInterfaceMethodAdditionalUnknownEnum() {

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

		SteamWebApiVersion
				.getCurrentVersionForWebApiInterfaceMethod(additionalSteamWebApiInterfaceMethod);
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
	}
}
