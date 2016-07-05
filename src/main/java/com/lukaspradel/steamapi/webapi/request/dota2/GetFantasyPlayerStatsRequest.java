package com.lukaspradel.steamapi.webapi.request.dota2;

import com.lukaspradel.steamapi.webapi.core.SteamWebApiInterface;
import com.lukaspradel.steamapi.webapi.core.SteamWebApiInterfaceMethod;
import com.lukaspradel.steamapi.webapi.core.SteamWebApiVersion;
import com.lukaspradel.steamapi.webapi.request.SteamWebApiRequest;
import com.lukaspradel.steamapi.webapi.request.builders.AbstractSteamWebApiRequestBuilder;

import java.util.Date;

/**
 * Steam Web API Request Fantasy Player Stats
 *
 * @see <a href=
 *      "https://wiki.teamfortress.com/wiki/WebAPI/GetFantasyPlayerStats"
 *      >https://wiki.teamfortress.com/wiki/WebAPI/GetFantasyPlayerStats</a>
 * @author Pazus
 *
 */
public class GetFantasyPlayerStatsRequest extends SteamWebApiRequest {
    public GetFantasyPlayerStatsRequest(SteamWebApiRequestBuilder builder) {super(builder);}

    /**
     * Builder object to create instances of {@link SteamWebApiRequest} for
     * request type {@link GetFantasyPlayerStatsRequest}.
     *
     * @author Pazus
     *
     */
    public static class GetFantasyPlayerStatsRequestBuilder extends AbstractSteamWebApiRequestBuilder {
        private final String fantasyLeagueID;
        private Long startTime;
        private Long endTime;
        private String matchId;
        private Integer seriesID;
        private String playerAccountID;

        private final String REQUEST_PARAM_FANTASY_LEAGUE_ID = "FantasyLeagueID";
        private final String REQUEST_PARAM_START_TIME = "StartTime";
        private final String REQUEST_PARAM_END_TIME = "EndTime";
        private final String REQUEST_PARAM_MATCH_ID = "matchid";
        private final String REQUEST_PARAM_SERIES_ID = "SeriesID";
        private final String REQUEST_PARAM_PLAYER_ACCOUNT_ID = "PlayerAccountID";


        public GetFantasyPlayerStatsRequestBuilder(String fantasyLeagueID) {
            this.fantasyLeagueID = fantasyLeagueID;
        }

        public GetFantasyPlayerStatsRequestBuilder startTime(Date startTime) {
            this.startTime = startTime.getTime() / 1000L;
            return this;
        }

        public GetFantasyPlayerStatsRequestBuilder endTime(Date endTime) {
            this.endTime = endTime.getTime() / 1000L;
            return this;
        }

        public GetFantasyPlayerStatsRequestBuilder matchId(String matchId) {
            this.matchId = matchId;
            return this;
        }

        public GetFantasyPlayerStatsRequestBuilder seriesID(Integer seriesID) {
            this.seriesID = seriesID;
            return this;
        }

        public GetFantasyPlayerStatsRequestBuilder playerAccountID(String playerAccountID) {
            this.playerAccountID = playerAccountID;
            return this;
        }

        @Override
        protected SteamWebApiVersion getVersion() {
            return SteamWebApiVersion.VERSION_ONE;
        }

        @Override
        protected SteamWebApiInterface getInterface() {
            return SteamWebApiInterface.I_DOTA2_FANTASY;
        }

        @Override
        protected SteamWebApiInterfaceMethod getInterfaceMethod() {
            return SteamWebApiInterfaceMethod.GET_FANTASY_PLAYER_STATS;
        }

        @Override
        public GetFantasyPlayerStatsRequest buildRequest() {
            addParameter(REQUEST_PARAM_FANTASY_LEAGUE_ID, fantasyLeagueID);

            if (startTime != null) {
                addParameter(REQUEST_PARAM_START_TIME,startTime);
            }

            if (endTime != null) {
                addParameter(REQUEST_PARAM_END_TIME, endTime);
            }

            if (matchId != null) {
                addParameter(REQUEST_PARAM_MATCH_ID, matchId);
            }

            if (seriesID != null) {
                addParameter(REQUEST_PARAM_SERIES_ID, seriesID);
            }

            if (playerAccountID != null) {
                addParameter(REQUEST_PARAM_PLAYER_ACCOUNT_ID, playerAccountID);
            }

            return new GetFantasyPlayerStatsRequest(this);
        }
    }
}
