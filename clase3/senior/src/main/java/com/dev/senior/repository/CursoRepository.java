package com.dev.senior.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.senior.model.entity.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long> {

}
