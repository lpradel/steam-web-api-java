package com.lukaspradel.steamapi.webapi.request;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lukaspradel.steamapi.webapi.core.SteamWebApiInterface;
import com.lukaspradel.steamapi.webapi.core.SteamWebApiInterfaceMethod;
import com.lukaspradel.steamapi.webapi.core.SteamWebApiVersion;
import com.lukaspradel.steamapi.webapi.request.builders.AbstractSteamWebApiServiceRequestBuilder;

/**
 * Steam Web API Request GetOwnedGames
 *
 * @see <a href=
 *      "https://developer.valvesoftware.com/wiki/Steam_Web_API#GetOwnedGames_.28v0001.29"
 *      >https://developer.valvesoftware.com/wiki/Steam_Web_API#GetOwnedGames_.28v0001.29</a>
 * @author lpradel
 *
 */
public class GetOwnedGamesRequest extends SteamWebApiRequest {

	private GetOwnedGamesRequest(SteamWebApiRequestBuilder builder) {
		super(builder);
	}

	/**
	 * The service parameter instance of {@link SteamWebApiServiceParameter} for
	 * {@link GetOwnedGamesRequest}.
	 *
	 * @author lpradel
	 *
	 */
	@JsonInclude(JsonInclude.Include.NON_NULL)
	public static class GetOwnedGamesRequestServiceParameter extends
			SteamWebApiServiceParameter {

		private String steamId;

		private Integer includeAppInfo;

		private Integer includePlayedFreeGames;

		private List<Integer> appIdsFilter;

		@JsonProperty(GetOwnedGamesRequestBuilder.REQUEST_PARAM_STEAM_ID)
		public String getSteamId() {
			return steamId;
		}

		@JsonProperty(GetOwnedGamesRequestBuilder.REQUEST_PARAM_INCLUDE_APP_INFO)
		public Integer getIncludeAppInfo() {
			return includeAppInfo;
		}

		@JsonProperty(GetOwnedGamesRequestBuilder.REQUEST_PARAM_INCLUDE_PLAYED_FREE_GAMES)
		public Integer getIncludePlayedFreeGames() {
			return includePlayedFreeGames;
		}

		@JsonProperty(GetOwnedGamesRequestBuilder.REQUEST_PARAM_APP_IDS_FILTER)
		public List<Integer> getAppIdsFilter() {
			return appIdsFilter;
		}

		public void setSteamId(String steamId) {
			this.steamId = steamId;
		}

		public void setIncludeAppInfo(Integer includeAppInfo) {
			this.includeAppInfo = includeAppInfo;
		}

		public void setIncludePlayedFreeGames(Integer includePlayedFreeGames) {
			this.includePlayedFreeGames = includePlayedFreeGames;
		}

		public void setAppIdsFilter(List<Integer> appIdsFilter) {
			this.appIdsFilter = appIdsFilter;
		}
	}

	/**
	 * Builder object to create instances of {@link SteamWebApiRequest} for
	 * request type {@link GetOwnedGamesRequest}.
	 *
	 * @author lpradel
	 *
	 */
	public static class GetOwnedGamesRequestBuilder extends
			AbstractSteamWebApiServiceRequestBuilder {

		private final String steamId;

		private boolean includeAppInfo;

		private boolean includePlayedFreeGames;

		private List<Integer> appIdsFilter = new ArrayList<Integer>();

		public static final String REQUEST_PARAM_STEAM_ID = "steamid";

		public static final String REQUEST_PARAM_INCLUDE_APP_INFO = "include_appinfo";

		public static final String REQUEST_PARAM_INCLUDE_PLAYED_FREE_GAMES = "include_played_free_games";

		public static final String REQUEST_PARAM_APP_IDS_FILTER = "appids_filter";

		public GetOwnedGamesRequestBuilder(String steamId) {

			this.steamId = steamId;
		}

		public GetOwnedGamesRequestBuilder includeAppInfo(boolean includeAppInfo) {

			this.includeAppInfo = includeAppInfo;
			return this;
		}

		public GetOwnedGamesRequestBuilder includePlayedFreeGames(
				boolean includePlayedFreeGames) {

			this.includePlayedFreeGames = includePlayedFreeGames;
			return this;
		}

		public GetOwnedGamesRequestBuilder appIdsFilter(
				List<Integer> appIdsFilter) {

			this.appIdsFilter.addAll(appIdsFilter);
			return this;
		}

		@Override
		protected SteamWebApiInterface getInterface() {

			return SteamWebApiInterface.I_PLAYER_SERVICE;
		}

		@Override
		protected SteamWebApiInterfaceMethod getInterfaceMethod() {

			return SteamWebApiInterfaceMethod.GET_OWNED_GAMES;
		}

		@Override
		protected SteamWebApiVersion getVersion() {

			return SteamWebApiVersion.VERSION_ONE;
		}

		@Override
		public GetOwnedGamesRequest buildRequest() {

			GetOwnedGamesRequestServiceParameter serviceParameter = new GetOwnedGamesRequestServiceParameter();

			serviceParameter.setSteamId(steamId);

			if (includeAppInfo) {
				serviceParameter.setIncludeAppInfo(1);
			}

			if (includePlayedFreeGames) {
				serviceParameter.setIncludePlayedFreeGames(1);
			}

			if (!appIdsFilter.isEmpty()) {
				serviceParameter.setAppIdsFilter(appIdsFilter);
			}

			addServiceParameter(serviceParameter);

			return new GetOwnedGamesRequest(this);
		}
	}
}
