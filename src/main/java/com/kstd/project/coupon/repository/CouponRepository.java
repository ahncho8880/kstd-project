package com.kstd.project.coupon.repository;

import com.kstd.project.coupon.domain.Coupon;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface CouponRepository extends ReactiveCrudRepository<Coupon, Long> {

}
