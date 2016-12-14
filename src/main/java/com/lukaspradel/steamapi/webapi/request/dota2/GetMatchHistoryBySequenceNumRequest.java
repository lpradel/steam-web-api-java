package com.lukaspradel.steamapi.webapi.request.dota2;

import com.lukaspradel.steamapi.webapi.core.SteamWebApiInterface;
import com.lukaspradel.steamapi.webapi.core.SteamWebApiInterfaceMethod;
import com.lukaspradel.steamapi.webapi.core.SteamWebApiVersion;
import com.lukaspradel.steamapi.webapi.request.SteamWebApiRequest;
import com.lukaspradel.steamapi.webapi.request.builders.AbstractSteamWebApiRequestBuilder;

/**
 * Steam Web API Request Match history by sequence number
 *
 * @see <a href=
 *      "https://wiki.teamfortress.com/wiki/WebAPI/GetMatchHistoryBySequenceNum"
 *      >https://wiki.teamfortress.com/wiki/WebAPI/GetMatchHistoryBySequenceNum</a>
 * @author Pazus
 *
 */
public class GetMatchHistoryBySequenceNumRequest extends SteamWebApiRequest {
    public GetMatchHistoryBySequenceNumRequest(SteamWebApiRequestBuilder builder) {super(builder);}

    /**
     * Builder object to create instances of {@link SteamWebApiRequest} for
     * request type {@link GetMatchHistoryBySequenceNumRequest}.
     *
     * @author Pazus
     *
     */
    public static class GetMatchHistoryBySequenceNumRequestBuilder extends AbstractSteamWebApiRequestBuilder {
        private Long startAtMatchSeqNum;
        private Integer matchesRequested;

        public static final String REQUEST_PARAM_START_AT_MATCH_SEQ_NUM = "start_at_match_seq_num";

        public static final String REQUEST_PARAM_MATCHES_REQUESTED = "matches_requested";

        public GetMatchHistoryBySequenceNumRequestBuilder startAtMatchSeqNum(Long startAtMatchSeqNum) {
            this.startAtMatchSeqNum = startAtMatchSeqNum;
            return this;
        }

        public GetMatchHistoryBySequenceNumRequestBuilder matchesRequested(Integer matchesRequested) {
            this.matchesRequested = matchesRequested;
            return this;
        }

        @Override
        protected SteamWebApiInterface getInterface() {
            return SteamWebApiInterface.I_DOTA2_MATCH;
        }

        @Override
        protected SteamWebApiInterfaceMethod getInterfaceMethod() {
            return SteamWebApiInterfaceMethod.GET_MATCH_HISTORY_BY_SEQUENCE_NUM;
        }

        @Override
        protected SteamWebApiVersion getVersion() {
            return SteamWebApiVersion.VERSION_ONE;
        }

        @Override
        public GetMatchHistoryBySequenceNumRequest buildRequest() {

            if (startAtMatchSeqNum != null) {
                addParameter(REQUEST_PARAM_START_AT_MATCH_SEQ_NUM,startAtMatchSeqNum);
            }

            if (matchesRequested != null) {
                addParameter(REQUEST_PARAM_MATCHES_REQUESTED,matchesRequested);
            }

            return new GetMatchHistoryBySequenceNumRequest(this);

        }
    }

}
