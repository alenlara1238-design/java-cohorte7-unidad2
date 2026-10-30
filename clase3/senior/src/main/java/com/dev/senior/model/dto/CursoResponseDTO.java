package com.dev.senior.model.dto;

public class CursoResponseDTO {
    private Long id;
    private String nombre;
    private Integer numeroHoras;

    public CursoResponseDTO(){}

    public CursoResponseDTO(Long id, String nombre, Integer numeroHoras) {
        this.id = id;
        this.nombre = nombre;
        this.numeroHoras = numeroHoras;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Integer getNumeroHoras() {
        return numeroHoras;
    }

    
}
