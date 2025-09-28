package com.poryectojpa.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NosotrosController {

    @GetMapping("/nosotros")
    public String mostrarNosotros(Model model) {
        model.addAttribute("titulo", "Nosotros - Sabor MasterClass");
        return "nosotros"; // busca src/main/resources/templates/nosotros.html
    }
}
