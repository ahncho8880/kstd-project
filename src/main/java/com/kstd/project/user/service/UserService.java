package com.kstd.project.user.service;

import com.kstd.project.user.service.dto.UserCoinDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {

    Flux<UserCoinDto> getAllUserCoin();
}
