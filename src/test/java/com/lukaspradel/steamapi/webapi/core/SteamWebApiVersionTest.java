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
public class SteamWebApiVersionTest extends BaseTest {

	@Mock
	private SteamWebApiInterfaceMethod additionalSteamWebApiInterfaceMethod;

	@Test(expectedExceptions = IllegalArgumentException.class, dependsOnMethods = { "testGetCurrentVersionForWebApiInterfaceMethodAdditionalUnknownEnumValue" })
	public void testGetCurrentVersionForWebApiInterfaceMethodError() {

		SteamWebApiVersion.getCurrentVersionForWebApiInterfaceMethod(null);
	}

	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testGetCurrentVersionForWebApiInterfaceMethodAdditionalUnknownEnumValue() {

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

		SteamWebApiVersion
				.getCurrentVersionForWebApiInterfaceMethod(additionalSteamWebApiInterfaceMethod);
	}

	@Test(dependsOnMethods = { "testGetCurrentVersionForWebApiInterfaceMethodAdditionalUnknownEnumValue" })
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
	}

	@Test(dependsOnMethods = { "testGetCurrentVersionForWebApiInterfaceMethodAdditionalUnknownEnumValue" })
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
