package com.example.sampler2dbc;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;


import static org.assertj.core.api.Assertions.assertThat;

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
				.expectBody(JsonNode.class)
				.consumeWith(r -> {
					JsonNode body = r.getResponseBody();
					assertThat(body).isNotNull();
					assertThat(body.size()).isEqualTo(5);

					assertThat(body.get(0).get("id").asText()).isEqualTo("1");
					assertThat(body.get(0).get("hello").asText()).isEqualTo("Hello, world!");
					assertThat(body.get(1).get("id").asText()).isEqualTo("2");
					assertThat(body.get(1).get("hello").asText()).isEqualTo("Bonjour, monde!");
					assertThat(body.get(2).get("id").asText()).isEqualTo("3");
					assertThat(body.get(2).get("hello").asText()).isEqualTo("Hola, mundo!");
					assertThat(body.get(3).get("id").asText()).isEqualTo("4");
					assertThat(body.get(3).get("hello").asText()).isEqualTo("Olá, mundo!");
					assertThat(body.get(4).get("id").asText()).isEqualTo("5");
					assertThat(body.get(4).get("hello").asText()).isEqualTo("こんにちは、世界！");
				});
	}
}
