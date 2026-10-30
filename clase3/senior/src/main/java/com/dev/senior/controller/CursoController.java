package com.dev.senior.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.senior.model.dto.CursoRequestDTO;
import com.dev.senior.model.dto.CursoResponseDTO;
import com.dev.senior.service.CursoService;

@RestController 
@RequestMapping("/cursos")
public class CursoController {
    private final CursoService service;

    public CursoController(CursoService service){
        this.service = service;
    }

    @GetMapping
    public List<CursoResponseDTO> listarCursos(){
        return service.listarCursos();
    }

    @PostMapping 
    public CursoResponseDTO crearCurso(@RequestBody CursoRequestDTO request){
        return service.crearCurso(request);
    }

    @PutMapping
    public CursoResponseDTO actualizarCurso(@PathVariable Long id, @RequestBody CursoRequestDTO request){
        return service.actualizarCurso(id, request);
    }

    @DeleteMapping 
    public void eliminarCurso(@PathVariable  Long id){
        service.eliminarCurso(id);
    }

}
