package com.kstd.project.user.service.impl;

import com.kstd.project.coin.service.CoinService;
import com.kstd.project.user.repository.UserCoinRepository;
import com.kstd.project.user.service.UserService;
import com.kstd.project.user.service.dto.UserCoinDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserCoinRepository userCoinRepository;

    @Override
    public Flux<UserCoinDto> getAllUserCoin() {
        return userCoinRepository.findAll()
            .map(UserCoinDto::of);
    }
}
