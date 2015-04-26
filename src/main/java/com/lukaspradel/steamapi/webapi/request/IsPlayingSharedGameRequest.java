package com.lukaspradel.steamapi.webapi.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lukaspradel.steamapi.webapi.core.SteamWebApiInterface;
import com.lukaspradel.steamapi.webapi.core.SteamWebApiInterfaceMethod;
import com.lukaspradel.steamapi.webapi.core.SteamWebApiVersion;
import com.lukaspradel.steamapi.webapi.request.builders.AbstractSteamWebApiServiceRequestBuilder;

/**
 * Steam Web API Request IsPlayingSharedGame
 *
 * @see <a href=
 *      "https://developer.valvesoftware.com/wiki/Steam_Web_API#IsPlayingSharedGame_.28v0001.29"
 *      >https://developer.valvesoftware.com/wiki/Steam_Web_API#IsPlayingSharedGame_.28v0001.29</a>
 * @author lpradel
 *
 */
public class IsPlayingSharedGameRequest extends SteamWebApiRequest {

	private IsPlayingSharedGameRequest(SteamWebApiRequestBuilder builder) {
		super(builder);
	}

	/**
	 * The service parameter instance of {@link SteamWebApiServiceParameter} for
	 * {@link IsPlayingSharedGameRequest}.
	 *
	 * @author lpradel
	 *
	 */
	@JsonInclude(JsonInclude.Include.NON_NULL)
	public static class IsPlayingSharedGameRequestServiceParameter extends
			SteamWebApiServiceParameter {

		private String steamId;

		private Integer appIdPlaying;

		@JsonProperty(IsPlayingSharedGameRequestBuilder.REQUEST_PARAM_STEAM_ID)
		public String getSteamId() {
			return steamId;
		}

		@JsonProperty(IsPlayingSharedGameRequestBuilder.REQUEST_PARAM_APP_ID_PLAYING)
		public Integer getAppIdPlaying() {
			return appIdPlaying;
		}

		public void setSteamId(String steamId) {
			this.steamId = steamId;
		}

		public void setAppIdPlaying(Integer appIdPlaying) {
			this.appIdPlaying = appIdPlaying;
		}
	}

	/**
	 * Builder object to create instances of {@link SteamWebApiRequest} for
	 * request type {@link IsPlayingSharedGameRequest}.
	 *
	 * @author lpradel
	 *
	 */
	public static class IsPlayingSharedGameRequestBuilder extends
			AbstractSteamWebApiServiceRequestBuilder {

		private final String steamId;

		private final Integer appIdPlaying;

		public static final String REQUEST_PARAM_STEAM_ID = "steamid";

		public static final String REQUEST_PARAM_APP_ID_PLAYING = "appid_playing";

		public IsPlayingSharedGameRequestBuilder(String steamId,
				Integer appIdPlaying) {

			this.steamId = steamId;
			this.appIdPlaying = appIdPlaying;
		}

		@Override
		protected SteamWebApiInterface getInterface() {

			return SteamWebApiInterface.I_PLAYER_SERVICE;
		}

		@Override
		protected SteamWebApiInterfaceMethod getInterfaceMethod() {

			return SteamWebApiInterfaceMethod.IS_PLAYING_SHARED_GAME;
		}

		@Override
		protected SteamWebApiVersion getVersion() {

			return SteamWebApiVersion.VERSION_ONE;
		}

		@Override
		public IsPlayingSharedGameRequest buildRequest() {

			IsPlayingSharedGameRequestServiceParameter serviceParameter = new IsPlayingSharedGameRequestServiceParameter();

			serviceParameter.setSteamId(steamId);
			serviceParameter.setAppIdPlaying(appIdPlaying);

			addServiceParameter(serviceParameter);

			return new IsPlayingSharedGameRequest(this);
		}
	}
}
