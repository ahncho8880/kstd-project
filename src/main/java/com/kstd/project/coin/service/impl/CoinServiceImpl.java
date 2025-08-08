package com.kstd.project.coin.service.impl;

import com.kstd.project.coin.repository.CoinRepository;
import com.kstd.project.coin.service.CoinService;
import com.kstd.project.coin.service.dto.CoinDto;
import com.kstd.project.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class CoinServiceImpl implements CoinService {

    private final CoinRepository coinRepository;

    private final UserService userService;

    @Override
    public Mono<CoinDto> reportCoin(Long coinId) {
        return coinRepository.findById(coinId)
            .zipWith(userService.getAllUserCoin().collectList())
            .map(CoinDto::of);
    }

    @Override
    public Mono<CoinDto> getCoin(Long coinId) {
        return coinRepository.findById(coinId)
            .map(CoinDto::of);
    }
}
