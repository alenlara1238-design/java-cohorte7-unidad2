package com.dev.senior.model.entity;

import jakarta.persistence.Entity;

import java.time.LocalDate;

import jakarta.persistence.*;
import jakarta.persistence.Table;

@Entity
@Table(name="inscripciones")
public class Inscripcion {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="estudiante_id", nullable = false)
    private Estudiante estudiante;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="curso_id", nullable = false)
    private Curso curso;

    private LocalDate fechaInscripcion;

    public Inscripcion(){}

    public Inscripcion(Estudiante estudiante, Curso curso) {
        this.estudiante = estudiante;
        this.curso = curso;
        this.fechaInscripcion = LocalDate.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public LocalDate getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(LocalDate fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }


    
    






}
