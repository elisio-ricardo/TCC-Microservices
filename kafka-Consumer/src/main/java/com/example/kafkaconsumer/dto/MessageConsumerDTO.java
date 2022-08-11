package com.example.kafkaconsumer.dto;

import com.example.kafkaconsumer.dto.enums.ServiceRequested;
import com.example.kafkaconsumer.dto.enums.TypeOfRequest;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MessageConsumerDTO {

    private String id;
    private ServiceRequested service;
    private TypeOfRequest type;

}

