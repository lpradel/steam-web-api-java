package com.lukaspradel.steamapi;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.mockito.MockitoAnnotations;
import org.powermock.modules.testng.PowerMockTestCase;
import org.testng.annotations.BeforeMethod;

public class BaseTest extends PowerMockTestCase {

	@BeforeMethod(alwaysRun = true)
	public void injectDoubles() {
		MockitoAnnotations.initMocks(this);
	}

	protected String readResourceAsString(String fileName) throws IOException {

		return IOUtils.toString(this.getClass().getResourceAsStream(fileName));
	}
}
