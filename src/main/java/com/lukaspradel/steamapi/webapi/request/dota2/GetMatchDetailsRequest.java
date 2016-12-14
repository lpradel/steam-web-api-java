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
public class GetMatchDetailsRequest extends SteamWebApiRequest{
    public GetMatchDetailsRequest(SteamWebApiRequestBuilder builder) {super(builder);}

    /**
     * Builder object to create instances of {@link SteamWebApiRequest} for
     * request type {@link GetMatchDetailsRequest}.
     *
     * @author Pazus
     *
     */
    public static class GetMatchDetailsRequestBuilder extends AbstractSteamWebApiRequestBuilder {
        private final String matchId;
        public final String REQUEST_PARAM_MATCH_ID = "match_id";

        public GetMatchDetailsRequestBuilder(String matchId) {
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
        public GetMatchDetailsRequest buildRequest() {
            addParameter(REQUEST_PARAM_MATCH_ID,matchId);

            return new GetMatchDetailsRequest(this);
        }
    }


}
