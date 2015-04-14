package com.lukaspradel.steamapi.webapi.request;

import com.lukaspradel.steamapi.webapi.core.SteamWebApiInterface;
import com.lukaspradel.steamapi.webapi.core.SteamWebApiInterfaceMethod;
import com.lukaspradel.steamapi.webapi.core.SteamWebApiVersion;
import com.lukaspradel.steamapi.webapi.request.builders.AbstractSteamWebApiRequestBuilder;

/**
 * Steam Web API Request GetFriendList
 *
 * @see <a href=
 *      "https://developer.valvesoftware.com/wiki/Steam_Web_API#GetFriendList_.28v0001.29"
 *      >https://developer.valvesoftware.com/wiki/Steam_Web_API#GetFriendList_.28v0001.29</a>
 * @author lpradel
 *
 */
public class GetFriendListRequest extends SteamWebApiRequest {

	private GetFriendListRequest(SteamWebApiRequestBuilder builder) {
		super(builder);
	}

	/**
	 * Relationship filter for request type {@link GetFriendListRequest}.
	 *
	 * @author lpradel
	 *
	 */
	public enum Relationship {

		/** Users with ANY relationship to the user */
		ALL("all"),
		/** Only users with the FRIEND relationship to the user */
		FRIEND("friend");

		private final String relationship;

		private Relationship(String relationship) {

			this.relationship = relationship;
		}

		@Override
		public String toString() {

			return relationship;
		}
	}

	/**
	 * Builder object to create instances of {@link SteamWebApiRequest} for
	 * request type {@link GetFriendListRequest}.
	 *
	 * @author lpradel
	 *
	 */
	public static class GetFriendListRequestBuilder extends
			AbstractSteamWebApiRequestBuilder {

		private final String steamId;

		private Relationship relationship = null;

		public static final String REQUEST_PARAM_STEAM_ID = "steamid";

		public static final String REQUEST_PARAM_RELATIONSHIP = "relationship";

		public GetFriendListRequestBuilder(String steamId) {

			this.steamId = steamId;
		}

		public GetFriendListRequestBuilder relationship(
				Relationship relationship) {

			this.relationship = relationship;
			return this;
		}

		@Override
		protected SteamWebApiInterface getInterface() {

			return SteamWebApiInterface.I_STEAM_USER;
		}

		@Override
		protected SteamWebApiInterfaceMethod getInterfaceMethod() {

			return SteamWebApiInterfaceMethod.GET_FRIEND_LIST;
		}

		@Override
		protected SteamWebApiVersion getVersion() {

			return SteamWebApiVersion.VERSION_ONE;
		}

		@Override
		public GetFriendListRequest buildRequest() {

			addParameter(REQUEST_PARAM_STEAM_ID, steamId);

			if (relationship != null) {
				addParameter(REQUEST_PARAM_RELATIONSHIP,
						relationship.toString());
			}

			return new GetFriendListRequest(this);
		}
	}
}
