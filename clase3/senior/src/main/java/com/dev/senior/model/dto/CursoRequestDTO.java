package com.dev.senior.model.dto;

public class CursoRequestDTO {

    private String nombre;
    private Integer duracionHoras;


    public CursoRequestDTO(){}


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
