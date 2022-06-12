package com.example.producerappliction.config;

import com.example.producerappliction.dtos.MessageDTO;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.security.authenticator.SaslClientAuthenticator;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;


import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {

    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;

    @Value(value = "${topic.name}")
    private String topic;

    @Bean
    public NewTopic createTopic(){
        return new NewTopic(topic, 1,(short) 1);
    }

    @Bean
    public ProducerFactory<String, Object> messageProducerFactory(){
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        configProps.put("spring.json.add.type.headers", false);
        configProps.put("spring.json.use.type.headers", false);

//        configProps.put("sasl.mechanism", "PLAIN");
//        configProps.put("security.protocol", "SASL_SSL");
//        configProps.put("sasl.jaas.config", "org.apache.kafka.common.security.plain.PlainLoginModule required username=\"$ConnectionString\" password=\"Endpoint=sb://dis-fbo-fkaso.servicebus.windows.net/;SharedAccessKeyName=messagesManager;SharedAccessKey=ofz7wtqWi0uEPAB4fEwHOuq1nNtFDF4hEGpnypDafSM=;EntityPath=messages\";");

        return new DefaultKafkaProducerFactory<>(configProps, new StringSerializer(), new JsonSerializer<>());
    }

    @Bean
    public KafkaTemplate<String, Object> messageKaTemplate(){
        return new KafkaTemplate<>(messageProducerFactory());
    }




}
