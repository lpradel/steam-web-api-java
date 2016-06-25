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
public class GetMatchHistory extends SteamWebApiRequest{
    public GetMatchHistory(SteamWebApiRequestBuilder builder) {super(builder);}

    /**
     * Game mode filter for request type {@link GetMatchHistory}.
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
     * Game skill filter for request type {@link GetMatchHistory}.
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
     * request type {@link GetMatchDetails}.
     *
     * @author Pazus
     *
     */
    public class GetMatchHistoryBuilder extends AbstractSteamWebApiRequestBuilder {
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

        public GetMatchHistoryBuilder appIdsFilter(List<String> heroIds) {
            this.heroIds.addAll(heroIds);
            return this;
        }

        public GetMatchHistoryBuilder gameMode(GameMode gameMode) {
            this.gameMode = gameMode;
            return this;
        }

        public GetMatchHistoryBuilder skill(Skill skill) {
            this.skill = skill;
            return this;
        }

        public GetMatchHistoryBuilder dateMin(Date dateMin) {
            this.dateMin = dateMin.getTime() / 1000L;
            return this;
        }

        public GetMatchHistoryBuilder dateMax(Date dateMax) {
            this.dateMax = dateMax.getTime() / 1000L;
            return this;
        }

        public GetMatchHistoryBuilder minPlayers(Integer minPlayers) {
            this.minPlayers = minPlayers;
            return this;
        }

        public GetMatchHistoryBuilder accountId(String accountId) {
            this.accountId = accountId;
            return this;
        }

        public GetMatchHistoryBuilder leagueId(String leagueId) {
            this.leagueId = leagueId;
            return this;
        }

        public GetMatchHistoryBuilder startAtMatchId(String startAtMatchId) {
            this.startAtMatchId = startAtMatchId;
            return this;
        }

        public GetMatchHistoryBuilder matchesRequested(Integer matchesRequested) {
            this.matchesRequested = matchesRequested;
            return this;
        }

        public GetMatchHistoryBuilder tournamentGamesOnly(boolean tournamentGamesOnly) {
            if (tournamentGamesOnly) {
                this.tournamentGamesOnly = "1";
            } else
                this.tournamentGamesOnly = "0";
            return this;
        }

        private final String REQUEST_PARAM_HERO_ID = "hero_id";
        private final String REQUEST_PARAM_GAME_MODE = "game_mode";
        private final String REQUEST_PARAM_SKILL = "skill";
        private final String REQUEST_PARAM_DATE_MIN = "date_min";
        private final String REQUEST_PARAM_DATE_MAX = "date_max";
        private final String REQUEST_PARAM_MIN_PLAYERS = "min_players";
        private final String REQUEST_PARAM_ACCOUNT_ID = "account_id";
        private final String REQUEST_PARAM_LEAGUE_ID = "league_id";
        private final String REQUEST_PARAM_START_AT_MATCH_ID = "start_at_match_id";
        private final String REQUEST_PARAM_MATCHES_REQUESTED = "matches_requested";
        private final String REQUEST_PARAM_TOURNAMENT_GAMES_ONLY = "tournament_games_only";

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
        public SteamWebApiRequest buildRequest() {
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

            return new GetMatchHistory(this);
        }
    }
}
