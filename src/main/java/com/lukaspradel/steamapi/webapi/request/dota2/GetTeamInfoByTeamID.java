package com.lukaspradel.steamapi.webapi.request.dota2;

import com.lukaspradel.steamapi.webapi.core.SteamWebApiInterface;
import com.lukaspradel.steamapi.webapi.core.SteamWebApiInterfaceMethod;
import com.lukaspradel.steamapi.webapi.core.SteamWebApiVersion;
import com.lukaspradel.steamapi.webapi.request.SteamWebApiRequest;
import com.lukaspradel.steamapi.webapi.request.builders.AbstractSteamWebApiRequestBuilder;

/**
 * Steam Web API Request Team info by team ID
 *
 * @see <a href=
 *      "https://wiki.teamfortress.com/wiki/WebAPI/GetTeamInfoByTeamID"
 *      >https://wiki.teamfortress.com/wiki/WebAPI/GetTeamInfoByTeamID</a>
 * @author Pazus
 *
 */
public class GetTeamInfoByTeamID extends SteamWebApiRequest{
    public GetTeamInfoByTeamID(SteamWebApiRequestBuilder builder) {super(builder);}

    /**
     * Builder object to create instances of {@link SteamWebApiRequest} for
     * request type {@link GetTeamInfoByTeamID}.
     *
     * @author Pazus
     *
     */
    public class GetTeamInfoByTeamIDBuilder extends AbstractSteamWebApiRequestBuilder {

        private Long startAtTeamId;

        private Integer teamsRequested;

        private final String REQUEST_PARAM_START_AT_TEAM_ID = "start_at_team_id ";

        private final String REQUEST_PARAM_TEAMS_REQUESTED = "teams_requested";

        public GetTeamInfoByTeamIDBuilder startAtTeamId(Long startAtTeamId) {
            this.startAtTeamId = startAtTeamId;
            return this;
        }

        public GetTeamInfoByTeamIDBuilder teamsRequested(Integer teamsRequested) {
            this.teamsRequested = teamsRequested;
            return this;
        }

        @Override
        protected SteamWebApiInterface getInterface() {
            return SteamWebApiInterface.I_DOTA2_MATCH;
        }

        @Override
        protected SteamWebApiInterfaceMethod getInterfaceMethod() {
            return SteamWebApiInterfaceMethod.GET_TEAM_INFO_BY_TEAM_ID;
        }

        @Override
        protected SteamWebApiVersion getVersion() {
            return SteamWebApiVersion.VERSION_ONE;
        }

        @Override
        public SteamWebApiRequest buildRequest() {

            if (startAtTeamId != null) {
                addParameter(REQUEST_PARAM_START_AT_TEAM_ID,startAtTeamId);
            }

            if (teamsRequested != null) {
                addParameter(REQUEST_PARAM_TEAMS_REQUESTED,teamsRequested);
            }

            return new GetTeamInfoByTeamID(this);
        }
    }
}
