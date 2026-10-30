package com.dev.senior.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.senior.model.entity.Estudiante;

public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {

    //consultas derivadas...
    //todos los estudiantes que pertenezcan al curso con Id...
    List<Estudiante> findByCurso_Id(Long cursoId);
}
