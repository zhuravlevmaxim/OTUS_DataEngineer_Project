package ru.otus.de.project.consumersms.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.de.project.consumersms.consumer.ConsumerSms;

@RestController
public class StartAndStopConsumer {

    private ConsumerSms consumerSms;

    @Autowired
    public StartAndStopConsumer(ConsumerSms consumerSms) {
        this.consumerSms = consumerSms;
    }

    @RequestMapping("/start")
    public ResponseEntity<String> startConsumer() {
        return ResponseEntity.ok(
                consumerSms.getMessage(true)
        );
    }

    @RequestMapping("/stop")
    public ResponseEntity<String> stopConsumer() {
        return ResponseEntity.ok(
                consumerSms.getMessage(false)
        );
    }
}