package ru.myPackage.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.myPackage.DAO.ModelsDAO;

@Controller
@RequestMapping("/start")
public class StartController {

    private final ModelsDAO modelsDAO;

    @Autowired
    public StartController(ModelsDAO modelsDAO) {
        this.modelsDAO = modelsDAO;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("countOfBuilders", modelsDAO.index().size());
        return "start/index";
    }
}
