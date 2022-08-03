package com.bootcamp.currency.service;

import com.bootcamp.currency.domain.Currency;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ICurrencyService {
    Flux<Currency> findAll();

    Mono<Currency> findById(String id);

    Mono<Currency> save(Currency currency);

    Mono<Currency> update(String id, Currency currency);

    Mono<Void> deleteById(String id);
}
