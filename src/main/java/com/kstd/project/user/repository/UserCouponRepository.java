package com.kstd.project.user.repository;

import com.kstd.project.user.domain.UserCoupon;
import com.kstd.project.user.domain.UserCouponStatus;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserCouponRepository extends ReactiveCrudRepository<UserCoupon, Long> {

	Flux<UserCoupon> findByUserId(Long userId);

	Flux<UserCoupon> findByCouponId(Long couponId);

	Mono<UserCoupon> findByIdAndUserIdAndStatus(Long id, Long userId, UserCouponStatus status);
}
