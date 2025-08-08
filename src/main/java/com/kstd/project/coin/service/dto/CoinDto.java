package com.kstd.project.coin.service.dto;

import com.kstd.project.coin.domain.Coin;
import com.kstd.project.user.domain.UserCoin;
import com.kstd.project.user.service.dto.UserCoinDto;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import reactor.util.function.Tuple2;

@Getter
@Setter
@Builder
public class CoinDto {

    private int remainingAmount;

    private List<UserCoinDto> userCoinDtoList;

    public static CoinDto of(Coin coin) {
        return CoinDto.builder().build();
    }

    public static CoinDto of(Tuple2<Coin, List<UserCoinDto>> tuple2) {
        return CoinDto.builder()
            .remainingAmount(tuple2.getT1().getRemainingAmount())
            .userCoinDtoList(tuple2.getT2())
            .build();
    }
}
