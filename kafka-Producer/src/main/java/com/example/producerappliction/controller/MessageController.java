package com.example.producerappliction.controller;

import com.example.producerappliction.dtos.MessageDTO;
import com.example.producerappliction.producer.MessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageProducer messageProducer;


    @PostMapping
    public ResponseEntity<MessageDTO> create(@RequestBody MessageDTO messageDTO){
        MessageDTO message = MessageDTO.builder().
                id(UUID.randomUUID().toString())
                .service(messageDTO.getService())
                .method(messageDTO.getMethod())
                .params(messageDTO.getParams())
                .repetitions(messageDTO.getRepetitions())
                .build();

        messageProducer.send(message);

        return ResponseEntity.ok(message);
    }



}
