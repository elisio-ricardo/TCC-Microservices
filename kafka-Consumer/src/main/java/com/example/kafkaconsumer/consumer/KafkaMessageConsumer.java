package com.example.kafkaconsumer.consumer;


import com.example.kafkaconsumer.dto.BookDTO;
import com.example.kafkaconsumer.dto.MessageConsumerDTO;
import com.example.kafkaconsumer.dto.enums.MethodRequested;
import com.example.kafkaconsumer.dto.enums.ServiceRequested;
import com.example.kafkaconsumer.model.Cambio;
import com.example.kafkaconsumer.proxy.BookProxy;
import com.example.kafkaconsumer.proxy.CambioProxy;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class KafkaMessageConsumer {

    @Value(value = "${spring.kafka.consumer.group-id}")
    private String groupID;

    @Value(value = "${topic.name}")
    private String topic;

    @Autowired
    private BookProxy bookProxy;

    @Autowired
    private CambioProxy cambioProxy;

    private static final Logger log = LoggerFactory.getLogger(KafkaMessageConsumer.class);

    @KafkaListener(topics = "${topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void listenTopicLogs(ConsumerRecord<String, MessageConsumerDTO> record){
        log.info("Received message: "+record.value());
        processMessage(record.value());
    }

    private void processMessage(MessageConsumerDTO message){

        if(message.getService() == ServiceRequested.BOOK){
            processBookRequest(message);
        }else if(message.getService() == ServiceRequested.CAMBIO){
            processCambioRequest(message);
        }

    }

    private void processBookRequest(MessageConsumerDTO message){
        switch (message.getMethod()){
            case GET_BOOK:
                //todo something

                break;
            case FIND_BOOK_BY_ID:
                break;
            case FIND_ALL_BOOK:
                Long start = System.nanoTime();
                ResponseEntity<List<BookDTO>> all = bookProxy.findAll();
                Long end = System.nanoTime();
                log.info("MICROSSERVICE \t Method requested {} \t Time elapsed {} nanossegundos", message.getMethod(), (end-start));
//                log.info("MICROSSERVICE - Tempo passado no metodo :" + (end-start) + " nanossegundos");
                break;
            case CREATE_BOOK:
                break;
            case UPDATE_BOOK:
                break;
            case DELETE_BOOK:
                break;

            default:
                System.out.println("Erro nao encontrado");
        }
    }

    private void processCambioRequest(MessageConsumerDTO message){
        switch (message.getMethod()){
            case GET_CAMBIO:
                break;
            case FIND_CAMBIO_BY_ID:
                break;
            case FIND_ALL_CAMBIO:
                //todo something
                Long start = System.nanoTime();
                ResponseEntity<List<Cambio>> all = cambioProxy.findAll();
                Long end = System.nanoTime();
                log.info("MICROSSERVICE \t Method requested {} \t Time elapsed {} nanossegundos", message.getMethod(), (end-start));
//                log.info("Tempo passado no metodo :" + (end-start) + " nanossegundos");
                break;
            case CREATE_CAMBIO:
                break;
            case UPDATE_CAMBIO:
                break;
            case DELETE_CAMBIO:
                break;

            default:
                System.out.println("Erro nao encontrado");
        }
    }

}
