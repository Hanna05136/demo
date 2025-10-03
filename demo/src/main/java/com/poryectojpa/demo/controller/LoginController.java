package com.poryectojpa.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.poryectojpa.demo.models.Persona;
import com.poryectojpa.demo.repository.personaRepository;

import jakarta.validation.Valid;

@Controller
public class LoginController {

    @Autowired
    private personaRepository personaRepository;

    // Mostrar formulario de login
    @GetMapping("/login")
    public String mostrarLogin(Model model) {
        model.addAttribute("persona", new Persona());
        return "login"; // templates/login.html
    }

    // Procesar login
    @PostMapping("/login")
    public String procesarLogin(
            @Valid @ModelAttribute("persona") Persona persona,
            BindingResult result,
            Model model) {

        // Validaciones de campos
        if (result.hasErrors()) {
            return "login";
        }
        
        // DEBUG: mostrar email ingresado
        System.out.println("Email ingresado en login: " + persona.getEmail());

        // Buscar usuario por email (trim para evitar espacios)
        Optional<Persona> optionalUser = personaRepository.findByEmail(persona.getEmail().trim());

        if (optionalUser.isEmpty()) {
            model.addAttribute("mensaje", "Correo o contraseña incorrectos");
            System.out.println("Usuario no encontrado");
            return "login";
        }

        Persona user = optionalUser.get();

        // DEBUG: mostrar datos del usuario encontrado
        System.out.println("Usuario encontrado en DB: " + user.getEmail());
        System.out.println("Rol en DB: " + user.getRolId());
        System.out.println("Contraseña en DB: " + user.getContrasena());
        System.out.println("Contraseña ingresada: " + persona.getContrasena());

        // Comparar contraseña
        if (!user.getContrasena().equals(persona.getContrasena())) {
            model.addAttribute("mensaje", "Correo o contraseña incorrectos");
            System.out.println("Contraseña incorrecta");
            return "login";
        }

        // Redirigir según rol
        if (user.getRolId() != null && user.getRolId().intValue() == 1) { // Admin
            System.out.println("Redirigiendo a admin");
            return "redirect:/admin";
        } else {
            System.out.println("Redirigiendo a estudiante");
            return "redirect:/estudiante";
        }
    }
}
