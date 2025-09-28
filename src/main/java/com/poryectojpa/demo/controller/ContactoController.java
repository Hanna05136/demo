package com.poryectojpa.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ContactoController {

    @GetMapping("/contacto")
    public String mostrarFormularioContacto() {
        return "contacto"; // Renderiza contacto.html
    }

    @PostMapping("/contacto")
    public String procesarFormularioContacto(
            @RequestParam String nombre,
            @RequestParam String apellido,
            @RequestParam String email,
            @RequestParam String numero,
            @RequestParam String tema,
            @RequestParam String mensaje,
            Model model
    ) {
        // Aquí puedes guardar la información en BD o enviarla por correo
        model.addAttribute("exito", "¡Mensaje enviado correctamente!");
        return "contacto"; // Se vuelve a mostrar la misma vista
    }
}
