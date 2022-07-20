package com.example.kafkaconsumer.dto;

import com.example.kafkaconsumer.dto.enums.MethodRequested;
import com.example.kafkaconsumer.dto.enums.ServiceRequested;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class MessageConsumerDTO {

    private String id;
    private ServiceRequested service;
    private MethodRequested method;
    private List<Object> params;

}

