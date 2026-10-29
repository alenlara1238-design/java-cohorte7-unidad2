package com.devsenior.jpaDemo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.devsenior.jpaDemo.model.dto.PersonaDTO;
import com.devsenior.jpaDemo.service.PersonaService;

@RestController 
@RequestMapping("/api/personas")
public class PersonaController {

    private final PersonaService service;

    public PersonaController(PersonaService service){
        this.service = service;
    }

    @PostMapping
    public PersonaDTO crear(@RequestBody PersonaDTO personaDTO){
        return service.crear(personaDTO);
    }

    @GetMapping
    public List<PersonaDTO> listar(){
       return service.listar();
    }

    @GetMapping("/{cedula}")
    public PersonaDTO obtener(@PathVariable Long cedula){
        return service.BuscarPorId(cedula);
    }

    @DeleteMapping("/{cedula}")
    public void eliminar(@PathVariable Long cedula){
        service.eliminar(cedula);
    }

    @GetMapping("/buscar")
    public List<PersonaDTO> buscarPorNombre(@RequestParam String nombre){
        return service.buscarPorNombre(nombre);
    }

    @GetMapping("/buscar/containing")
    public List<PersonaDTO> buscarPorNombreContaining(@RequestParam String nombre){
        return service.buscarPorNombreContaining(nombre);
    }

    @GetMapping("/email")
    public PersonaDTO buscarPorEmail(@RequestParam String email){
        return service.buscarPorEmail(email);
    }

    @GetMapping("/telefono")
    public List<PersonaDTO> buscarPorTelefono(@RequestParam String prefijo){
        return service.buscarPorTelefonoStartingWith(prefijo);
    }
}
