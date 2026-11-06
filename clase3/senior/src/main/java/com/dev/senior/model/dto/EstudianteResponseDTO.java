package com.dev.senior.model.dto;

public class EstudianteResponseDTO {
    private Long id;
    private String nombre;
    private String correo;

    public EstudianteResponseDTO(){}

    public EstudianteResponseDTO(Long id, String nombre, String correo) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }
    
}
