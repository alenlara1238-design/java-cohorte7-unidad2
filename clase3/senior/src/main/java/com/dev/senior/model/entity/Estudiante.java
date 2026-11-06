package com.dev.senior.model.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity 
@Table(name="estudiantes")
public class Estudiante {

    @Id 
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="curso_id")
    private Long id;

    private String nombre;

    private String correo;

    @OneToMany(mappedBy = "estudiante")
    private List<Inscripcion> inscripciones = new ArrayList<>();

    public Estudiante(){}

    public Estudiante(Long id, String nombre, String correo, List<Inscripcion> inscripciones) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.inscripciones = inscripciones;
    }

    public Estudiante(String nombre, String correo, List<Inscripcion> inscripciones ) {
        this.nombre = nombre;
        this.correo = correo;
        this.inscripciones = inscripciones;
    }

    public Estudiante(String nombre, String correo ) {
        this.nombre = nombre;
        this.correo = correo;
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

   public List<Inscripcion> getInscripciones(){
    return this.inscripciones;
   }


   public void setInscripciones(List<Inscripcion> inscripciones){
    this.inscripciones = inscripciones;
   }
    
}
