package ru.otus.de.project.bdinvalidwriterpayment.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.otus.de.project.bdinvalidwriterpayment.consumer.ConsumerPayment;

@Controller
public class StartAndStopConsumer {

    private ConsumerPayment consumerPayment;
    private String initText = "PLEASE PUSH START BUTTON FOR START INVALID BD WRITER!";

    @Autowired
    public StartAndStopConsumer(ConsumerPayment consumerPayment) {
        this.consumerPayment = consumerPayment;
    }

    @RequestMapping("/")
    public String getIndex(Model model) {
        model.addAttribute("text", initText);
        return "index";
    }

    @RequestMapping("/start")
    public String startProducer(Model model) {
        model.addAttribute("text",
                consumerPayment.getMessage(true)
        );
        return "index";
    }

    @RequestMapping("/stop")
    public String stopProducer(Model model) {
        model.addAttribute("text",
                consumerPayment.getMessage(false)
        );
        return "index";
    }
}
