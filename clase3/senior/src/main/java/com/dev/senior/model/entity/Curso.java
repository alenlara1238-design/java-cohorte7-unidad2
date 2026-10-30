package com.dev.senior.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity 
@Table(name="cursos")
public class Curso {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private Integer duracionHoras;

    public Curso(){}

    public Curso(Long id, String nombre, Integer duracionHoras) {
        this.id = id;
        this.nombre = nombre;
        this.duracionHoras = duracionHoras;
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

    
}
