package com.dev.senior.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.senior.model.dto.InscripcionRequestDTO;
import com.dev.senior.model.dto.InscripcionResponseDTO;
import com.dev.senior.service.InscripcionService;

@RestController 
@RequestMapping("/inscripciones")
public class InscripcionController {
    private final InscripcionService service;

    public InscripcionController(InscripcionService service){
        this.service = service;
    }

    @PostMapping 
    public InscripcionResponseDTO crearInscripcion(@RequestBody InscripcionRequestDTO request){
        return service.crearInscripcion(request);
    }

    @GetMapping 
    public  List<InscripcionResponseDTO> listarInscripciones(){
        return this.service.listarInscripciones();
    }

    @GetMapping("/estudiante/id")
    public List<InscripcionResponseDTO> buscarPorEstudianteId(@PathVariable Long id){
        return this.service.buscarPorEstudiante_id(id);
    }


    @GetMapping("/curso/id")
    public List<InscripcionResponseDTO> buscarPorCursoId(@PathVariable Long id){
        return this.service.buscarPorCursoId(id);
    }


    @DeleteMapping("/{id}")
    public void eliminarInscripcion(@PathVariable Long id){
        this.service.eliminarInscripcion(id);
    }


    

}
