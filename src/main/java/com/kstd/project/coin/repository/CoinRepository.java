package com.kstd.project.coin.repository;

import com.kstd.project.coin.domain.Coin;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface CoinRepository extends ReactiveCrudRepository<Coin, Long> {

}
