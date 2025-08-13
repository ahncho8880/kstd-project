package com.kstd.project.coupon.domain;

import com.kstd.project.common.domain.BaseTimeEntity;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Getter;

@Getter
@Table
public class Coupon extends BaseTimeEntity {

    @Id
    private Long id;

    private CouponType type;

    private Integer requiredCoins;

	private Integer winnerCount;
}
