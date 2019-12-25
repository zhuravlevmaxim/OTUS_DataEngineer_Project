package ru.otus.de.project.consumersms.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.de.project.consumersms.consumer.ConsumerSms;

@RestController
public class StartAndStopConsumer {

    private ConsumerSms consumerSms;
    private String initText = "PLEASE PUSH START BUTTON FOR START VALIDATOR!";

    @Autowired
    public StartAndStopConsumer(ConsumerSms consumerSms) {
        this.consumerSms = consumerSms;
    }

    @RequestMapping("/")
    public String getIndex(Model model) {
        model.addAttribute("text", initText);
        return "index";
    }

    @RequestMapping("/start")
    public String startConsumer(Model model) {
        model.addAttribute("text",
                consumerSms.getMessage(true)
        );
        return "index";
    }

    @RequestMapping("/stop")
    public String stopConsumer(Model model) {
        model.addAttribute("text",
                consumerSms.getMessage(false)
        );
        return "index";
    }
}