package ru.otus.de.project.bdinvalidwritergeo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.de.project.bdinvalidwritergeo.consumer.ConsumerGeo;

@RestController
public class StartAndStopConsumer {

    private ConsumerGeo consumerGeo;

    @Autowired
    public StartAndStopConsumer(ConsumerGeo consumerGeo) {
        this.consumerGeo = consumerGeo;
    }

    @RequestMapping("/start")
    public ResponseEntity<String> startConsumer() {
        return ResponseEntity.ok(
                consumerGeo.getMessage(true)
        );
    }

    @RequestMapping("/stop")
    public ResponseEntity<String> stopConsumer() {
        return ResponseEntity.ok(
                consumerGeo.getMessage(false)
        );
    }
}
