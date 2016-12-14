package com.lukaspradel.steamapi.webapi.request.dota2;

import com.lukaspradel.steamapi.webapi.core.SteamWebApiInterface;
import com.lukaspradel.steamapi.webapi.core.SteamWebApiInterfaceMethod;
import com.lukaspradel.steamapi.webapi.core.SteamWebApiVersion;
import com.lukaspradel.steamapi.webapi.request.SteamWebApiRequest;
import com.lukaspradel.steamapi.webapi.request.builders.AbstractSteamWebApiRequestBuilder;

/**
 * Steam Web API Request Dota 2 items
 *
 * @see <a href=
 *      "https://wiki.teamfortress.com/wiki/WebAPI/GetGameItems"
 *      >https://wiki.teamfortress.com/wiki/WebAPI/GetGameItems</a>
 * @author Pazus
 *
 */
public class GetGameItemsRequest extends SteamWebApiRequest {
    public GetGameItemsRequest(SteamWebApiRequestBuilder builder) {super(builder);}

    /**
     * Builder object to create instances of {@link SteamWebApiRequest} for
     * request type {@link GetGameItemsRequest}.
     *
     * @author Pazus
     *
     */
    public static class GetGameItemsRequestBuilder extends AbstractSteamWebApiRequestBuilder {
        private String language;

        public final String REQUEST_PARAM_LANGUAGE = "language";

        @Override
        protected SteamWebApiVersion getVersion() {
            return SteamWebApiVersion.VERSION_ONE;
        }

        @Override
        protected SteamWebApiInterface getInterface() {
            return SteamWebApiInterface.I_ECON_DOTA2;
        }

        @Override
        protected SteamWebApiInterfaceMethod getInterfaceMethod() {
            return SteamWebApiInterfaceMethod.GET_GAME_ITEMS;
        }

        @Override
        public GetGameItemsRequest buildRequest() {
            if (language != null) {
                addParameter(REQUEST_PARAM_LANGUAGE, language);
            }
            return new GetGameItemsRequest(this);
        }
    }
}
