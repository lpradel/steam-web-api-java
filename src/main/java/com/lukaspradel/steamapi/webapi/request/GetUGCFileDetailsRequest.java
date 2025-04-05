package com.lukaspradel.steamapi.webapi.request;

import com.lukaspradel.steamapi.webapi.core.SteamWebApiInterface;
import com.lukaspradel.steamapi.webapi.core.SteamWebApiInterfaceMethod;
import com.lukaspradel.steamapi.webapi.core.SteamWebApiVersion;
import com.lukaspradel.steamapi.webapi.request.builders.AbstractSteamWebApiRequestBuilder;

/**
 * Steam Web API Request GetUGCFileDetails
 *
 * @see <a href=
 *      "https://wiki.teamfortress.com/wiki/WebAPI/GetUGCFileDetails"
 *      >https://wiki.teamfortress.com/wiki/WebAPI/GetUGCFileDetails</a>
 * @author Mihajlo Nesic
 *
 */
public class GetUGCFileDetailsRequest extends SteamWebApiRequest {

    public GetUGCFileDetailsRequest(SteamWebApiRequestBuilder builder) {
        super(builder);
    }

    public static class GetUGCFileDetailsRequestBuilder extends AbstractSteamWebApiRequestBuilder {

        private final Long ugcId;
        private final Integer appId;
        private String steamId;

        public static final String REQUEST_PARAM_UGC_ID = "ugcid";
        public static final String REQUEST_PARAM_APP_ID = "appid";
        public static final String REQUEST_PARAM_STEAM_ID = "steamid";

        public GetUGCFileDetailsRequestBuilder(Long ugcId, Integer appId) {
            this.ugcId = ugcId;
            this.appId = appId;
        }

        public GetUGCFileDetailsRequestBuilder steamId(String steamId) {
            this.steamId = steamId;
            return this;
        }

        @Override
        protected SteamWebApiVersion getVersion() {
            return SteamWebApiVersion.VERSION_ONE;
        }

        @Override
        protected SteamWebApiInterface getInterface() {
            return SteamWebApiInterface.I_STEAM_REMOTE_STORAGE;
        }

        @Override
        protected SteamWebApiInterfaceMethod getInterfaceMethod() {
            return SteamWebApiInterfaceMethod.GET_UGC_FILE_DETAILS;
        }

        @Override
        public GetUGCFileDetailsRequest buildRequest() {

            addParameter(REQUEST_PARAM_UGC_ID, ugcId);
            addParameter(REQUEST_PARAM_APP_ID, appId);

            if (steamId != null) {
                addParameter(REQUEST_PARAM_STEAM_ID, steamId);
            }

            return new GetUGCFileDetailsRequest(this);
        }
    }
}
