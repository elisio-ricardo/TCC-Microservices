package br.com.erudio.dtos;

import java.math.BigDecimal;

import br.com.erudio.model.Cambio;
import lombok.Data;


@Data
public class CambioDTO {

    private String from;

    private String to;

    private BigDecimal conversionFactor;

    private BigDecimal convertedValue;

    
    public CambioDTO (Cambio cambio){
        this.from = cambio.getFrom();
        this.to = cambio.getTo();
        this.conversionFactor = cambio.getConversionFactor();
        this. convertedValue = cambio.getConvertedValue();
    }
    
}
