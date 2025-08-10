package com.kstd.project.common.handler;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.kstd.project.common.exception.KstdException;

import reactor.core.publisher.Mono;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(KstdException.class)
	public Mono<ResponseEntity<Map<String, String>>> handleKstdException(KstdException ex) {
		return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST)
			.contentType(MediaType.APPLICATION_JSON)
			.body(Map.of("errorMessage", ex.getMessage())));
	}
}
