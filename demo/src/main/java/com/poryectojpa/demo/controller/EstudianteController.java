package com.poryectojpa.demo.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/estudiante")
public class EstudianteController {

    @GetMapping
    public String vistaEstudiante(Model model) {
        // Simulamos un usuario. Esto puede venir de la sesión o la BD
        model.addAttribute("usuario", "Juan Pérez");
        return "Estudiante"; // Renderiza estudiante.html
    }

    @GetMapping("/miscursos")
    public String misCursos(Model model) {
        // Aquí cargarías cursos del estudiante
        return "MisCursos"; // otra vista
    }
}

