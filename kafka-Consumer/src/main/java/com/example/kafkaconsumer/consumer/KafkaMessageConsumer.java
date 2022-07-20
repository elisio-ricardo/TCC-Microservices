package com.example.kafkaconsumer.consumer;


import com.example.kafkaconsumer.dto.BookDTO;
import com.example.kafkaconsumer.dto.MessageConsumerDTO;
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
//        log.info("Received message partition: "+record.partition());
        log.info("Received message: "+record.value());
        processMessage(record.value());
//        Object valor = record.value();
//        MessageConsumerDTO mensagem = (MessageConsumerDTO) valor;
//        log.info("Received message: "+((MessageConsumerDTO) record.value()).getType());
//        log.info("SEGUNDA MENSAGEM NESSE CARALHO: "+mensagem.getSecondMessage());
    }

    private void processMessage(MessageConsumerDTO message){

        if(message.getService() == ServiceRequested.BOOK){
            processBookRequest(message);
        }else if(message.getService() == ServiceRequested.CAMBIO){
            processCambioRequest(message);
        }

//        Long start = System.nanoTime();
//        log.info("Initializing ProcessMessage");
//        System.out.println(message.toString());
//        Long end = System.nanoTime();
//        System.out.println("Tempo passado no metodo :" + (end-start) + "nanossegundos");
//        System.out.println("Tempo passado no metodo segundos :" + (end-start)*(Math.pow(10, -9)) + "s");

    }

    private void processBookRequest(MessageConsumerDTO message){
        switch (message.getType()){
            case GET:
                //todo something
                Long start = System.nanoTime();
                ResponseEntity<List<BookDTO>> all = bookProxy.findAll();
                Long end = System.nanoTime();
                log.info("MICROSSERVICE - Tempo passado no metodo :" + (end-start) + " nanossegundos");
//                System.out.println("Tempo passado no metodo segundos :" + (end-start)*(Math.pow(10, -9)) + "s");
//
//                System.out.println("------------");
//                System.out.println("Agora dentro do processo");
//                System.out.println("------------");
//
//                bookProxy.findAllTimeProcessed();

                break;
            case PUT:
                //todo something
                System.out.println("PUT");
                break;
            case POST:
                //todo something]
                System.out.println("POST");
                break;
            case DELETE:
                //todo something
                System.out.println("DELETE");
                break;
            default:
                System.out.println("Erro nao encontrado");
        }
    }

    private void processCambioRequest(MessageConsumerDTO messageCambio){
        switch (messageCambio.getType()){
            case GET:
                //todo something
                System.out.println("GET");

                Long start = System.nanoTime();
                ResponseEntity<List<Cambio>> all = cambioProxy.findAll();
                Long end = System.nanoTime();
                log.info("Tempo passado no metodo :" + (end-start) + " nanossegundos");
                break;
            case PUT:
                //todo something
                System.out.println("PUT");
                break;
            case POST:
                //todo something]
                System.out.println("POST");
                break;
            case DELETE:
                //todo something
                System.out.println("DELETE");
                break;
            default:
                System.out.println("Erro nao encontrado");
        }
    }

}
