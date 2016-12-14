package com.lukaspradel.steamapi.webapi.request.dota2;

import com.lukaspradel.steamapi.webapi.core.SteamWebApiInterface;
import com.lukaspradel.steamapi.webapi.core.SteamWebApiInterfaceMethod;
import com.lukaspradel.steamapi.webapi.core.SteamWebApiVersion;
import com.lukaspradel.steamapi.webapi.request.SteamWebApiRequest;
import com.lukaspradel.steamapi.webapi.request.builders.AbstractSteamWebApiRequestBuilder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Steam Web API Request Matches History
 *
 * @see <a href=
 *      "https://wiki.teamfortress.com/wiki/WebAPI/GetMatchHistory"
 *      >https://wiki.teamfortress.com/wiki/WebAPI/GetMatchHistory</a>
 * @author Pazus
 *
 */
public class GetMatchHistoryRequest extends SteamWebApiRequest{
    public GetMatchHistoryRequest(SteamWebApiRequestBuilder builder) {super(builder);}

    /**
     * Game mode filter for request type {@link GetMatchHistoryRequest}.
     *
     * @author Pazus
     *
     */
    public enum GameMode {


        NONE(0),ALL_PICK(1),CAPITAINS_MODE(2),RANDOM_DRAFT(3),SINGLE_DRAFT(4),
        ALL_RANDOM(5),INTRO(6),DIRETIDE(7),REVERSE_CAPRAINS_MODE(8),
        THE_GREEVILING(9),TUTORIAL(10),MID_ONLY(11),LEAST_PLAYED(12),
        NEW_PLAYER_POOL(13),COMPEDIUM_MATCHMAKING(14),CAPTAINS_DRAFT(16);

        private final int gameMode;

        private GameMode(int gameMode) {
            this.gameMode = gameMode;
        }


        @Override
        public String toString() {
            return String.valueOf(gameMode);
        }
    }


    /**
     * Game skill filter for request type {@link GetMatchHistoryRequest}.
     *
     * @author Pazus
     *
     */
    public enum Skill {
        ANY(0), NORMAL(1), HIGH(2), VERY_HIGH(3);

        private final int skill;
        private Skill(int skill) {
            this.skill = skill;
        }

        @Override
        public String toString() {
            return String.valueOf(skill);
        }
    }

    /**
     * Builder object to create instances of {@link SteamWebApiRequest} for
     * request type {@link GetMatchDetailsRequest}.
     *
     * @author Pazus
     *
     */
    public static class GetMatchHistoryRequestBuilder extends AbstractSteamWebApiRequestBuilder {
        private List<String> heroIds =  new ArrayList<>();
        private GameMode gameMode = null;
        private Skill skill = null;
        private Long dateMin;
        private Long dateMax;
        private Integer minPlayers;
        private String accountId;
        private String leagueId;
        private String startAtMatchId;
        private Integer matchesRequested;
        private String tournamentGamesOnly;

        public GetMatchHistoryRequestBuilder heroIdsFilter(List<String> heroIds) {
            this.heroIds.addAll(heroIds);
            return this;
        }

        public GetMatchHistoryRequestBuilder gameMode(GameMode gameMode) {
            this.gameMode = gameMode;
            return this;
        }

        public GetMatchHistoryRequestBuilder skill(Skill skill) {
            this.skill = skill;
            return this;
        }

        public GetMatchHistoryRequestBuilder dateMin(Date dateMin) {
            this.dateMin = dateMin.getTime() / 1000L;
            return this;
        }

        public GetMatchHistoryRequestBuilder dateMax(Date dateMax) {
            this.dateMax = dateMax.getTime() / 1000L;
            return this;
        }

        public GetMatchHistoryRequestBuilder minPlayers(Integer minPlayers) {
            this.minPlayers = minPlayers;
            return this;
        }

        public GetMatchHistoryRequestBuilder accountId(String accountId) {
            this.accountId = accountId;
            return this;
        }

        public GetMatchHistoryRequestBuilder leagueId(String leagueId) {
            this.leagueId = leagueId;
            return this;
        }

        public GetMatchHistoryRequestBuilder startAtMatchId(String startAtMatchId) {
            this.startAtMatchId = startAtMatchId;
            return this;
        }

        public GetMatchHistoryRequestBuilder matchesRequested(Integer matchesRequested) {
            this.matchesRequested = matchesRequested;
            return this;
        }

        public GetMatchHistoryRequestBuilder tournamentGamesOnly(boolean tournamentGamesOnly) {
            if (tournamentGamesOnly) {
                this.tournamentGamesOnly = "1";
            } else
                this.tournamentGamesOnly = "0";
            return this;
        }

        public static final String REQUEST_PARAM_HERO_ID = "hero_id";
        public static final String REQUEST_PARAM_GAME_MODE = "game_mode";
        public static final String REQUEST_PARAM_SKILL = "skill";
        public static final String REQUEST_PARAM_DATE_MIN = "date_min";
        public static final String REQUEST_PARAM_DATE_MAX = "date_max";
        public static final String REQUEST_PARAM_MIN_PLAYERS = "min_players";
        public static final String REQUEST_PARAM_ACCOUNT_ID = "account_id";
        public static final String REQUEST_PARAM_LEAGUE_ID = "league_id";
        public static final String REQUEST_PARAM_START_AT_MATCH_ID = "start_at_match_id";
        public static final String REQUEST_PARAM_MATCHES_REQUESTED = "matches_requested";
        public static final String REQUEST_PARAM_TOURNAMENT_GAMES_ONLY = "tournament_games_only";

        @Override
        protected SteamWebApiInterface getInterface() {
            return SteamWebApiInterface.I_DOTA2_MATCH;
        }

        @Override
        protected SteamWebApiInterfaceMethod getInterfaceMethod() {
            return SteamWebApiInterfaceMethod.GET_MATCH_HISTORY;
        }

        @Override
        protected SteamWebApiVersion getVersion() {
            return SteamWebApiVersion.VERSION_ONE;
        }

        @Override
        public GetMatchHistoryRequest buildRequest() {
            if (!heroIds.isEmpty()) {
                addListParameter(REQUEST_PARAM_HERO_ID,heroIds);
            }

            if (gameMode != null) {
                addParameter(REQUEST_PARAM_GAME_MODE,gameMode.toString());
            }

            if(skill != null) {
                addParameter(REQUEST_PARAM_SKILL,skill.toString());
            }

            if (dateMin != null) {
                addParameter(REQUEST_PARAM_DATE_MIN,dateMin);
            }

            if (dateMax != null) {
                addParameter(REQUEST_PARAM_DATE_MAX,dateMax);
            }

            if (minPlayers != null) {
                addParameter(REQUEST_PARAM_MIN_PLAYERS,minPlayers);
            }

            if (accountId != null) {
                addParameter(REQUEST_PARAM_ACCOUNT_ID,accountId);
            }

            if (leagueId != null) {
                addParameter(REQUEST_PARAM_LEAGUE_ID,leagueId);
            }

            if (startAtMatchId != null) {
                addParameter(REQUEST_PARAM_START_AT_MATCH_ID,startAtMatchId);
            }

            if (matchesRequested != null) {
                addParameter(REQUEST_PARAM_MATCHES_REQUESTED,matchesRequested);
            }

            if (tournamentGamesOnly != null) {
                addParameter(REQUEST_PARAM_TOURNAMENT_GAMES_ONLY,tournamentGamesOnly);
            }

            return new GetMatchHistoryRequest(this);
        }
    }
}
