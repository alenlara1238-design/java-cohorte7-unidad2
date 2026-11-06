package com.dev.senior.model.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity 
@Table(name="cursos")
public class Curso {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="curso_id")
    private Long id;

    private String nombre;

    private Integer duracionHoras;

    @OneToMany(mappedBy = "curso")
    private List<Inscripcion> inscripciones = new ArrayList<>();

    public Curso(){}

    public Curso(Long id, String nombre, Integer duracionHoras) {
        this.id = id;
        this.nombre = nombre;
        this.duracionHoras = duracionHoras;
    }

    public Curso(Long id, String nombre, Integer duracionHoras, List<Inscripcion> inscripciones) {
        this.id = id;
        this.nombre = nombre;
        this.duracionHoras = duracionHoras;
        this.inscripciones = inscripciones;
    }


    public Curso(String nombre, Integer duracionHoras){
        this.nombre = nombre;
        this.duracionHoras = duracionHoras;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getDuracionHoras() {
        return duracionHoras;
    }

    public void setDuracionHoras(Integer duracionHoras) {
        this.duracionHoras = duracionHoras;
    }

    public List<Inscripcion> getInscripciones(){
        return this.inscripciones;
    }

    public void setInscripciones(List<Inscripcion> inscripciones){
        this.inscripciones = inscripciones;
    }

}
