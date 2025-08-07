package com.kstd.project.user.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table
public class UserCoupon {

    @Id
    private Long id;

    private Long userId;

    private Long couponId;
}
