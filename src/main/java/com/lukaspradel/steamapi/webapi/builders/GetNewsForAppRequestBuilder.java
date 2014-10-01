package com.lukaspradel.steamapi.webapi.builders;

import com.lukaspradel.steamapi.webapi.SteamWebApiInterface;
import com.lukaspradel.steamapi.webapi.SteamWebApiInterfaceMethod;
import com.lukaspradel.steamapi.webapi.SteamWebApiRequest;
import com.lukaspradel.steamapi.webapi.SteamWebApiVersion;

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

		addParameter("appid", appId);

		if (count != null) {
			addParameter("count", count);
		}

		if (maxLength != null) {
			addParameter("maxlength", maxLength);
		}

		return super.buildRequest();
	}
}