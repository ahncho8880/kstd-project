package com.kstd.project.coupon.service;

import com.kstd.project.coupon.domain.CouponType;
import com.kstd.project.coupon.service.dto.CouponDto;

import reactor.core.publisher.Mono;

public interface CouponService {
	// 휴가쿠폰응모
	Mono<CouponDto> applyCoupon(Long couponId, Long userId);
	// 휴가쿠폰취소
	Mono<CouponDto> cancelCoupon(Long userCouponId, Long userId);
	// 휴가 쿠폰별 전체 응모 현황 조회
	Mono<CouponDto> getAllUserCoupon(CouponType couponType);
}
