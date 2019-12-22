package ru.otus.de.project.servicegeo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.de.project.servicegeo.producer.ServiceProducer;

@RestController
public class StartAndStopController {

    private ServiceProducer serviceProducer;

    @Autowired
    public StartAndStopController(ServiceProducer serviceProducer) {
        this.serviceProducer = serviceProducer;
    }

    @RequestMapping("/start")
    public ResponseEntity<String> startProducer() {
        return ResponseEntity.ok(serviceProducer
                .sendData(true)
        );
    }

    @RequestMapping("/stop")
    public ResponseEntity<String> stopProducer() {
        return ResponseEntity.ok(serviceProducer
                .sendData(false)
        );
    }
}
