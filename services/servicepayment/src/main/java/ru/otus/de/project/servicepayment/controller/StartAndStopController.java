package ru.otus.de.project.servicepayment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.otus.de.project.servicepayment.producer.ServiceProducer;

@Controller
public class StartAndStopController {

    private ServiceProducer serviceProducer;
    private String initText = "PLEASE PUSH START BUTTON FOR START PRODUCER!";

    @Autowired
    public StartAndStopController(ServiceProducer serviceProducer) {
        this.serviceProducer = serviceProducer;
    }

    @RequestMapping("/")
    public String getIndex(Model model) {
        model.addAttribute("text", initText);
        return "index";
    }

    @RequestMapping("/start")
    public String startProducer(Model model) {
        model.addAttribute("text",
                serviceProducer.sendData(true)
        );
        return "index";
    }

    @RequestMapping("/stop")
    public String stopProducer(Model model) {
        model.addAttribute("text",
                serviceProducer.sendData(false)
        );
        return "index";
    }
}
