package com.kstd.project.coin.service;

import com.kstd.project.coin.service.dto.CoinDto;
import com.kstd.project.coin.service.dto.CoinRequestDto;

import reactor.core.publisher.Mono;

public interface CoinService {

    Mono<CoinDto> reportCoin(Long coinId);

	Mono<CoinDto> applyCoin(CoinRequestDto coinRequestDto);
}
