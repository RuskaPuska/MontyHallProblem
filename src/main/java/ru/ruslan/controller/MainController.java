package ru.ruslan.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.ruslan.model.Problem;

@Controller
@RequestMapping("")
public class MainController {

    @GetMapping
    public String index(@ModelAttribute("problem") Problem problem) {
        return "index";
    }

    @PostMapping
    public String sendResults(@ModelAttribute("problem") @Valid Problem problem, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "index";
        }
        return "results";
    }
}