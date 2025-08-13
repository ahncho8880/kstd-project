package com.kstd.project.coin.service.impl;

import com.kstd.project.coin.repository.CoinRepository;
import com.kstd.project.coin.service.CoinService;
import com.kstd.project.coin.service.dto.CoinDto;
import com.kstd.project.coin.service.dto.CoinRequestDto;
import com.kstd.project.common.exception.KstdException;
import com.kstd.project.user.service.UserService;
import lombok.RequiredArgsConstructor;

import org.reactivestreams.Publisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.reactive.TransactionalOperator;

import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

@Service
public class CoinServiceImpl implements CoinService {

    private final CoinRepository coinRepository;

    private final UserService userService;

	private final Sinks.Many<CoinRequestDto> coinApplySink;

	private final TransactionalOperator txOperator;

	public CoinServiceImpl(CoinRepository coinRepository, UserService userService, TransactionalOperator txOperator) {
		this.coinRepository = coinRepository;
		this.userService = userService;
		this.txOperator = txOperator;

		// 코인응모의 경우 큐에 들어온 요청을 순차적으로 처리, 실질적인 로직은 processCoinApply 메소드에서 처리
		coinApplySink = Sinks.many().unicast().onBackpressureBuffer();
		coinApplySink.asFlux()
			.concatMap(this::processCoinApply)
			.subscribe();
	}

	private Mono<CoinDto> processCoinApply(CoinRequestDto coinRequestDto) {
		return coinRepository.findById(coinRequestDto.getCoinId())
			.switchIfEmpty(Mono.error(new KstdException("코인을 찾을수 없습니다.")))
			.flatMap(coin -> {
				// 코인 잔액 확인
				if(coin.getRemainingAmount() <= 0) {
					return Mono.error(new KstdException("코인이 부족합니다."));
				} else {
					// 사용자에게 코인 추가
					coin.setRemainingAmount(coin.getRemainingAmount() - 1);
					return coinRepository.save(coin)
						.as(txOperator::transactional)
						.flatMap(savedCoin -> userService.addUserCoin(coinRequestDto.getUserId(), 1, coin.getLimitedAmount()))
						.map(CoinDto::of);
				}
			})
			.doOnNext(coinRequestDto::success)
			.onErrorResume(e -> {
				// 에러 발생 시 sink에 에러 전달
				coinRequestDto.getSink().tryEmitError(e);

				// 예외를 sink 종료 없이 처리
				return Mono.empty();
			});
	}

	@Override
    public Mono<CoinDto> reportCoin(Long coinId) {
        return coinRepository.findById(coinId)
            .zipWith(userService.getAllUserCoin().collectList())
            .map(CoinDto::of);
    }


	@Override
	public Mono<CoinDto> applyCoin(CoinRequestDto coinRequestDto) {
		Sinks.EmitResult result = coinApplySink.tryEmitNext(coinRequestDto);
		if (result.isFailure()) {
			return Mono.error(new RuntimeException("큐에 추가 실패"));
		}
		return coinRequestDto.getSink().asMono();
	}
}
