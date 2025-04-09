package com.lukaspradel.steamapi.webapi.request.tf2;

import com.lukaspradel.steamapi.webapi.core.SteamWebApiInterface;
import com.lukaspradel.steamapi.webapi.core.SteamWebApiInterfaceMethod;
import com.lukaspradel.steamapi.webapi.core.SteamWebApiVersion;
import com.lukaspradel.steamapi.webapi.request.SteamWebApiRequest;
import com.lukaspradel.steamapi.webapi.request.builders.AbstractSteamWebApiRequestBuilder;

/**
 * Steam Web API Request Team Fortress 2 item schema
 *
 * @see <a href=
 *      "https://wiki.teamfortress.com/wiki/WebAPI/GetSchemaItems"
 *      >https://wiki.teamfortress.com/wiki/WebAPI/GetSchemaItems</a>
 * @author Mihajlo Nesic
 *
 */
public class GetSchemaItemsRequest extends SteamWebApiRequest {

    public GetSchemaItemsRequest(SteamWebApiRequestBuilder builder) {
        super(builder);
    }

    public static class GetSchemaItemsRequestBuilder extends AbstractSteamWebApiRequestBuilder {
        private String language;
        private Integer start;

        public static final String REQUEST_PARAM_LANGUAGE = "language";
        public static final String REQUEST_PARAM_START = "start";

        public GetSchemaItemsRequestBuilder language(String language) {
            this.language = language;
            return this;
        }

        public GetSchemaItemsRequestBuilder start(Integer start) {
            this.start = start;
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
            return SteamWebApiInterfaceMethod.GET_SCHEMA_ITEMS;
        }

        @Override
        public GetSchemaItemsRequest buildRequest() {
            if (language != null) {
                addParameter(REQUEST_PARAM_LANGUAGE, language);
            }
            if (start != null) {
                addParameter(REQUEST_PARAM_START, start);
            }
            return new GetSchemaItemsRequest(this);
        }
    }
}
