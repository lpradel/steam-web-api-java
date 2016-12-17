package com.lukaspradel.steamapi.webapi.request.builders;

import com.lukaspradel.steamapi.webapi.core.SteamWebApiInterface;
import com.lukaspradel.steamapi.webapi.core.SteamWebApiInterfaceMethod;
import com.lukaspradel.steamapi.webapi.core.SteamWebApiVersion;
import com.lukaspradel.steamapi.webapi.request.GetNewsForAppRequest;
import com.lukaspradel.steamapi.webapi.request.dota2.GetHeroesRequest;
import com.lukaspradel.steamapi.webapi.request.dota2.GetHeroesRequest.GetHeroesRequestBuilder;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

import com.lukaspradel.steamapi.webapi.request.dota2.GetMatchHistoryRequest;
import com.lukaspradel.steamapi.webapi.request.dota2.GetMatchHistoryRequest.GetMatchHistoryRequestBuilder;


import java.util.*;


/**
 * Created by Pavel Kaplya on 17.12.2016.
 */
public class SteamWebApiRequestBuilderTest {
    @Test
    public void testGetMatchHistoryRequestBuilder() {
        final GregorianCalendar startDate = new GregorianCalendar(2016,12,01);
        final GregorianCalendar endDate = new GregorianCalendar(2016,12,31);
        final List<String> heroIdsFilter = Arrays.asList("1","2");

        GetMatchHistoryRequest request = new GetMatchHistoryRequestBuilder()
                .accountId("123")
                .dateMin(startDate.getTime())
                .dateMax(endDate.getTime())
                .gameMode(GetMatchHistoryRequest.GameMode.ALL_PICK)
                .heroIdsFilter(heroIdsFilter)
                .leagueId("5")
                .matchesRequested(100)
                .minPlayers(10)
                .skill(GetMatchHistoryRequest.Skill.HIGH)
                .tournamentGamesOnly(true)
                .buildRequest();

        assertNotNull(request);

        assertEquals(request.getApiInterface(), SteamWebApiInterface.I_DOTA2_MATCH);
        assertEquals(request.getInterfaceMethod(), SteamWebApiInterfaceMethod.GET_MATCH_HISTORY);
        assertEquals(request.getVersion(), SteamWebApiVersion.VERSION_ONE);

        Map<String, String> parameters = request.getParameters();

        assertEquals(parameters.get(GetMatchHistoryRequestBuilder.REQUEST_PARAM_ACCOUNT_ID),"123");
        assertEquals(parameters.get(GetMatchHistoryRequestBuilder.REQUEST_PARAM_DATE_MIN),String.valueOf(startDate.getTimeInMillis()/1000L));
        assertEquals(parameters.get(GetMatchHistoryRequestBuilder.REQUEST_PARAM_DATE_MAX),String.valueOf(endDate.getTimeInMillis()/1000L));
        assertEquals(parameters.get(GetMatchHistoryRequestBuilder.REQUEST_PARAM_GAME_MODE),"1");
        assertEquals(parameters.get(GetMatchHistoryRequestBuilder.REQUEST_PARAM_HERO_ID),"1,2");
        assertEquals(parameters.get(GetMatchHistoryRequestBuilder.REQUEST_PARAM_LEAGUE_ID),"5");
        assertEquals(parameters.get(GetMatchHistoryRequestBuilder.REQUEST_PARAM_MATCHES_REQUESTED),"100");
        assertEquals(parameters.get(GetMatchHistoryRequestBuilder.REQUEST_PARAM_MIN_PLAYERS),"10");
        assertEquals(parameters.get(GetMatchHistoryRequestBuilder.REQUEST_PARAM_SKILL),"2");
        assertEquals(parameters.get(GetMatchHistoryRequestBuilder.REQUEST_PARAM_TOURNAMENT_GAMES_ONLY),"1");

    }

    @Test
    public void testGetHeroesBuilder() {
        GetHeroesRequest request = new GetHeroesRequestBuilder().language("en").itemizedonly(true).buildRequest();

        assertNotNull(request);

        assertEquals(request.getApiInterface(), SteamWebApiInterface.I_ECON_DOTA2);
        assertEquals(request.getInterfaceMethod(), SteamWebApiInterfaceMethod.GET_HEROES);
        assertEquals(request.getVersion(), SteamWebApiVersion.VERSION_ONE);

        Map<String, String> parameters = request.getParameters();
        assertEquals(parameters.size(),3);
        assertEquals(parameters.get(GetHeroesRequestBuilder.REQUEST_PARAM_LANGUAGE),"en");
        assertEquals(parameters.get(GetHeroesRequestBuilder.REQUEST_PARAM_ITEMIZEDONLY),"true");
    }
}
