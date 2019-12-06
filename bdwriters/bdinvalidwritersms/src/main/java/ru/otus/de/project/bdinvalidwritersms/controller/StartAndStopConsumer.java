package ru.otus.de.project.bdinvalidwritersms.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.de.project.bdinvalidwritersms.consumer.ConsumerSms;

@RestController
public class StartAndStopConsumer {

    private ConsumerSms consumerSms;

    @Autowired
    public StartAndStopConsumer(ConsumerSms consumerSms) {
        this.consumerSms = consumerSms;
    }

    @RequestMapping("/start")
    public ResponseEntity<String> startConsumer() {
        consumerSms.getMessage(true);
        return ResponseEntity.ok("Consumer is start!");
    }

    @RequestMapping("/stop")
    public ResponseEntity<String> stopConsumer() {
        consumerSms.getMessage(false);
        return ResponseEntity.ok("Consumer is stop!");
    }
}
