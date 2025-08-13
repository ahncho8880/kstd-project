package com.kstd.project.coin.service.dto;

import com.kstd.project.coin.domain.Coin;
import com.kstd.project.user.service.dto.UserDto;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import reactor.util.function.Tuple2;

@Getter
@Setter
@Builder
public class CoinDto {

	private Long userId;

	private Integer userCoins;

    private int remainingAmount;

    private List<UserDto> userDtoList;

    public static CoinDto of(Tuple2<Coin, List<UserDto>> tuple2) {
        return CoinDto.builder()
            .remainingAmount(tuple2.getT1().getRemainingAmount())
            .userDtoList(tuple2.getT2())
            .build();
    }

	public static CoinDto of(UserDto userDto) {
		return CoinDto.builder().userId(userDto.getUserId()).userCoins(userDto.getUserCoins()).build();
	}
}
