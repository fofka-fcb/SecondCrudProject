package ru.myPackage.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.myPackage.DAO.ModelsDAO;
import ru.myPackage.models.Builder;
import ru.myPackage.models.State;
import ru.myPackage.services.BuilderService;
import ru.myPackage.utils.BuilderValidator;

@Controller
@RequestMapping("/builders")
public class BuilderController {

    private final ModelsDAO modelsDAO;

    private final BuilderValidator builderValidator;

    private final BuilderService builderService;

    @Autowired
    public BuilderController(ModelsDAO modelsDAO, BuilderValidator builderValidator, BuilderService builderService) {
        this.modelsDAO = modelsDAO;
        this.builderValidator = builderValidator;
        this.builderService = builderService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("builders", builderService.findAll());
        return "builders/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("builders", builderService.findOne(id));
        return "builders/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("builders") Builder builder) {
        return "builders/new";
    }

    @PostMapping
    public String createBuilder(@ModelAttribute("builders") @Valid Builder builder,
                                BindingResult bindingResult) {

        builderValidator.validate(builder, bindingResult);

        if (bindingResult.hasErrors()) return "builders/new";

        builderService.save(builder);
        return "redirect:/builders";
    }

    @GetMapping("/{id}/edit")
    public String edit(ModelMap modelMap, @PathVariable("id") int id) {
        modelMap.addAttribute("builders", builderService.findOne(id));
        modelMap.addAttribute("states", State.values());
        return "builders/edit";
    }

    @PatchMapping("/{id}/update")
    public String update(@ModelAttribute("builders") @Valid Builder builder,
                         @PathVariable("id") int id,
                         BindingResult bindingResult) {

        if (!builder.getEmail().equals(builderService.findOne(id).getEmail())) {
            builderValidator.validate(builder, bindingResult);
        }

        if (bindingResult.hasErrors()) return "builders/edit";

        builderService.update(id, builder);
        return "redirect:/builders/{id}";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        builderService.delete(id);
        return "redirect:/builders";
    }

    @GetMapping("/{id}/editTime")
    public String getUpdateTime(Model model, @PathVariable("id") int id) {
        model.addAttribute("builder", builderService.findOne(id));
        return "builders/editTime";
    }

    @PostMapping("/editTime/{id}")
    public String updateTimeOfBuilder(@ModelAttribute("builders") Builder builder,
                                      @PathVariable("id") int id,
                                      @RequestParam("time") int time) {
        builderService.updateTime(id, time);
        return "redirect:/builders/{id}/editTime";
    }
}
