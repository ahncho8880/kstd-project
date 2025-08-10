package com.kstd.project.coupon.service.dto;

import com.kstd.project.coupon.domain.Coupon;
import com.kstd.project.coupon.domain.CouponType;
import com.kstd.project.user.service.dto.UserDto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CouponDto {
	private Long couponId;

	private Long userId;

	private CouponType type;

	private Integer userCoins;

	private Long userCouponId;

	private Integer totalApplications;

	private Long appliedApplications;

	private Long cancelledApplications;

	public static CouponDto of(UserDto userDto) {
		return CouponDto.builder().couponId(userDto.getCouponId()).userId(userDto.getUserId()).userCoins(userDto.getUserCoins()).build();
	}

	public static CouponDto of(Coupon coupon) {
		return CouponDto.builder()
			.couponId(coupon.getId())
			.type(coupon.getType())
			.build();
	}
}
