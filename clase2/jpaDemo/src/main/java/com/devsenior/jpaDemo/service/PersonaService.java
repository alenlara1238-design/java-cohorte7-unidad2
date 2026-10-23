package com.devsenior.jpaDemo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.devsenior.jpaDemo.model.dto.PersonaDTO;
import com.devsenior.jpaDemo.model.entity.Persona;
import com.devsenior.jpaDemo.repository.PersonaRepository;

@Service 
public class PersonaService {

    private final PersonaRepository repository;

    public PersonaService(PersonaRepository repository){
        this.repository = repository;
    }

    public PersonaDTO crear(PersonaDTO dto){
        Persona persona = mapToEntity(dto);
        Persona personaGuardada = repository.save(persona);
        return mapToDTO(personaGuardada);
        
    }

    public List<PersonaDTO> listar(){
        return repository.findAll()
                .stream()
                .map(entity -> this.mapToDTO(entity))
                .toList();
    }

    public PersonaDTO BuscarPorId(Long cedula){
        Persona persona = repository.findById(cedula).orElseThrow(()-> new RuntimeException("Persona no encontrada"));

        return mapToDTO(persona);
    }

    public void eliminar(Long cedula){
        repository.deleteById(cedula);
    }

    private PersonaDTO mapToDTO(Persona persona){
        return new PersonaDTO(
            persona.getCedula(),
            persona.getNombre(),
            persona.getTelefono(),
            persona.getEmail()
        );
    }

    private Persona mapToEntity(PersonaDTO dto){
        return new Persona(
            dto.getCedula(),
            dto.getNombre(),
            dto.getTelefono(),
            dto.getEmail()
        );
    }
}
