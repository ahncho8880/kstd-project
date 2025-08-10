package com.kstd.project.coupon.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.kstd.project.coupon.domain.CouponType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class CouponControllerTest {

	@Autowired
	private WebTestClient webTestClient;
	Long userId = 1L;
	Long couponId = 1L;

	@Test
	void apply() {
		CouponController.CouponApplyRequest request = new CouponController.CouponApplyRequest(userId, couponId);

		webTestClient.post()
			.uri("/coupons/apply")
			.contentType(MediaType.APPLICATION_JSON)
			.bodyValue(request)
			.exchange()
			.expectStatus().isOk()
			.expectBody()
			.jsonPath("$.couponId").isEqualTo(userId)
			.jsonPath("$.userId").isEqualTo(couponId)
			.jsonPath("$.userCoins").isNumber();
	}

	@Test
	void cancel() {
		CouponController.CouponCancelRequest request = new CouponController.CouponCancelRequest(userId, couponId);

		webTestClient.put()
			.uri("/coupons/cancel")
			.contentType(MediaType.APPLICATION_JSON)
			.bodyValue(request)
			.exchange()
			.expectStatus().is4xxClientError();
			// .expectBody()
			// .jsonPath("$.totalRemainingCoins").isNumber()
			// .jsonPath("$.users").isArray();
	}

	@Test
	void check() {
		String type = "holiday1"; // or "holiday3"

		webTestClient.get()
			.uri("/coupons/{couponType}", type)
			.exchange()
			.expectStatus().isOk()
			.expectBody()
			.jsonPath("$.couponId").isNumber()
			.jsonPath("$.couponType").isEqualTo(CouponType.of(type))
			.jsonPath("$.totalApplications").isNumber()
			.jsonPath("$.statusCounts").isMap();
	}
}