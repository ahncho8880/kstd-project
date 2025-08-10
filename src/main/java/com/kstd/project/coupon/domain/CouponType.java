package com.kstd.project.coupon.domain;

import com.kstd.project.common.exception.KstdException;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CouponType {
    HOLIDAY1("holiday1"),
	HOLIDAY3("holiday3"),;

	private final String name;

	public static CouponType of(String name) throws KstdException {
		for (CouponType couponType : CouponType.values()) {
			if (couponType.getName().equals(name)) {
				return couponType;
			}
		}
		throw new KstdException("Invalid coupon type name: " + name);
	}
}
