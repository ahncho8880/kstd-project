package com.kstd.project.coupon.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kstd.project.common.exception.KstdException;
import com.kstd.project.coupon.domain.CouponType;
import com.kstd.project.coupon.service.CouponService;
import com.kstd.project.coupon.service.dto.CouponDto;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
@Tag(name = "쿠폰", description = "휴가 쿠폰 관련 api")
public class CouponController {

    private final CouponService couponService;

    @PostMapping("/coupons/apply")
    @Operation(summary = "휴가 쿠폰 응모", description = "소유한 응모 코인으로 특정 휴가 쿠폰에 응모한다")
    public Mono<CouponApplyResponse> apply(@RequestBody CouponApplyRequest request) {
        return couponService.applyCoupon(request.couponId, request.userId)
            .map(CouponApplyResponse::of);
    }

    @PutMapping("/coupons/cancel")
    @Operation(summary = "휴가 쿠폰 취소", description = "응모한 휴가 쿠폰 응모를 취소한다")
    public Mono<CouponCancelResponse> cancel(@RequestBody CouponCancelRequest request) {
        return couponService.cancelCoupon(request.userCouponId, request.userId)
            .map(CouponCancelResponse::of);
    }

    @GetMapping("/coupons/{couponType}")
    @Operation(summary = "휴가 쿠폰별 전체 응모 현황 조회", description = "휴가 쿠폰별 전체 응모 현황을 조회한다")
    public Mono<CouponCheckResponse> check(@Parameter(description = "holiday1/holiday3") @PathVariable(value = "couponType") String name) throws KstdException {
        return couponService.getAllUserCoupon(CouponType.of(name))
			.map(CouponCheckResponse::of);
    }

    public record CouponApplyResponse(Long couponId, Long userId, Long userCouponId, Integer userCoins) {
        public static CouponApplyResponse of(CouponDto couponDto) {
            return new CouponApplyResponse(couponDto.getCouponId(), couponDto.getUserId(), couponDto.getUserCouponId(), couponDto.getUserCoins());
        }
    }
    public record CouponCancelResponse(Long couponId, Long userId, Integer userCoins) {
		public static CouponCancelResponse of(CouponDto couponDto) {
			return new CouponCancelResponse(couponDto.getCouponId(), couponDto.getUserId(), couponDto.getUserCoins());
		}
	}
    public record CouponCheckResponse(Long couponId, String couponType, Integer totalApplications, CouponCheckStatusResponse statusCounts) {
		public static CouponCheckResponse of(CouponDto couponDto) {
			return new CouponCheckResponse(couponDto.getCouponId(), couponDto.getType().name(), couponDto.getTotalApplications() ,new CouponCheckStatusResponse(
				couponDto.getAppliedApplications(), couponDto.getCancelledApplications()));
		}

		record CouponCheckStatusResponse(Long applied, Long canceled) {}
	}
    public record CouponApplyRequest(Long userId, Long couponId) {}
    public record CouponCancelRequest(Long userId, Long userCouponId) {}
}
