package ru.otus.de.project.bdvalidwriterpayment.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.otus.de.project.bdvalidwriterpayment.consumer.ConsumerPayment;

@Controller
public class StartAndStopConsumer {

    private ConsumerPayment consumerPayment;
    private String initText = "PLEASE PUSH START BUTTON FOR START VALID BD WRITER!";

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
