package com.lukaspradel.steamapi;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.mockito.MockitoAnnotations;
import org.powermock.modules.testng.PowerMockTestCase;
import org.testng.annotations.BeforeMethod;

public class BaseTest extends PowerMockTestCase {

	@BeforeMethod(alwaysRun = true)
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}

	protected String readResourceAsString(String fileName) throws IOException {
		try {
			Path filePath = Paths.get(this.getClass().getResource(fileName).toURI());
			return new String(Files.readAllBytes(filePath), Charset.defaultCharset());
		} catch (URISyntaxException e) {
			throw new IOException(e);
		}
	}
}
