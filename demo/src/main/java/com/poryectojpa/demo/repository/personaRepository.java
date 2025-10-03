package com.poryectojpa.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poryectojpa.demo.models.Persona;

@Repository
public interface personaRepository extends JpaRepository<Persona, Integer> {

    Optional<Persona> findByEmail(String email);

    // Buscar por nombre que contenga algo y rol
    List<Persona> findByNombreContainingIgnoreCaseAndRolId(String nombre, Integer rolId);

    // Buscar solo por nombre
    List<Persona> findByNombreContainingIgnoreCase(String nombre);

    // Buscar solo por rol
    List<Persona> findByRolId(Integer rolId);
}
