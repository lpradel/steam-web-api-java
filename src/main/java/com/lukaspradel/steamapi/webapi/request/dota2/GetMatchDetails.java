package com.lukaspradel.steamapi.webapi.request.dota2;

import com.lukaspradel.steamapi.webapi.core.SteamWebApiInterface;
import com.lukaspradel.steamapi.webapi.core.SteamWebApiInterfaceMethod;
import com.lukaspradel.steamapi.webapi.core.SteamWebApiVersion;
import com.lukaspradel.steamapi.webapi.request.SteamWebApiRequest;
import com.lukaspradel.steamapi.webapi.request.builders.AbstractSteamWebApiRequestBuilder;

/**
 * Steam Web API Request Match Details
 *
 * @see <a href=
 *      "https://wiki.teamfortress.com/wiki/WebAPI/GetMatchDetails"
 *      >https://wiki.teamfortress.com/wiki/WebAPI/GetMatchDetails</a>
 * @author Pazus
 *
 */
public class GetMatchDetails extends SteamWebApiRequest{
    public GetMatchDetails (SteamWebApiRequestBuilder builder) {super(builder);}

    /**
     * Builder object to create instances of {@link SteamWebApiRequest} for
     * request type {@link GetMatchDetails}.
     *
     * @author Pazus
     *
     */
    public class GetMatchDetailsBuilder extends AbstractSteamWebApiRequestBuilder {
        private final String matchId;
        private final String REQUEST_PARAM_MATCH_ID = "match_id";

        public GetMatchDetailsBuilder(String matchId) {
            this.matchId = matchId;
        }

        @Override
        protected SteamWebApiInterface getInterface() {
            return SteamWebApiInterface.I_DOTA2_MATCH;
        }

        @Override
        protected SteamWebApiInterfaceMethod getInterfaceMethod() {
            return SteamWebApiInterfaceMethod.GET_MATCH_DETAILS;
        }

        @Override
        protected SteamWebApiVersion getVersion() {
            return SteamWebApiVersion.VERSION_ONE;
        }

        @Override
        public SteamWebApiRequest buildRequest() {
            addParameter(REQUEST_PARAM_MATCH_ID,matchId);

            return new GetMatchDetails(this);
        }
    }


}
