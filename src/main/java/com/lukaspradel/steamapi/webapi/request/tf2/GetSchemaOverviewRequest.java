package com.lukaspradel.steamapi.webapi.request.tf2;

import com.lukaspradel.steamapi.webapi.core.SteamWebApiInterface;
import com.lukaspradel.steamapi.webapi.core.SteamWebApiInterfaceMethod;
import com.lukaspradel.steamapi.webapi.core.SteamWebApiVersion;
import com.lukaspradel.steamapi.webapi.request.SteamWebApiRequest;
import com.lukaspradel.steamapi.webapi.request.builders.AbstractSteamWebApiRequestBuilder;

/**
 * Steam Web API Request Team Fortress 2 item schema overview
 *
 * @see <a href=
 *      "https://wiki.teamfortress.com/wiki/WebAPI/GetSchemaOverview"
 *      >https://wiki.teamfortress.com/wiki/WebAPI/GetSchemaOverview</a>
 * @author Mihajlo Nesic
 *
 */
public class GetSchemaOverviewRequest extends SteamWebApiRequest {

    public GetSchemaOverviewRequest(SteamWebApiRequestBuilder builder) {
        super(builder);
    }

    public static class GetSchemaOverviewRequestBuilder extends AbstractSteamWebApiRequestBuilder {
        private String language;

        public static final String REQUEST_PARAM_LANGUAGE = "language";

        public GetSchemaOverviewRequestBuilder language(String language) {
            this.language = language;
            return this;
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
            return SteamWebApiInterfaceMethod.GET_SCHEMA_OVERVIEW;
        }

        @Override
        public GetSchemaOverviewRequest buildRequest() {
            if (language != null) {
                addParameter(REQUEST_PARAM_LANGUAGE, language);
            }
            return new GetSchemaOverviewRequest(this);
        }
    }
}
