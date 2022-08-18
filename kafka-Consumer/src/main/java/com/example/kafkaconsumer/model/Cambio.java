package com.example.kafkaconsumer.model;

import java.io.Serializable;
import java.math.BigDecimal;


public class Cambio implements Serializable {

	private static final long serialVersionUID = 1L;


	private Long id;


	private String from;


	private String to;


	private BigDecimal conversionFactor;


	private BigDecimal convertedValue;

	private String environment;

	public Cambio() {
		super();
	}

	public Cambio(Long id, String from, String to, BigDecimal conversionFactor, BigDecimal convertedValue,
                  String environment) {
		super();
		this.id = id;
		this.from = from;
		this.to = to;
		this.conversionFactor = conversionFactor;
		this.convertedValue = convertedValue;
		this.environment = environment;
	}

	public Cambio(String from, String to, BigDecimal conversionFactor) {
		super();
		this.from = from;
		this.to = to;
		this.conversionFactor = conversionFactor;
		this.convertedValue = convertedValue;
		this.environment = environment;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public BigDecimal getConversionFactor() {
		return conversionFactor;
	}

	public void setConversionFactor(BigDecimal conversionFactor) {
		this.conversionFactor = conversionFactor;
	}

	public BigDecimal getConvertedValue() {
		return convertedValue;
	}

	public void setConvertedValue(BigDecimal convertedValue) {
		this.convertedValue = convertedValue;
	}

	public String getEnvironment() {
		return environment;
	}

	public void setEnvironment(String environment) {
		this.environment = environment;
	}

}
