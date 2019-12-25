package ru.otus.de.project.bdinvalidwritersms.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.otus.de.project.bdinvalidwritersms.consumer.ConsumerSms;

@Controller
public class StartAndStopConsumer {

    private ConsumerSms consumerSms;
    private String initText = "PLEASE PUSH START BUTTON FOR START INVALID BD WRITER!";

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
    public String startProducer(Model model) {
        model.addAttribute("text",
                consumerSms.getMessage(true)
        );
        return "index";
    }

    @RequestMapping("/stop")
    public String stopProducer(Model model) {
        model.addAttribute("text",
                consumerSms.getMessage(false)
        );
        return "index";
    }
}
