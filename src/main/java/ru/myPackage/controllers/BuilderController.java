package ru.myPackage.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.myPackage.DAO.ModelsDAO;
import ru.myPackage.models.Builder;
import ru.myPackage.utils.BuilderValidator;

@Controller
@RequestMapping("/builders")
public class BuilderController {

    private final ModelsDAO modelsDAO;

    private final BuilderValidator builderValidator;

    @Autowired
    public BuilderController(ModelsDAO modelsDAO, BuilderValidator builderValidator) {
        this.modelsDAO = modelsDAO;
        this.builderValidator = builderValidator;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("builders", modelsDAO.index());
        return "builders/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") String id, Model model) {
        model.addAttribute("builders", modelsDAO.show(id));
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

        modelsDAO.save(builder);
        return "redirect:/builders";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") String id) {
        model.addAttribute("builders", modelsDAO.show(id));
        return "builders/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("builders") @Valid Builder builder,
                         @PathVariable("id") String id,
                         BindingResult bindingResult) {

        builderValidator.validate(builder, bindingResult);

        if (bindingResult.hasErrors()) return "builders/new";

        modelsDAO.update(id, builder);
        return "redirect:/builders";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") String id) {
        modelsDAO.delete(id);
        return "redirect:/builders";
    }

    @GetMapping("/{id}/editTime")
    public String getUpdateTime(Model model, @PathVariable("id") String id) {
        model.addAttribute("builder", modelsDAO.show(id));
        return "builders/editTime";
    }

    @PostMapping("/editTime/{id}")
    public String updateTimeOfBuilder(@ModelAttribute("builders") Builder builder,
                                      @PathVariable("id") String id,
                                      @RequestParam("time") int time) {
        modelsDAO.updateTime(id, time);
        return "redirect:/builders/{id}/editTime";
    }
}
