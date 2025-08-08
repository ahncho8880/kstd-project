package com.kstd.project.coupon.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@Tag(name = "쿠폰", description = "휴가 쿠폰 관련 api")
public class CouponController {

    @PostMapping("/coupon/apply")
    @Operation(summary = "휴가 쿠폰 응모", description = "소유한 응모 코인으로 특정 휴가 쿠폰에 응모한다")
    public Mono<CouponApplyResponse> apply() {
        return Mono.empty();
    }

    @PutMapping("/coupon/cancel")
    @Operation(summary = "휴가 쿠폰 취소", description = "응모한 휴가 쿠폰 응모를 취소한다")
    public Mono<CouponCancelResponse> cancel() {
        return Mono.empty();
    }

    @GetMapping("/coupon")
    @Operation(summary = "휴가 쿠폰별 전체 응모 현황 조회", description = "휴가 쿠폰별 전체 응모 현황을 조회한다")
    public Mono<CouponCheckResponse> check() {
        return Mono.empty();
    }

    public record CouponApplyResponse() {}
    public record CouponCancelResponse() {}
    public record CouponCheckResponse() {}
}
