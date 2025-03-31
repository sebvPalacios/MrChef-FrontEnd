package com.example.mrchef;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RecetasController {

    @GetMapping("/recetas")
    public String login() {
        return "recetas";
    }
}