package br.com.erudio.configuration;

import br.com.erudio.dtos.MessageConsumerDTO;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {

    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;

    @Value(value = "${spring.kafka.consumer.group-id}")
    private String groupID;


    @Bean
    public ConsumerFactory<String, MessageConsumerDTO> messageConsumerFactory(){
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        configProps.put(ConsumerConfig.GROUP_ID_CONFIG, groupID);
        configProps.put("sasl.jaas.config", "org.apache.kafka.common.security.plain.PlainLoginModule required username=\"$ConnectionString\" password=\"Endpoint=sb://dis-fbo-fkaso.servicebus.windows.net/;SharedAccessKeyName=log-send;SharedAccessKey=YX+vzVqExH7GFFz5l0hV0+vpCPb7RnhbVco/7R9gil4=;EntityPath=logs\";");

        return new DefaultKafkaConsumerFactory<>(configProps, new StringDeserializer(), new JsonDeserializer<>(MessageConsumerDTO.class, false));
    }


    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, MessageConsumerDTO> messageDTOConcurrentKafkaListenerContainerFactory(){
        ConcurrentKafkaListenerContainerFactory<String, MessageConsumerDTO> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(messageConsumerFactory());
        return factory;
    }

}
