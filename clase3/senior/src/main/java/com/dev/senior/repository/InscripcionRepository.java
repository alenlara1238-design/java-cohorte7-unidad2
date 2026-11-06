package com.dev.senior.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.senior.model.entity.Inscripcion;

public interface InscripcionRepository extends JpaRepository<Inscripcion, Long> {
    List<Inscripcion> findByEstudiante_id(Long estudianteid);

    List<Inscripcion> findByCurso_id(Long cursoId);
    
}
