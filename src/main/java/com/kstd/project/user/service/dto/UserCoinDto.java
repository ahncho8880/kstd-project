package com.kstd.project.user.service.dto;

import com.kstd.project.user.domain.UserCoin;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserCoinDto {

    private Long userId;

    private Integer userCoins;

    public static UserCoinDto of(UserCoin userCoin) {
        return new UserCoinDto(userCoin.getUserId(), userCoin.getCoinAmount());
    }
}