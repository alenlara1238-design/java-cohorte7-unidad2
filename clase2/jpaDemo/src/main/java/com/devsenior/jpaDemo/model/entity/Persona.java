package com.devsenior.jpaDemo.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="personas")
public class Persona {

    @Id 
    @Column(name="cedula")
    private Long cedula;

    @Column(name="nombre", length = 100, nullable = false)
    private String nombre;

    @Column(name="telefono", length = 20)
    private String telefono;

    @Column(name="email", length = 150)
    private String email;


    // Constructor es obligatorio para JPA
    public Persona(){}


    public Persona(Long cedula, String nombre, String telefono, String email) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
    }


    public Long getCedula() {
        return cedula;
    }


    public void setCedula(Long cedula) {
        this.cedula = cedula;
    }


    public String getNombre() {
        return nombre;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public String getTelefono() {
        return telefono;
    }


    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }

    
}
