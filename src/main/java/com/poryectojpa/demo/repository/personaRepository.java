package com.poryectojpa.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.poryectojpa.demo.models.Persona;

@Repository
public interface personaRepository extends JpaRepository<Persona, Integer> {

    // 🔎 Buscar persona por email (de tu código original)
    Persona findByEmail(String email);

    // 🔎 Filtrar solo personas no eliminadas
    @Query("SELECT p FROM Persona p WHERE p.deleted = false")
    List<Persona> findAllActive();

    // 🗑 Soft Delete (marcar como eliminado)
    @Transactional
    @Modifying
    @Query("UPDATE Persona p SET p.deleted = true WHERE p.id = :id")
    void softDeleteById(Integer id);
}
