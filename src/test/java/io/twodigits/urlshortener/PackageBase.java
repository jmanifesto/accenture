package io.twodigits.urlshortener;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import io.twodigits.urlshortener.api.URLController;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@DirtiesContext
@AutoConfigureMessageVerifier
class PackageBase {

	@Autowired
	private URLController urlController;

	@Test
	void contextLoads() {
	}

	@Before
	public void setup() {
		StandaloneMockMvcBuilder standaloneMockMvcBuilder
				= MockMvcBuilders.standaloneSetup(urlController);
		RestAssuredMockMvc.standaloneSetup(standaloneMockMvcBuilder);
	}

}
