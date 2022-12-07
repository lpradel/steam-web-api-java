package com.lukaspradel.steamapi;

import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class BaseTest {

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
