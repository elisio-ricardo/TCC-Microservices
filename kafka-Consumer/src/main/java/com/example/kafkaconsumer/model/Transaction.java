package com.example.kafkaconsumer.model;

import com.example.kafkaconsumer.dto.enums.MethodRequested;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "transaction")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String initialTime;

    private String endTime;

    private String timeLapsed;

    private Integer level;

    private Integer repetitions;

    private String method;
}
