package com.lukaspradel.steamapi.webapi.request.dota2;

import com.lukaspradel.steamapi.webapi.core.SteamWebApiInterface;
import com.lukaspradel.steamapi.webapi.core.SteamWebApiInterfaceMethod;
import com.lukaspradel.steamapi.webapi.core.SteamWebApiVersion;
import com.lukaspradel.steamapi.webapi.request.SteamWebApiRequest;
import com.lukaspradel.steamapi.webapi.request.builders.AbstractSteamWebApiRequestBuilder;

/**
 * Steam Web API Request Pro Players List
 *
 * @see <a href=
 *      "https://wiki.teamfortress.com/wiki/WebAPI/GetProPlayerListRequest"
 *      >https://wiki.teamfortress.com/wiki/WebAPI/GetProPlayerListRequest</a>
 * @author Pazus
 *
 */
public class GetProPlayerListRequest extends SteamWebApiRequest{
    public GetProPlayerListRequest(SteamWebApiRequestBuilder builder) {super(builder);}

    /**
     * Builder object to create instances of {@link SteamWebApiRequest} for
     * request type {@link GetProPlayerListRequest}.
     *
     * @author Pazus
     *
     */
    public static class GetProPlayerListRequestBuilder extends AbstractSteamWebApiRequestBuilder {
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
            return SteamWebApiInterfaceMethod.GET_PRO_PLAYER_LIST;
        }

        @Override
        public GetProPlayerListRequest buildRequest() {
            return new GetProPlayerListRequest(this);
        }
    }
}
