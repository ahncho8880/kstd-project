package com.kstd.project.coin.service;

import com.kstd.project.coin.controller.CoinController.CoinReportResponse;
import com.kstd.project.coin.service.dto.CoinDto;
import reactor.core.publisher.Mono;

public interface CoinService {

    Mono<CoinDto> reportCoin(Long coinId);

    Mono<CoinDto> getCoin(Long coinId);
}
