package com.dev.senior.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.dev.senior.model.dto.InscripcionRequestDTO;
import com.dev.senior.model.dto.InscripcionResponseDTO;
import com.dev.senior.model.entity.*;
import com.dev.senior.repository.*;
import com.dev.senior.repository.InscripcionRepository;

import jakarta.transaction.Transactional;

@Service 
public class InscripcionService {
    //Como este es el servicioDe la tabla intermediaMuy probablemente va a necesitar De los Repositorios de sus dependencias y el propio.

    private final InscripcionRepository inscripcionRepository;
    private final EstudianteRepository estudianteRepository;
    private final CursoRepository cursoRepository;

    public InscripcionService(
        InscripcionRepository inscripcionRepository,
        EstudianteRepository estudianteRepository,
        CursoRepository cursoRepository
    ){
        this.inscripcionRepository = inscripcionRepository;
        this.estudianteRepository = estudianteRepository;
        this.cursoRepository = cursoRepository;
    }

    @Transactional 
    public InscripcionResponseDTO crearInscripcion(InscripcionRequestDTO request){
        //1. verificamos existenci de estudiante que viene en request
        Estudiante estudiante = estudianteRepository.findById(request.getEstudianteId())
                                    .orElseThrow(()-> new IllegalArgumentException("Estudiante no encontrado"));
                  
        //2. verificamos si el curso existe
        Curso curso = cursoRepository.findById(request.getCursoId())
                        .orElseThrow(()->new IllegalArgumentException("Curso no encontrado"));

        Inscripcion inscripcion = new Inscripcion(estudiante, curso);

        Inscripcion creada =   inscripcionRepository.save(inscripcion);

        return convertirAResponseDTO(creada);
    }

    public List<InscripcionResponseDTO> listarInscripciones(){
        List<Inscripcion> inscripciones = inscripcionRepository.findAll();

        List<InscripcionResponseDTO> respuestas = new ArrayList<>();

        for(Inscripcion inscripcion: inscripciones){
            respuestas.add(convertirAResponseDTO(inscripcion));
        }

        return respuestas;
    } 

    public List<InscripcionResponseDTO> buscarPorEstudiante_id(Long estudianteId){
        
        if(!estudianteRepository.existsById(estudianteId)){
            throw new IllegalArgumentException("Estudiante no encontrado");
        }

        List<Inscripcion> inscripciones = inscripcionRepository.findByEstudiante_id(estudianteId);

        List<InscripcionResponseDTO> respuestas = new ArrayList<>();

        for(Inscripcion inscripcion: inscripciones){
            respuestas.add(convertirAResponseDTO(inscripcion));
        }

        return respuestas;
    }


    public List<InscripcionResponseDTO> buscarPorCursoId(Long cursoId){
        if(!cursoRepository.existsById(cursoId)){
            throw new IllegalArgumentException("Curso no encontrado");
        }

        List<Inscripcion> inscripciones = inscripcionRepository.findByCurso_id(cursoId);

        List<InscripcionResponseDTO> respuestas = new ArrayList<>();

        for(Inscripcion inscripcion: inscripciones){
            respuestas.add(convertirAResponseDTO(inscripcion));
        }

        return respuestas;

    }

    public void eliminarInscripcion(Long id){
        if(!inscripcionRepository.existsById(id)){
            throw new IllegalArgumentException("Inscripcion no encontrada");
        }

        inscripcionRepository.deleteById(id);
    }


    public InscripcionResponseDTO convertirAResponseDTO(Inscripcion inscripcion){
        return new InscripcionResponseDTO(inscripcion.getId(), inscripcion.getEstudiante().getId(), inscripcion.getEstudiante().getNombre(), inscripcion.getCurso().getId(),inscripcion.getCurso().getNombre(), inscripcion.getFechaInscripcion());
    }


    















}
