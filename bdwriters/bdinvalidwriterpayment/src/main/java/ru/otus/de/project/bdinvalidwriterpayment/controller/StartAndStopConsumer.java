package ru.otus.de.project.bdinvalidwriterpayment.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.de.project.bdinvalidwriterpayment.consumer.ConsumerPayment;

@RestController
public class StartAndStopConsumer {

    private ConsumerPayment consumerPayment;

    @Autowired
    public StartAndStopConsumer(ConsumerPayment consumerPayment) {
        this.consumerPayment = consumerPayment;
    }

    @RequestMapping("/start")
    public ResponseEntity<String> startConsumer() {
        consumerPayment.getMessage(true);
        return ResponseEntity.ok("Consumer is start!");
    }

    @RequestMapping("/stop")
    public ResponseEntity<String> stopConsumer() {
        consumerPayment.getMessage(false);
        return ResponseEntity.ok("Consumer is stop!");
    }
}
