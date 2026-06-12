package com.example.First.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class EmployeeConsumer {

    @KafkaListener(topics = "employee-topic",groupId = "employee-group")
    public void consume(String message){
        System.out.println("Received: " + message);
    }
}
