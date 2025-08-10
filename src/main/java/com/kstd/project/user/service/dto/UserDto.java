package com.kstd.project.user.service.dto;

import com.kstd.project.coupon.domain.CouponType;
import com.kstd.project.user.domain.User;
import com.kstd.project.user.domain.UserCoupon;
import com.kstd.project.user.domain.UserCouponStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class UserDto {

    private Long userId;

    private Integer userCoins;

    private Long userCouponId;

	private Long couponId;

	private CouponType couponType;

	private UserCouponStatus userCouponStatus;

    public static UserDto of(User user) {
        return UserDto.builder().userId(user.getId()).userCoins(user.getCoinAmount()).build();
    }

    public static UserDto of(UserCoupon userCoupon) {
        return UserDto.builder().userId(userCoupon.getUserId()).userCouponId(userCoupon.getId()).couponId(userCoupon.getCouponId()).build();
    }
}