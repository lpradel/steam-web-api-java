package com.lukaspradel.steamapi.webapi.request.tf2;

import com.lukaspradel.steamapi.webapi.core.SteamWebApiInterface;
import com.lukaspradel.steamapi.webapi.core.SteamWebApiInterfaceMethod;
import com.lukaspradel.steamapi.webapi.core.SteamWebApiVersion;
import com.lukaspradel.steamapi.webapi.request.SteamWebApiRequest;
import com.lukaspradel.steamapi.webapi.request.builders.AbstractSteamWebApiRequestBuilder;

/**
 * Steam Web API Request Team Fortress 2 inventory
 *
 * @see <a href=
 *      "https://wiki.teamfortress.com/wiki/WebAPI/GetPlayerItems"
 *      >https://wiki.teamfortress.com/wiki/WebAPI/GetPlayerItems</a>
 * @author Mihajlo Nesic
 *
 */
public class GetPlayerItemsRequest extends SteamWebApiRequest {

    public GetPlayerItemsRequest(SteamWebApiRequestBuilder builder) {
        super(builder);
    }

    public static class GetPlayerItemsRequestBuilder extends AbstractSteamWebApiRequestBuilder {
        private String steamId;

        public static final String REQUEST_PARAM_STEAM_ID = "steamid";

        public GetPlayerItemsRequestBuilder(String steamId) {
            if (steamId == null || steamId.isEmpty()) {
                throw new IllegalArgumentException("You must supply Steam ID!");
            }
            this.steamId = steamId;
        }

        @Override
        protected SteamWebApiVersion getVersion() {
            return SteamWebApiVersion.VERSION_ONE;
        }

        @Override
        protected SteamWebApiInterface getInterface() {
            return SteamWebApiInterface.I_ECON_TF2;
        }

        @Override
        protected SteamWebApiInterfaceMethod getInterfaceMethod() {
            return SteamWebApiInterfaceMethod.GET_PLAYER_ITEMS;
        }

        @Override
        public GetPlayerItemsRequest buildRequest() {
            addParameter(REQUEST_PARAM_STEAM_ID, steamId);
            return new GetPlayerItemsRequest(this);
        }
    }
}
