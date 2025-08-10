package com.kstd.project.coin.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class CoinControllerTest {

	@Autowired
	private WebTestClient webTestClient;

	@Test
	void apply() {
	}

	@Test
	void report() {
		Long coinId = 1L;

		webTestClient.get()
			.uri("/coin/{id}/report", coinId)
			.exchange()
			.expectStatus().isOk()
			.expectBody()
			.jsonPath("$.totalRemainingCoins").isNumber()
			.jsonPath("$.users").isArray();
	}
}