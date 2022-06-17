package com.lukaspradel.steamapi.webapi.request.builders;

import static org.mockito.Mockito.when;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.reflect.Whitebox;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lukaspradel.steamapi.BaseTest;
import com.lukaspradel.steamapi.webapi.request.SteamWebApiRequest;
import com.lukaspradel.steamapi.webapi.request.SteamWebApiServiceParameter;

public class SteamWebApiServiceRequestBuilderTest extends BaseTest {

	@Mock
	ObjectMapper mapperMock;

	@Mock
	JsonProcessingException mockException;

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
	public void testSteamWebApiServiceRequestBuilderJsonMappingException()
			throws JsonProcessingException {

		SteamWebApiServiceRequestTestBuilder serviceRequestTestBuilder = new SteamWebApiServiceRequestTestBuilder();

		when(
				mapperMock.writeValueAsString(Mockito
						.any(SteamWebApiServiceTestParameter.class)))
				.thenThrow(mockException);
		Whitebox.setInternalState(serviceRequestTestBuilder, "mapper",
				mapperMock);

		serviceRequestTestBuilder.buildRequest();
	}
}
