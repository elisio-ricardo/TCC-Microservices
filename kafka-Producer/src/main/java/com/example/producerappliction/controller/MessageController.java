package com.example.producerappliction.controller;

import com.example.producerappliction.dtos.MessageDTO;
import com.example.producerappliction.enums.TypeOfRequest;
import com.example.producerappliction.producer.MessageProducer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;

import java.util.UUID;

@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageProducer messageProducer;


    @PostMapping
    public ResponseEntity<MessageDTO> creaate(@RequestBody MessageDTO messageDTO){
        MessageDTO message = MessageDTO.builder().
                                        id(UUID.randomUUID().toString())
                .type(messageDTO.getType()).service(messageDTO.getService()).build();

        messageProducer.send(message);

        return ResponseEntity.ok(message);
    }



}
