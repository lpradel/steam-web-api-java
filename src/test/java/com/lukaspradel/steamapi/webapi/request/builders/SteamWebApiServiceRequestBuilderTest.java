package com.lukaspradel.steamapi.webapi.request.builders;

import com.lukaspradel.steamapi.webapi.core.BaseTest;
import com.lukaspradel.steamapi.webapi.request.SteamWebApiRequest;
import com.lukaspradel.steamapi.webapi.request.SteamWebApiServiceParameter;
import org.testng.annotations.Test;

public class SteamWebApiServiceRequestBuilderTest extends BaseTest {

	public class SteamWebApiServiceTestParameter extends
			SteamWebApiServiceParameter {

	}

	public class SteamWebApiServiceRequestTestBuilder extends
			AbstractSteamWebApiServiceRequestBuilder {

		@Override
		public SteamWebApiRequest buildRequest() {

			SteamWebApiServiceTestParameter serviceParameter = new SteamWebApiServiceTestParameter();
			addServiceParameter(serviceParameter);

			return SteamWebApiRequestFactory
					.createGetFriendListRequest("12345");
		}
	}

	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testSteamWebApiServiceRequestBuilderJsonMappingException() {

		SteamWebApiServiceRequestTestBuilder serviceRequestTestBuilder = new SteamWebApiServiceRequestTestBuilder();

		serviceRequestTestBuilder.buildRequest();
	}
}
