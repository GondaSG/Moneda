package com.bootcamp.currency.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.bootcamp.currency.domain.Currency;
@Repository
public interface ICurrencyRepository extends ReactiveCrudRepository<Currency,String>{

}
