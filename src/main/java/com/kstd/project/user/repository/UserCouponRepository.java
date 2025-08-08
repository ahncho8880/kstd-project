package com.kstd.project.user.repository;

import com.kstd.project.user.domain.UserCoupon;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface UserCouponRepository extends ReactiveCrudRepository<UserCoupon, Long> {

}
