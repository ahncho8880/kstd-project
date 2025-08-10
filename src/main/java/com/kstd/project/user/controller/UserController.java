package com.kstd.project.user.controller;

import java.util.List;

import com.kstd.project.user.service.UserService;
import com.kstd.project.user.service.dto.UserDto;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
@Tag(name = "사용자", description = "사용자 관련 api")
public class UserController {

	private final UserService userService;

    @GetMapping("/users/{userId}/coupon")
    @Operation(summary = "사용자 응모 현황 조회", description = "특정 사용자의 응모 현황을 조회한다")
    public Mono<UserCouponResponse> userCoupon(@PathVariable(value = "userId") Long userId) {
        return userService.getUserCoupon(userId)
			.map(UserCouponResponse::of);
    }

    @GetMapping("/users/{userId}/coins")
    @Operation(summary = "사용자 응모 코인 수량 조회", description = "사용자가 가진 응모 코인 수량 조회한다")
    public Mono<UserCoinResponse> userCoin(@PathVariable(value = "userId") Long userId) {
        return userService.getUser(userId)
			.map(UserCoinResponse::of);
    }

    public record UserCouponResponse(Long userId, List<UserCouponDataResponse> userCoupon) {
		public static UserCouponResponse of(List<UserDto> userDtoList) {
			return new UserCouponResponse(userDtoList.getFirst().getUserId(), UserCouponDataResponse.of(userDtoList));
		}
		record UserCouponDataResponse(Long userCouponId, Long couponId, String couponType, String status) {
			public static List<UserCouponDataResponse> of(List<UserDto> userDtoList) {
				return userDtoList.stream()
					.map(userDto -> new UserCouponDataResponse(userDto.getUserCouponId(), userDto.getCouponId(), userDto.getCouponType().name(), userDto.getUserCouponStatus().name()))
					.toList();
			}
		}
	}
    public record UserCoinResponse(String userId, Integer coinAmount) {
		public static UserCoinResponse of(UserDto userDto) {
			return new UserCoinResponse(userDto.getUserId().toString(), userDto.getUserCoins());
		}
	}
}
