package com.kstd.project.coupon.repository;

import com.kstd.project.coupon.domain.Coupon;
import com.kstd.project.coupon.domain.CouponType;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import reactor.core.publisher.Mono;

public interface CouponRepository extends ReactiveCrudRepository<Coupon, Long> {

	Mono<Coupon> findByType(CouponType type);
}
