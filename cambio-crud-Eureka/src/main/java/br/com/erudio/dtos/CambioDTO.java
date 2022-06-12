package br.com.erudio.dtos;

import br.com.erudio.model.Cambio;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Transient;
import java.math.BigDecimal;

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
