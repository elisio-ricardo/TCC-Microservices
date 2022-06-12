package com.example.producerappliction.dtos;

import com.example.producerappliction.enums.ServiceRequested;
import com.example.producerappliction.enums.TypeOfRequest;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MessageDTO {

    private String id;
    private ServiceRequested service;
    private TypeOfRequest type;

}
