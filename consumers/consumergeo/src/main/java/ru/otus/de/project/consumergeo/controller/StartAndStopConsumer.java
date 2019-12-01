package ru.otus.de.project.consumergeo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.de.project.consumergeo.consumer.ConsumerGeo;

@RestController
public class StartAndStopConsumer {

    private ConsumerGeo consumerGeo;

    @Autowired
    public StartAndStopConsumer(ConsumerGeo consumerGeo) {
        this.consumerGeo = consumerGeo;
    }

    @RequestMapping("/start")
    public ResponseEntity<String> startConsumer() {
        consumerGeo.getMessage(true);
        return ResponseEntity.ok("Consumer is start!");
    }

    @RequestMapping("/stop")
    public ResponseEntity<String> stopConsumer() {
        consumerGeo.getMessage(false);
        return ResponseEntity.ok("Consumer is stop!");
    }
}
