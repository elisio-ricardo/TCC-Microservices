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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public ServiceRequested getService() {
		return service;
	}
	public void setService(ServiceRequested service) {
		this.service = service;
	}
	public TypeOfRequest getType() {
		return type;
	}
	public void setType(TypeOfRequest type) {
		this.type = type;
	}
    
    

}

