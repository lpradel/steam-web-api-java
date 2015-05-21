package com.lukaspradel.steamapi.webapi.core;

import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.reflect.Whitebox;
import org.testng.annotations.Test;

import com.lukaspradel.steamapi.BaseTest;

@PrepareForTest(SteamWebApiInterfaceMethod.class)
public class SteamWebApiInterfaceTest extends BaseTest {

	@Mock
	private SteamWebApiInterfaceMethod additionalSteamWebApiInterfaceMethod;

	@Test(expectedExceptions = IllegalArgumentException.class, dependsOnMethods = { "testGetInterfaceForMethodAdditionalUnknownEnumValue" })
	public void testGetInterfaceForMethodError() {

		SteamWebApiInterface.getInterfaceForMethod(null);
	}

	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testGetInterfaceForMethodAdditionalUnknownEnumValue() {

		SteamWebApiInterfaceMethod[] values = SteamWebApiInterfaceMethod
				.values();
		SteamWebApiInterfaceMethod[] valuesAndAdditional = new SteamWebApiInterfaceMethod[values.length + 1];
		System.arraycopy(values, 0, valuesAndAdditional, 0, values.length);

		// create additional, unknown SteamWebApiInterfaceMethod
		PowerMockito.mockStatic(SteamWebApiInterfaceMethod.class);

		Whitebox.setInternalState(additionalSteamWebApiInterfaceMethod, "name",
				"ADDITIONAL_STEAMWEBAPIINTERFACEMETHOD");
		Whitebox.setInternalState(additionalSteamWebApiInterfaceMethod,
				"ordinal", values.length);
		valuesAndAdditional[values.length] = additionalSteamWebApiInterfaceMethod;

		when(SteamWebApiInterfaceMethod.values()).thenReturn(
				valuesAndAdditional);

		SteamWebApiInterface
				.getInterfaceForMethod(additionalSteamWebApiInterfaceMethod);
	}

	@Test(dependsOnMethods = { "testGetInterfaceForMethodAdditionalUnknownEnumValue" })
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
	}

	@Test(dependsOnMethods = { "testGetInterfaceForMethodAdditionalUnknownEnumValue" })
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
