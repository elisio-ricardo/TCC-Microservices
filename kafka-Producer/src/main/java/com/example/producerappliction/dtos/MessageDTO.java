package com.example.producerappliction.dtos;

import com.example.producerappliction.enums.MethodRequested;
import com.example.producerappliction.enums.ServiceRequested;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MessageDTO {

    private String id;
    private ServiceRequested service;
    private MethodRequested method;
    private List<Object> params;
    private Integer repetitions;

}
