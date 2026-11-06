package com.dev.senior.model.dto;

public class InscripcionRequestDTO {
    private Long estudianteId;

    private Long cursoId;

    public Long getEstudianteId() {
        return estudianteId;
    }

    public void setEstudianteId(Long eestudianteId) {
        this.estudianteId = eestudianteId;
    }

    public Long getCursoId() {
        return cursoId;
    }

    public void setCursoId(Long cursoId) {
        this.cursoId = cursoId;
    }


    
}
