package com.lukaspradel.steamapi.webapi.request;

import com.lukaspradel.steamapi.webapi.core.SteamWebApiInterface;
import com.lukaspradel.steamapi.webapi.core.SteamWebApiInterfaceMethod;
import com.lukaspradel.steamapi.webapi.core.SteamWebApiVersion;
import com.lukaspradel.steamapi.webapi.request.builders.AbstractSteamWebApiServiceRequestBuilder;

/**
 * Steam Web API Request GetAppList
 *
 * @see <a href=
 *      "https://partner.steamgames.com/doc/webapi/ISteamApps#GetAppList"
 *      >https://partner.steamgames.com/doc/webapi/ISteamApps#GetAppList</a>
 * @author jbleau
 *
 */
public class GetAppListRequest extends SteamWebApiRequest {
    protected GetAppListRequest(SteamWebApiRequestBuilder builder) {
        super(builder);
    }

    public static class GetAppListRequestBuilder extends
            AbstractSteamWebApiServiceRequestBuilder {

        @Override
        protected SteamWebApiInterface getInterface() {
            return SteamWebApiInterface.I_STEAM_APPS;
        }

        @Override
        protected SteamWebApiInterfaceMethod getInterfaceMethod() {
            return SteamWebApiInterfaceMethod.GET_APP_LIST;
        }

        @Override
        protected SteamWebApiVersion getVersion() {
            return SteamWebApiVersion.VERSION_TWO;
        }

        @Override
        public GetAppListRequest buildRequest() {
            return new GetAppListRequest(this);
        }
    }
}
