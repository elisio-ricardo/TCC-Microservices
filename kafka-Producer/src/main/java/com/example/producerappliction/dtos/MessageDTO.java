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
    
    
    
    
	public MessageDTO(String id, ServiceRequested service, TypeOfRequest type) {
		super();
		this.id = id;
		this.service = service;
		this.type = type;
	}
	
	
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
