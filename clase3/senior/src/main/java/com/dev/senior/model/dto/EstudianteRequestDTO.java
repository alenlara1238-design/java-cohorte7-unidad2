package com.dev.senior.model.dto;

public class EstudianteRequestDTO {
    private String nombre;
    private String correo;
    private Long cursoId;

    public EstudianteRequestDTO(){}

    public EstudianteRequestDTO(String nombre, String correo, Long cursoId) {
        this.nombre = nombre;
        this.correo = correo;
        this.cursoId = cursoId;
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

    public Long getCursoId() {
        return cursoId;
    }

    public void setCursoId(Long cursoId) {
        this.cursoId = cursoId;
    }

    
}
