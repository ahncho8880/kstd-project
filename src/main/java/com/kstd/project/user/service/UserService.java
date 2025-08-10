package com.kstd.project.user.service;

import java.util.List;

import com.kstd.project.user.service.dto.UserDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {

    Flux<UserDto> getAllUserCoin();

	Mono<UserDto> getUser(Long userId);

	Mono<UserDto> updateUserCoin(long userId, int requiredCoins);

	Mono<UserDto> saveUserCoupon(Long userId, Long couponId);

	Mono<UserDto> getUserCoupon(Long userCouponId, Long userId);

	Mono<UserDto> deleteUserCoupon(Long userCouponId);

	Mono<List<UserDto>> getUserCoupon(Long userId);

	Flux<UserDto> getUserCouponByCouponId(Long couponId);
}
