package com.example.First.controller;

import com.example.First.kafka.EmployeeProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/kafka")
public class KafkaController {

    private final EmployeeProducer producer;

    @PostMapping("/send")
    public String sendMessage(@RequestParam String message){
        producer.sendMessage(message);
        return "message sent successfully";
    }

}
