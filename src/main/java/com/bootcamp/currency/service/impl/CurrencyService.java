package com.bootcamp.currency.service.impl;

import java.util.function.BiFunction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Service;

import com.bootcamp.currency.domain.Currency;
import com.bootcamp.currency.repository.ICurrencyRepository;
import com.bootcamp.currency.service.ICurrencyService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CurrencyService implements ICurrencyService {
	private final String KEY_CACHE = "currency";
	@Autowired
	private final ICurrencyRepository iCurrencyRepository; 
	private final ReactiveRedisOperations<String,Currency> operations;
	
	BiFunction<String,String,String> cont = (clave,id) -> { return clave + id;};
	
	@Override
	public Flux<Currency> findAll() {
		return iCurrencyRepository.findAll();
	}

	@Override
	public Mono<Currency> findById(String id) {
		return operations.opsForValue()
				.get(cont.apply(KEY_CACHE, id ))
				.map(_currency -> (Currency)_currency)
				.switchIfEmpty(iCurrencyRepository.findById(id));
	}

	@Override
	public Mono<Currency> save(Currency currency) {
		return iCurrencyRepository.save(currency);
	}

	@Override
	public Mono<Currency> update(String id, Currency currency) {
		// iCurrencyRepository.save(currency);
		Mono<Currency> currencySave = iCurrencyRepository.save(currency);
		return operations.opsForValue()
					.set(cont.apply(KEY_CACHE, id ), currency)
					.map(__-> currency);
	}

	@Override
	public Mono<Void> deleteById(String id) {
		return iCurrencyRepository.deleteById(id);
	}

}
