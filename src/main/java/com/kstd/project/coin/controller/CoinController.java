package com.kstd.project.coin.controller;

import com.kstd.project.coin.service.CoinService;
import com.kstd.project.coin.service.dto.CoinDto;
import com.kstd.project.user.controller.UserController;
import com.kstd.project.user.service.dto.UserDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
@Tag(name = "응모 코인", description = "응모 코인 쿠폰 관련 api")
public class CoinController {

    private final CoinService coinService;

    @PostMapping("/coins/apply")
    @Operation(summary = "응모 코인 획득", description = "응모 코인을 획득 요청한다")
    public Mono<CoinApplyResponse> apply() {
        return Mono.empty();
    }

    @GetMapping("/coins/{coinId}/report")
    @Operation(summary = "전체 응모 코인 현황 조회", description = "남은 응모 코인 수와 사용자별 획득한 응모 코인 수량을 조회한다")
    public Mono<CoinReportResponse> report(@PathVariable(value = "coinId") Long coinId) {
        return coinService.reportCoin(coinId)
            .map(CoinReportResponse::of);
    }

    public record CoinApplyResponse() {}

    public record CoinReportResponse(Integer totalRemainingCoins, List<UserCoinReportResponse> users) {
        private static CoinReportResponse of(CoinDto coinDto) {
            return new CoinReportResponse(coinDto.getRemainingAmount(), UserCoinReportResponse.of(coinDto.getUserDtoList()));
        }

		record UserCoinReportResponse(Long userId, Integer coinAmount) {
			public static List<UserCoinReportResponse> of(List<UserDto> userDtoList) {
				return userDtoList.stream()
					.map(userDto -> new UserCoinReportResponse(userDto.getUserId(), userDto.getUserCoins()))
					.toList();
			}
		}
    }
}
