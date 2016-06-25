package com.lukaspradel.steamapi.webapi.request.dota2;

import com.lukaspradel.steamapi.webapi.core.SteamWebApiInterface;
import com.lukaspradel.steamapi.webapi.core.SteamWebApiInterfaceMethod;
import com.lukaspradel.steamapi.webapi.core.SteamWebApiVersion;
import com.lukaspradel.steamapi.webapi.request.SteamWebApiRequest;
import com.lukaspradel.steamapi.webapi.request.builders.AbstractSteamWebApiRequestBuilder;

/**
 * Steam Web API Request Live League Games
 *
 * @see <a href=
 *      "https://wiki.teamfortress.com/wiki/WebAPI/GetLeagueListing"
 *      >https://wiki.teamfortress.com/wiki/WebAPI/GetLeagueListing</a>
 * @author Pazus
 *
 */
public class GetLiveLeagueGamesRequest extends SteamWebApiRequest{
    public GetLiveLeagueGamesRequest(SteamWebApiRequestBuilder builder) {super(builder);}

    /**
     * Builder object to create instances of {@link SteamWebApiRequest} for
     * request type {@link GetLiveLeagueGamesRequest}.
     *
     * @author Pazus
     *
     */
    public static class GetLiveLeagueGamesRequestBuilder extends AbstractSteamWebApiRequestBuilder {
        @Override
        protected SteamWebApiVersion getVersion() {
            return SteamWebApiVersion.VERSION_ONE;
        }

        @Override
        protected SteamWebApiInterface getInterface() {
            return SteamWebApiInterface.I_DOTA2_MATCH;
        }

        @Override
        protected SteamWebApiInterfaceMethod getInterfaceMethod() {
            return SteamWebApiInterfaceMethod.GET_LIVE_LEAGUE_GAMES;
        }

        @Override
        public GetLiveLeagueGamesRequest buildRequest() {
            return new GetLiveLeagueGamesRequest(this);
        }
    }
}
