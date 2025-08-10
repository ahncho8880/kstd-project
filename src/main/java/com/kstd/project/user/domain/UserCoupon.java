package com.kstd.project.user.domain;

import com.kstd.project.common.domain.BaseTimeEntity;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@Table
public class UserCoupon extends BaseTimeEntity {

    @Id
    private Long id;

    private Long userId;

    private Long couponId;

	@Builder.Default
	private UserCouponStatus status = UserCouponStatus.ACTIVE;
}
