package ru.otus.de.project.servicesms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.de.project.servicesms.producer.ServiceProducer;

@RestController
public class StartAndStopController {

    private ServiceProducer serviceProducer;

    @Autowired
    public StartAndStopController(ServiceProducer serviceProducer) {
        this.serviceProducer = serviceProducer;
    }

    @RequestMapping("/start")
    public ResponseEntity<String> startProducer() {
        serviceProducer.sendData(true);
        return ResponseEntity.ok("Producer is start!");
    }

    @RequestMapping("/stop")
    public ResponseEntity<String> stopProducer() {
        serviceProducer.sendData(false);
        return ResponseEntity.ok("Producer is stop!");
    }
}
