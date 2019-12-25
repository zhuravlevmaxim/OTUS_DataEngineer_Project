package ru.otus.de.project.consumerpayment.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.otus.de.project.consumerpayment.consumer.ConsumerPayment;

@Controller
public class StartAndStopConsumer {

    private ConsumerPayment consumerPayment;
    private String initText = "PLEASE PUSH START BUTTON FOR START VALIDATOR!";

    @Autowired
    public StartAndStopConsumer(ConsumerPayment consumerSms) {
        this.consumerPayment = consumerSms;
    }

    @RequestMapping("/")
    public String getIndex(Model model) {
        model.addAttribute("text", initText);
        return "index";
    }

    @RequestMapping("/start")
    public String startConsumer(Model model) {
        model.addAttribute("text",
                consumerPayment.getMessage(true)
        );
        return "index";
    }

    @RequestMapping("/stop")
    public String stopConsumer(Model model) {
        model.addAttribute("text",
                consumerPayment.getMessage(false)
        );
        return "index";
    }
}