package br.com.erudio.dtos;


import br.com.erudio.dtos.enums.ServiceRequested;
import br.com.erudio.dtos.enums.TypeOfRequest;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MessageConsumerDTO {

    private String id;
    private ServiceRequested service;
    private TypeOfRequest type;

}

