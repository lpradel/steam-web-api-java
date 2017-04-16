package com.lukaspradel.steamapi.webapi.request.dota2;


import com.lukaspradel.steamapi.webapi.core.SteamWebApiInterface;
import com.lukaspradel.steamapi.webapi.core.SteamWebApiInterfaceMethod;
import com.lukaspradel.steamapi.webapi.core.SteamWebApiVersion;
import com.lukaspradel.steamapi.webapi.request.SteamWebApiRequest;
import com.lukaspradel.steamapi.webapi.request.builders.AbstractSteamWebApiRequestBuilder;

/**
 * Steam Web API Request GetHeroes
 *
 * @see <a href=
 *      "https://wiki.teamfortress.com/wiki/WebAPI/GetHeroes"
 *      >https://wiki.teamfortress.com/wiki/WebAPI/GetHeroes</a>
 * @author Pazus
 *
 */
public class GetHeroesRequest extends SteamWebApiRequest {

    private GetHeroesRequest(SteamWebApiRequestBuilder builder) {
        super(builder);
    }

    /**
     * Builder object to create instances of {@link SteamWebApiRequest} for
     * request type {@link GetHeroesRequest}.
     *
     * @author Pazus
     *
     */
    public static class GetHeroesRequestBuilder extends
            AbstractSteamWebApiRequestBuilder {

        /**
         * ISO639-1 language code
         * @see <a href="https://en.wikipedia.org/wiki/List_of_ISO_639-1_codes">https://en.wikipedia.org/wiki/List_of_ISO_639-1_codes</a>
         *
         * @author Pazus
         */
        private String language;
        private Boolean itemizedonly;

        public static final String REQUEST_PARAM_LANGUAGE = "language";

        public static final String REQUEST_PARAM_ITEMIZEDONLY = "itemizedonly";

        public GetHeroesRequestBuilder language(String language) {

            this.language = language;
            return this;
        }

        public GetHeroesRequestBuilder itemizedonly(Boolean itemizedonly) {
            this.itemizedonly = itemizedonly;
            return this;
        }

        @Override
        protected SteamWebApiInterface getInterface() {

            return SteamWebApiInterface.I_ECON_DOTA2;
        }

        @Override
        protected SteamWebApiInterfaceMethod getInterfaceMethod() {

            return SteamWebApiInterfaceMethod.GET_HEROES;
        }

        @Override
        protected SteamWebApiVersion getVersion() {

            return SteamWebApiVersion.VERSION_ONE;
        }

        @Override
        public GetHeroesRequest buildRequest() {

            if (language != null) {
                addParameter(REQUEST_PARAM_LANGUAGE,language);
            }

            if (itemizedonly != null) {
                addParameter(REQUEST_PARAM_ITEMIZEDONLY,itemizedonly.toString());
            }

            return new GetHeroesRequest(this);
        }
    }
}
