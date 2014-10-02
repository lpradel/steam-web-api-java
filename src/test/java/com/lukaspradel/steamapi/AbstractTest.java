package com.lukaspradel.steamapi;

import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;

public abstract class AbstractTest {

	@BeforeMethod(alwaysRun = true)
	public void injectDoubles() {
		MockitoAnnotations.initMocks(this);
	}
}
