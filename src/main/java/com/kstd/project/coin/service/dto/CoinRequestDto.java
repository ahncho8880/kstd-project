package com.kstd.project.coin.service.dto;

import lombok.Getter;
import reactor.core.publisher.Mono;
import reactor.core.publisher.MonoSink;
import reactor.core.publisher.Sinks;

@Getter
public class CoinRequestDto {

	private final Long userId;

	private final Long coinId;

	private final Sinks.One<CoinDto> sink = Sinks.one();

	public CoinRequestDto(Long userId, Long coinId) {
		this.userId = userId;
		this.coinId = coinId;
	}

	public void success(CoinDto coinDto) {
		sink.tryEmitValue(coinDto);
	}
}
