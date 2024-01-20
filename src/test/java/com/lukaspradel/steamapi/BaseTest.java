package com.lukaspradel.steamapi;

import org.mockito.MockitoAnnotations;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class BaseTest {

	private AutoCloseable mocks = null;

	@BeforeMethod(alwaysRun = true)
	public void initMocks() {
		mocks = MockitoAnnotations.openMocks(this);
	}

	@AfterMethod(alwaysRun = true)
	public void closeMocks() throws Exception {
		if (mocks != null)
			mocks.close();
	}

	protected String readResourceAsString(String fileName) throws IOException {
		try {
			Path filePath = Paths.get(this.getClass().getResource(fileName).toURI());
			return Files.readString(filePath, Charset.defaultCharset());
		} catch (URISyntaxException e) {
			throw new IOException(e);
		}
	}
}
