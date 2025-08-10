package com.kstd.project.coupon.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.reactive.TransactionalOperator;

import com.kstd.project.common.exception.KstdException;
import com.kstd.project.coupon.domain.CouponType;
import com.kstd.project.coupon.repository.CouponRepository;
import com.kstd.project.coupon.service.CouponService;
import com.kstd.project.coupon.service.dto.CouponDto;
import com.kstd.project.user.domain.UserCouponStatus;
import com.kstd.project.user.service.UserService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuples;

@Service
@RequiredArgsConstructor
public class CouponServiceImpl implements CouponService {

	private final CouponRepository couponRepository;

	private final UserService userService;

	private final TransactionalOperator txOperator;

	@Override
	public Mono<CouponDto> applyCoupon(Long couponId, Long userId) {
		return couponRepository.findById(couponId)
			.zipWith(userService.getUser(userId))
			.filter(tuple2 -> tuple2.getT1().getRequiredCoins() <= tuple2.getT2().getUserCoins())
			.switchIfEmpty(Mono.error(new KstdException("쿠폰 응모에 실패했습니다. 충분한 코인이 없습니다.")))
			.flatMap(tuple2 -> userService.saveUserCoupon(tuple2.getT2().getUserId(), tuple2.getT1().getId())
				.flatMap(savedUserDto ->
					userService.updateUserCoin(tuple2.getT2().getUserId(), tuple2.getT1().getRequiredCoins())
						.map(userDto -> Tuples.of(userDto, savedUserDto))
				)
				// .flatMap(userDto -> userService.updateUserCoin(tuple2.getT2().getUserId(), tuple2.getT1().getRequiredCoins()))
				.map(tuple -> {
					tuple.getT1().setUserCouponId(tuple.getT2().getUserCouponId());
					return tuple.getT1();

				})
			)
			.map(userCoinDto -> CouponDto.builder()
				.userId(userId)
				.userCouponId(userCoinDto.getUserCouponId())
				.couponId(couponId)
				.userCoins(userCoinDto.getUserCoins())
				.build()
			)
			.as(txOperator::transactional);
	}

	@Override
	public Mono<CouponDto> cancelCoupon(Long userCouponId, Long userId) {
		return userService.getUserCoupon(userCouponId, userId)
			.switchIfEmpty(Mono.error(new KstdException("해당 쿠폰 응모가 존재하지 않습니다.")))
			.flatMap(userDto -> couponRepository.findById(userDto.getCouponId())
				.flatMap(coupon -> userService.updateUserCoin(userId, -coupon.getRequiredCoins())
						.map(userDto1 -> {
							userDto.setUserCoins(userDto1.getUserCoins());
							return userDto;
						})
				)
				.then(userService.deleteUserCoupon(userCouponId))
				.thenReturn(userDto))
			.map(CouponDto::of)
			.as(txOperator::transactional);
	}

	@Override
	public Mono<CouponDto> getAllUserCoupon(CouponType couponType) {
		return couponRepository.findByType(couponType)
			.switchIfEmpty(Mono.error(new KstdException("해당 쿠폰이 존재하지 않습니다.")))
			.flatMap(coupon -> userService.getUserCouponByCouponId(coupon.getId()).collectList()
				.map(userCoupons -> CouponDto.builder()
					.couponId(coupon.getId())
					.type(coupon.getType())
					.totalApplications(userCoupons.size())
					.appliedApplications(userCoupons.stream().filter(uc -> UserCouponStatus.ACTIVE.equals(uc.getUserCouponStatus())).count())
					.cancelledApplications(userCoupons.stream().filter(uc -> UserCouponStatus.INACTIVE.equals(uc.getUserCouponStatus())).count())
					.build()
				));
	}
}
