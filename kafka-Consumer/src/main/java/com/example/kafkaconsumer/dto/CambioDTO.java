package com.example.kafkaconsumer.dto;

import com.example.kafkaconsumer.model.Cambio;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CambioDTO {

    private String from;

    private String to;

    private BigDecimal conversionFactor;

    private BigDecimal convertedValue;

    public CambioDTO(Cambio cambio){
        this.from = cambio.getFrom();
        this.to = cambio.getTo();
        this.conversionFactor = cambio.getConversionFactor();
        this. convertedValue = cambio.getConvertedValue();
    }
}
