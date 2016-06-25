package com.lukaspradel.steamapi.webapi.request.dota2;

import com.lukaspradel.steamapi.webapi.core.SteamWebApiInterface;
import com.lukaspradel.steamapi.webapi.core.SteamWebApiInterfaceMethod;
import com.lukaspradel.steamapi.webapi.core.SteamWebApiVersion;
import com.lukaspradel.steamapi.webapi.request.SteamWebApiRequest;
import com.lukaspradel.steamapi.webapi.request.builders.AbstractSteamWebApiRequestBuilder;

/**
 * Steam Web API Request League Listing
 *
 * @see <a href=
 *      "https://wiki.teamfortress.com/wiki/WebAPI/GetLeagueListing"
 *      >https://wiki.teamfortress.com/wiki/WebAPI/GetLeagueListing</a>
 * @author Pazus
 *
 */
public class GetLeagueListing extends SteamWebApiRequest{
    public GetLeagueListing (SteamWebApiRequestBuilder builder) {super(builder);}

    /**
     * Builder object to create instances of {@link SteamWebApiRequest} for
     * request type {@link GetLeagueListing}.
     *
     * @author Pazus
     *
     */
    public class GetLeagueListingBuilder extends AbstractSteamWebApiRequestBuilder {

        /**
         * ISO639-1 language code
         * @see <a href="https://en.wikipedia.org/wiki/List_of_ISO_639-1_codes">https://en.wikipedia.org/wiki/List_of_ISO_639-1_codes</a>
         *
         * @author Pazus
         */
        private String language;
        private final String REQUEST_PARAM_LANGUAGE = "language";

        public GetLeagueListingBuilder language(String language) {
            this.language = language;
            return this;
        }

        @Override
        protected SteamWebApiVersion getVersion() {
            return SteamWebApiVersion.VERSION_ONE;
        }

        @Override
        protected SteamWebApiInterface getInterface() {
            return SteamWebApiInterface.I_DOTA2_MATCH;
        }

        @Override
        protected SteamWebApiInterfaceMethod getInterfaceMethod() {
            return SteamWebApiInterfaceMethod.GET_LEAGUE_LISTING;
        }

        @Override
        public SteamWebApiRequest buildRequest() {

            if (language != null) {
                addParameter(REQUEST_PARAM_LANGUAGE,language);
            }

            return new GetLeagueListing(this);
        }
    }
}
