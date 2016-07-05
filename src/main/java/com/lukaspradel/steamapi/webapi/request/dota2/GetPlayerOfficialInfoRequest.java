package com.lukaspradel.steamapi.webapi.request.dota2;

import com.lukaspradel.steamapi.webapi.core.SteamWebApiInterface;
import com.lukaspradel.steamapi.webapi.core.SteamWebApiInterfaceMethod;
import com.lukaspradel.steamapi.webapi.core.SteamWebApiVersion;
import com.lukaspradel.steamapi.webapi.request.SteamWebApiRequest;
import com.lukaspradel.steamapi.webapi.request.builders.AbstractSteamWebApiRequestBuilder;

/**
 * Steam Web API Request Player Official Info
 *
 * @see <a href=
 *      "https://wiki.teamfortress.com/wiki/WebAPI/GetPlayerOfficialInfo"
 *      >https://wiki.teamfortress.com/wiki/WebAPI/GetPlayerOfficialInfo</a>
 * @author Pazus
 *
 */
public class GetPlayerOfficialInfoRequest extends SteamWebApiRequest {
    public GetPlayerOfficialInfoRequest(SteamWebApiRequestBuilder builder) { super(builder);}

    /**
     * Builder object to create instances of {@link SteamWebApiRequest} for
     * request type {@link GetPlayerOfficialInfoRequest}.
     *
     * @author Pazus
     *
     */
    public static class GetPlayerOfficialInfoRequestBuilder extends AbstractSteamWebApiRequestBuilder {

        private final String accountId;

        private final String REQUEST_PARAM_ACCOUNT_ID = "accountid";

        public GetPlayerOfficialInfoRequestBuilder(String accountId) {
            this.accountId = accountId;
        }

        @Override
        public GetPlayerOfficialInfoRequest buildRequest() {

            addParameter(REQUEST_PARAM_ACCOUNT_ID, accountId);
            return new GetPlayerOfficialInfoRequest(this);
        }

        @Override
        protected SteamWebApiInterface getInterface() {
            return SteamWebApiInterface.I_DOTA2_FANTASY;
        }

        @Override
        protected SteamWebApiInterfaceMethod getInterfaceMethod() {
            return SteamWebApiInterfaceMethod.GET_PLAYER_OFFICIAL_INFO;
        }

        @Override
        protected SteamWebApiVersion getVersion() {
            return SteamWebApiVersion.VERSION_ONE;
        }
    }
}
