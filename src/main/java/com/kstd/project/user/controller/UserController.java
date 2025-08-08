package com.kstd.project.user.controller;

import com.kstd.project.coupon.controller.CouponController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@Tag(name = "사용자", description = "사용자 관련 api")
public class UserController {

    @GetMapping("/user/coupon")
    @Operation(summary = "사용자 응모 현황 조회", description = "특정 사용자의 응모 현황을 조회한다")
    public Mono<UserCouponResponse> userCoupon() {
        return Mono.empty();
    }

    @GetMapping("/user/coin")
    @Operation(summary = "사용자 응모 코인 수량 조회", description = "사용자가 가진 응모 코인 수량 조회한다")
    public Mono<UserCoinResponse> userCoin() {
        return Mono.empty();
    }

    public record UserCouponResponse() {}
    public record UserCoinResponse() {}
}
