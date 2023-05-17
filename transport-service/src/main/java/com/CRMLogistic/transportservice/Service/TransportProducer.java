package com.CRMLogistic.transportservice.Service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class TransportProducer {

    private KafkaTemplate<String, String> kafkaTemplate;

    public TransportProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }


    public void sendMessage(String message){
        kafkaTemplate.send("transport",message);
    }
}
