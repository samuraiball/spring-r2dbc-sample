package com.example.sampler2dbc;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SampleR2dbcApplicationTests {

	@LocalServerPort
	int port;

	WebTestClient webTestClient;

	@Before
	public void before() {
		this.webTestClient = WebTestClient.bindToServer()
				.baseUrl("http://localhost:" + port)
				.build();
	}

	@Test
	public void testHello() throws Exception {
		this.webTestClient.get()
				.uri("/hello")
				.exchange()
				.expectStatus().isOk()
				.expectBody(String.class).isEqualTo("Hello, world!Bonjour, monde!Hola, mundo!Olá, mundo!こんにちは、世界！");
	}
}
