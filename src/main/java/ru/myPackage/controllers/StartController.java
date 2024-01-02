package ru.myPackage.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.myPackage.services.BuilderService;

@Controller
@RequestMapping("/start")
public class StartController {

    private final BuilderService builderService;

    @Autowired
    public StartController(BuilderService builderService) {
        this.builderService = builderService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("countOfBuilders", builderService.findAll().size());
        return "start/index";
    }
}
