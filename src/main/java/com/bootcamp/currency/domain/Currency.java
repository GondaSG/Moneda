package com.bootcamp.currency.domain;

import java.sql.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
@Document("currency")
public class Currency {

	@Id
	private String id;
	private String name;
	private String currencyTypeId;
	private double pricePurchase;
	private double priceSale;
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
	private String registrationDate;

    private static final long serialVersionUID = 1L;
}
