package com.dev.senior.model.dto;

public class EstudianteResponseDTO {
    private Long id;
    private String nombre;
    private String correo;
    private Long cursoId;
    private String nombreCurso;

    public EstudianteResponseDTO(){}

    public EstudianteResponseDTO(Long id, String nombre, String correo, Long cursoId, String nombreCurso) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.cursoId = cursoId;
        this.nombreCurso = nombreCurso;
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

    public Long getCursoId() {
        return cursoId;
    }

    public String getNombreCurso() {
        return nombreCurso;
    }

    
}
