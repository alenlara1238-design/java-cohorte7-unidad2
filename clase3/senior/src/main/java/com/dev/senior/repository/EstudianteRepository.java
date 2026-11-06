package com.dev.senior.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.senior.model.entity.Estudiante;

public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {

    
}
