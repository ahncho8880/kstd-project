package com.kstd.project.coupon.domain;

import com.kstd.project.common.domain.BaseTimeEntity;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table
public class Coupon extends BaseTimeEntity {

    @Id
    private Long id;

    private CouponType type;

    private int requiredCoins;
}
