package com.dev.senior.model.dto;

import java.time.LocalDate;

public class InscripcionResponseDTO {
    private Long id;
    private Long estudianteId;
    private String estudianteNombre;
    private Long cursoId;
    private String cursoNombre;
    private LocalDate fechaInscripcion;


    public InscripcionResponseDTO(Long id, Long estudianteId, String estudianteNombre, Long cursoId, String cursoNombre,
            LocalDate fechaInscripcion) {
        this.id = id;
        this.estudianteId = estudianteId;
        this.estudianteNombre = estudianteNombre;
        this.cursoId = cursoId;
        this.cursoNombre = cursoNombre;
        this.fechaInscripcion = fechaInscripcion;
    }


    public Long getId() {
        return id;
    }


    public Long getEstudianteId() {
        return estudianteId;
    }


    public String getEstudianteNombre() {
        return estudianteNombre;
    }


    public Long getCursoId() {
        return cursoId;
    }


    public String getCursoNombre() {
        return cursoNombre;
    }


    public LocalDate getFechaInscripcion() {
        return fechaInscripcion;
    }

    
    
}
