package com.bootcamp.currency.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bootcamp.currency.domain.Currency;
import com.bootcamp.currency.service.ICurrencyService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/currency")
public class CurrencyController {
	@Autowired
    private ICurrencyService currencyTypeService;

    @GetMapping
    public Flux<Currency> findAll(){
        return currencyTypeService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Currency> getById(@PathVariable("id") String id){return  currencyTypeService.findById(id);
    }

    @PostMapping
    public Mono<Currency> create(@RequestBody Currency currency){
        return currencyTypeService.save(currency);
    }

    @PutMapping("/{id}")
    public Mono<Currency> update(@PathVariable String id, @RequestBody Currency currency){
        return currencyTypeService.update(id, currency);
    }

    @DeleteMapping
    public Mono<Void> deleteById(@PathVariable("id") String id){
        return currencyTypeService.deleteById(id);
    }
}
