package ru.otus.de.project.consumergeo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.otus.de.project.consumergeo.consumer.ConsumerGeo;

@Controller
public class StartAndStopConsumer {

    private ConsumerGeo consumerGeo;
    private String initText = "PLEASE PUSH START BUTTON FOR START VALIDATOR!";

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
    public String startConsumer(Model model) {
        model.addAttribute("text",
                consumerGeo.getMessage(true)
        );
        return "index";
    }

    @RequestMapping("/stop")
    public String stopConsumer(Model model) {
        model.addAttribute("text",
                consumerGeo.getMessage(false)
        );
        return "index";
    }
}
