package ru.otus.de.project.bdinvalidwritergeo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.otus.de.project.bdinvalidwritergeo.consumer.ConsumerGeo;

@Controller
public class StartAndStopConsumer {

    private ConsumerGeo consumerGeo;
    private String initText = "PLEASE PUSH START BUTTON FOR START INVALID BD WRITER!";

    @Autowired
    public StartAndStopConsumer(ConsumerGeo consumerGeo) {
        this.consumerGeo = consumerGeo;
    }

    @RequestMapping("/")
    public String getIndex(Model model) {
        model.addAttribute("text", initText);
        return "index";
    }

    @RequestMapping("/start")
    public String startProducer(Model model) {
        model.addAttribute("text",
                consumerGeo.getMessage(true)
        );
        return "index";
    }

    @RequestMapping("/stop")
    public String stopProducer(Model model) {
        model.addAttribute("text",
                consumerGeo.getMessage(false)
        );
        return "index";
    }
}
