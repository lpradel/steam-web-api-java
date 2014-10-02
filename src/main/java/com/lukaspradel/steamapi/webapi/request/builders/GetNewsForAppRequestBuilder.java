package com.lukaspradel.steamapi.webapi.request.builders;

import com.lukaspradel.steamapi.webapi.SteamWebApiInterface;
import com.lukaspradel.steamapi.webapi.SteamWebApiInterfaceMethod;
import com.lukaspradel.steamapi.webapi.SteamWebApiVersion;
import com.lukaspradel.steamapi.webapi.request.SteamWebApiRequest;

/**
 * Builder object to create instances of {@link SteamWebApiRequest} for request
 * type GetNewsForApp.
 *
 * @author lpradel
 *
 */
public class GetNewsForAppRequestBuilder extends
		AbstractSteamWebApiRequestBuilder {

	private final Integer appId;

	private Integer count;

	private Integer maxLength;

	static final String REQUEST_PARAM_APP_ID = "appid";

	static final String REQUEST_PARAM_COUNT = "count";

	static final String REQUEST_PARAM_MAX_LENGTH = "maxlength";

	public GetNewsForAppRequestBuilder(Integer appId) {

		this.appId = appId;
	}

	public GetNewsForAppRequestBuilder count(Integer count) {

		this.count = count;
		return this;
	}

	public GetNewsForAppRequestBuilder maxLength(Integer maxLength) {

		this.maxLength = maxLength;
		return this;
	}

	@Override
	protected SteamWebApiInterface getInterface() {

		return SteamWebApiInterface.I_STEAM_NEWS;
	}

	@Override
	protected SteamWebApiInterfaceMethod getInterfaceMethod() {

		return SteamWebApiInterfaceMethod.GET_NEWS_FOR_APP;
	}

	@Override
	protected SteamWebApiVersion getVersion() {

		return SteamWebApiVersion.VERSION_TWO;
	}

	@Override
	public SteamWebApiRequest buildRequest() {

		addParameter(REQUEST_PARAM_APP_ID, appId);

		if (count != null) {
			addParameter(REQUEST_PARAM_COUNT, count);
		}

		if (maxLength != null) {
			addParameter(REQUEST_PARAM_MAX_LENGTH, maxLength);
		}

		return super.buildRequest();
	}
}