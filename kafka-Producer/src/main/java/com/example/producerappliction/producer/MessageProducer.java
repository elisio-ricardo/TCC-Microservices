package com.example.producerappliction.producer;

import com.example.producerappliction.dtos.MessageDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.method.HandlerMethod;

@Service
public class MessageProducer {

    private static final Logger logger = LoggerFactory.getLogger(MessageProducer.class);

    private final String topic;
    private final KafkaTemplate<String, Object> kafkaTemplate;


    @Autowired
    private ObjectMapper mapper;

    public MessageProducer( @Value("${topic.name}") String topic, KafkaTemplate<String, Object> kafkaTemplate) {
        this.topic = topic;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(MessageDTO messageDTO){
        kafkaTemplate.send(topic, messageDTO).addCallback(
                success -> {logger.info("Messsage sent " + success.getProducerRecord().value());
                            logger.info("Partition: " + success.getProducerRecord().partition());},
                failure -> logger.info("Message failed " + failure.getMessage())
        );
    }

    private String toJson(Object obj) {
        try {
            if (obj instanceof HandlerMethod) {
                HandlerMethod handlerMethod = (HandlerMethod) obj;
                return mapper.writeValueAsString(handlerMethod.getMethod());
            }
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            System.out.println(e+ "When converting obj to JSON in LogUtils.");

        }
        return "";
    }
}
