package com.poryectojpa.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poryectojpa.demo.models.Curso;
import com.poryectojpa.demo.repository.cursoRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin/cursos")
public class CursoController {

    @Autowired
    private cursoRepository cursoRepository;

    // Listar cursos
    @GetMapping
    public String listarCursos(Model model) {
        model.addAttribute("cursos", cursoRepository.findAll());
        return "admin/cursos/lista";
    }

    // Mostrar formulario de creación
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("curso", new Curso());
        return "admin/cursos/form";
    }

    // Guardar curso
    @PostMapping("/guardar")
    public String guardarCurso(@Valid @ModelAttribute("curso") Curso curso,
                               BindingResult result,
                               Model model) {
        if (result.hasErrors()) {
            return "admin/cursos/form";
        }
        cursoRepository.save(curso);
        return "redirect:/admin/cursos";
    }

    // Editar curso
    @GetMapping("/editar/{id}")
    public String editarCurso(@PathVariable("id") Integer id, Model model) {
        Curso curso = cursoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Curso inválido"));
        model.addAttribute("curso", curso);
        return "admin/cursos/form";
    }

    // Eliminar curso
    @GetMapping("/eliminar/{id}")
    public String eliminarCurso(@PathVariable("id") Integer id) {
        Curso curso = cursoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Curso inválido"));
        cursoRepository.delete(curso);
        return "redirect:/admin/cursos";
    }
}
