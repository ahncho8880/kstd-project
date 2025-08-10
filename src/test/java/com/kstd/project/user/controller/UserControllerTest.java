package com.kstd.project.user.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.kstd.project.coupon.domain.CouponType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class UserControllerTest {

	@Autowired
	private WebTestClient webTestClient;
		Long userId = 1L;

	@Test
	void userCoupon() {

		webTestClient.get()
			.uri("/users/{id}/coupon", userId)
			.exchange()
			.expectStatus().isOk()
			.expectBody()
			.jsonPath("$.userId").isEqualTo(userId)
			.jsonPath("$.userCoupon").isArray();
	}

	@Test
	void userCoin() {

		webTestClient.get()
			.uri("/users/{userId}/coins", userId)
			.exchange()
			.expectStatus().isOk()
			.expectBody()
			.jsonPath("$.userId").isEqualTo(userId)
			.jsonPath("$.coinAmount").isNumber();
	}
}