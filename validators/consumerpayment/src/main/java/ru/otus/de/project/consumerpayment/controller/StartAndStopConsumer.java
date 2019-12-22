package ru.otus.de.project.consumerpayment.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.de.project.consumerpayment.consumer.ConsumerPayment;

@RestController
public class StartAndStopConsumer {

    private ConsumerPayment consumerPayment;

    @Autowired
    public StartAndStopConsumer(ConsumerPayment consumerSms) {
        this.consumerPayment = consumerSms;
    }

    @RequestMapping("/start")
    public ResponseEntity<String> startConsumer() {
        return ResponseEntity.ok(
                consumerPayment.getMessage(true)
        );
    }

    @RequestMapping("/stop")
    public ResponseEntity<String> stopConsumer() {
        return ResponseEntity.ok(
                consumerPayment.getMessage(false)
        );
    }
}