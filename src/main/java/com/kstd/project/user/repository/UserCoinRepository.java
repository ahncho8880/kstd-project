package com.kstd.project.user.repository;

import com.kstd.project.user.domain.UserCoin;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface UserCoinRepository extends ReactiveCrudRepository<UserCoin, Long> {

}
