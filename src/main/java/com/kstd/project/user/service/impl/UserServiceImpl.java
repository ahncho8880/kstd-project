package com.kstd.project.user.service.impl;

import java.util.List;

import com.kstd.project.common.exception.KstdException;
import com.kstd.project.coupon.domain.CouponType;
import com.kstd.project.coupon.repository.CouponRepository;
import com.kstd.project.coupon.service.dto.CouponDto;
import com.kstd.project.user.domain.UserCoupon;
import com.kstd.project.user.domain.UserCouponStatus;
import com.kstd.project.user.repository.UserRepository;
import com.kstd.project.user.repository.UserCouponRepository;
import com.kstd.project.user.service.UserService;
import com.kstd.project.user.service.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.reactive.TransactionalOperator;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

	private final CouponRepository couponRepository;

    private final UserRepository userRepository;

    private final UserCouponRepository userCouponRepository;

	private final TransactionalOperator txOperator;

    @Override
    public Flux<UserDto> getAllUserCoin() {
        return userRepository.findAll()
            .map(UserDto::of);
    }

    @Override
    public Mono<UserDto> getUser(Long userId) {
        return userRepository.findById(userId)
			.switchIfEmpty(Mono.error(new KstdException("사용자를 찾을 수 없습니다.")))
			.map(UserDto::of);
    }

    @Override
    public Mono<UserDto> updateUserCoin(long userId, int requiredCoins) {
        return userRepository.findById(userId)
            .flatMap(user -> {
                user.setCoinAmount(user.getCoinAmount() - requiredCoins);
                return userRepository.save(user);
            })
            .map(UserDto::of);
    }

    @Override
    public Mono<UserDto> saveUserCoupon(Long userId, Long couponId) {
        return userCouponRepository.save(UserCoupon.builder().userId(userId).couponId(couponId).build())
            .map(UserDto::of);
    }

    @Override
    public Mono<UserDto> getUserCoupon(Long userCouponId, Long userId) {
        return userCouponRepository.findByIdAndUserIdAndStatus(userCouponId, userId, UserCouponStatus.ACTIVE)
            .map(UserDto::of)
            .switchIfEmpty(Mono.error(new KstdException("쿠폰을 찾을 수 없습니다.")));
    }

	@Override
	public Mono<UserDto> deleteUserCoupon(Long userCouponId) {
		return userCouponRepository.findById(userCouponId)
			.switchIfEmpty(Mono.error(new KstdException("쿠폰 응모가 존재하지 않습니다.")))
			.flatMap(userCoupon -> {
				userCoupon.setStatus(UserCouponStatus.INACTIVE);
				return userCouponRepository.save(userCoupon);
			})
			.map(UserDto::of);
	}

	@Override
	public Mono<List<UserDto>> getUserCoupon(Long userId) {
		return couponRepository.findAll().map(CouponDto::of).collectList()
				.flatMap(couponDtoList -> userCouponRepository.findByUserId(userId)
					.map(userCoupon -> {
						CouponType couponType =  couponDtoList.stream()
							.filter(couponDto -> couponDto.getCouponId().equals(userCoupon.getCouponId()))
							.findFirst()
							.map(CouponDto::getType)
							.orElseThrow(() -> new KstdException("쿠폰 타입을 찾을 수 없습니다."));

						return UserDto.builder()
							.userCouponId(userCoupon.getId())
							.couponId(userCoupon.getCouponId())
							.userId(userCoupon.getUserId())
							.userCouponStatus(userCoupon.getStatus())
							.couponType(couponType)
							.build();
					})
					.collectList()
				);
	}

	@Override
	public Flux<UserDto> getUserCouponByCouponId(Long couponId) {
		return userCouponRepository.findByCouponId(couponId)
			.map(userCoupon -> UserDto.builder()
				.userCouponId(userCoupon.getId())
				.couponId(userCoupon.getCouponId())
				.userId(userCoupon.getUserId())
				.userCouponStatus(userCoupon.getStatus())
				.build());
	}

	@Override
	public Mono<UserDto> addUserCoin(Long userId, Integer amount, Integer limit) {
		return userRepository.findById(userId)
			.switchIfEmpty(Mono.error(new KstdException("사용자를 찾을 수 없습니다.")))
			.flatMap(user -> {
				int userCoins = user.getCoinAmount() + amount;
				if (userCoins > limit) {
					return Mono.error(new KstdException("가질수 있는 코인 초과."));
				}
				user.setCoinAmount(userCoins);
				return userRepository.save(user)
					.map(UserDto::of)
					.as(txOperator::transactional);
			});
	}
}
