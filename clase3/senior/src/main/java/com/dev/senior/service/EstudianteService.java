package com.dev.senior.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.spel.ExtensionAwareEvaluationContextProvider;
import org.springframework.stereotype.Service;

import com.dev.senior.model.dto.EstudianteRequestDTO;
import com.dev.senior.model.dto.EstudianteResponseDTO;
import com.dev.senior.model.entity.*;
import com.dev.senior.repository.EstudianteRepository;

@Service 
public class EstudianteService {
    private final EstudianteRepository estudianteRepository;

    public EstudianteService(
        EstudianteRepository estudianteRepository
    ){
        this.estudianteRepository = estudianteRepository;
    }

    public List<EstudianteResponseDTO> listarEstudiantes(){
      List<Estudiante> estudiantes =  this.estudianteRepository.findAll();

      List<EstudianteResponseDTO> respuesta = new ArrayList<>();

      for(Estudiante estudiante: estudiantes){
        respuesta.add(convertirAResponseDTO(estudiante));
      }

      return respuesta;
    }

    public EstudianteResponseDTO crearEstudiante(
        EstudianteRequestDTO request
    ){
       Estudiante estudiante = new Estudiante(
            request.getNombre(),
            request.getCorreo()
       );

       Estudiante creado = estudianteRepository.save(estudiante);

       return convertirAResponseDTO(creado);
    }

    private EstudianteResponseDTO convertirAResponseDTO(Estudiante estudiante){
        return new EstudianteResponseDTO(
            estudiante.getId(),
            estudiante.getNombre(),
            estudiante.getCorreo()
        );
    }

}
