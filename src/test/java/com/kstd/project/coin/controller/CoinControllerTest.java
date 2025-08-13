package com.kstd.project.coin.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.kstd.project.coupon.controller.CouponController;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class CoinControllerTest {

	@Autowired
	private WebTestClient webTestClient;

	@Test
	void apply() {
		Long userId = 1L;
		Long coinId = 1L;
		CoinController.CoinApplyRequest request = new CoinController.CoinApplyRequest(userId, coinId);

		webTestClient.post()
			.uri("/coins/apply")
			.contentType(MediaType.APPLICATION_JSON)
			.bodyValue(request)
			.exchange()
			.expectStatus().isOk()
			.expectBody()
			.jsonPath("$.userId").isEqualTo(userId)
			.jsonPath("$.userCoins").isNumber();
	}

	@Test
	void report() {
		Long coinId = 1L;

		webTestClient.get()
			.uri("/coins/{coinId}/report", coinId)
			.exchange()
			.expectStatus().isOk()
			.expectBody()
			.jsonPath("$.totalRemainingCoins").isNumber()
			.jsonPath("$.users").isArray();
	}
}