package com.poryectojpa.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poryectojpa.demo.models.Persona;
import com.poryectojpa.demo.repository.personaRepository;

@Controller
@RequestMapping("/personas")
public class PersonaController {

    @Autowired
    private personaRepository personaRepository;

    // 1. LISTAR TODAS LAS PERSONAS
    @GetMapping
    public String listarPersonas(Model model) {
        List<Persona> personas = personaRepository.findAll();
        model.addAttribute("personas", personas);
        return "admin"; // Vista que lista y muestra formulario
    }

    // 2. MOSTRAR FORMULARIO PARA NUEVA PERSONA
    @GetMapping("/nueva")
    public String formularioNuevaPersona(Model model) {
        model.addAttribute("persona", new Persona());
        return "admin";
    }

    // 3. GUARDAR NUEVA PERSONA
    @PostMapping
    public String guardarPersona(@ModelAttribute Persona persona) {
        personaRepository.save(persona);
        return "redirect:/personas";
    }

    // 4. MOSTRAR FORMULARIO DE EDICIÓN
    @GetMapping("/editar/{id}")
    public String formularioEditarPersona(@PathVariable Integer id, Model model) {
        Persona persona = personaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Persona no encontrada: " + id));
        model.addAttribute("persona", persona);
        return "admin";
    }

    // 5. ACTUALIZAR PERSONA
    @PostMapping("/{id}")
    public String actualizarPersona(@PathVariable Integer id, @ModelAttribute Persona persona) {
        persona.setId(id);
        personaRepository.save(persona);
        return "redirect:/personas";
    }

    // 6. ELIMINAR PERSONA
    @GetMapping("/eliminar/{id}")
    public String eliminarPersona(@PathVariable Integer id) {
        personaRepository.deleteById(id);
        return "redirect:/personas";
    }
}
