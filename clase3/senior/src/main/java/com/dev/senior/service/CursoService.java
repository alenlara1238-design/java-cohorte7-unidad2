package com.dev.senior.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.dev.senior.model.dto.CursoRequestDTO;
import com.dev.senior.model.dto.CursoResponseDTO;
import com.dev.senior.model.entity.Curso;
import com.dev.senior.repository.CursoRepository;

@Service 
public class CursoService {
    private final CursoRepository cursoRepo;

    public CursoService(CursoRepository cursoRepo){
        this.cursoRepo = cursoRepo;
    }

    public List<CursoResponseDTO> listarCursos(){
        //lista de entidades
        List<Curso> cursos = cursoRepo.findAll();

        //lista vacía, lista para llenar la respuesta.
        List<CursoResponseDTO> respuesta = new ArrayList<>();

        for(Curso curso: cursos){
            respuesta.add(convertirAResponseDTO(curso));
        }

        return respuesta;
    }

    public CursoResponseDTO crearCurso(CursoRequestDTO request){
        Curso curso = new Curso(
            request.getNombre(),
            request.getDuracionHoras()
        );

        Curso cursoCreado = cursoRepo.save(curso);
        return convertirAResponseDTO(cursoCreado);
    }


    public CursoResponseDTO actualizarCurso(Long id, CursoRequestDTO request){
        Curso curso = cursoRepo.findById(id)
                        .orElseThrow(()->
                            new IllegalArgumentException("Curso con id: " + id + " no encontrado")
                        );
        
         curso.setNombre(request.getNombre());
         curso.setDuracionHoras(request.getDuracionHoras()); 
         
        Curso cursoActualizado = cursoRepo.save(curso);
        return convertirAResponseDTO(cursoActualizado);
        
    }


    public void eliminarCurso(Long id){
        Curso curso = cursoRepo.findById(id)
        .orElseThrow(()->
            new IllegalArgumentException("Curso con id: " + id + " no encontrado");
        );

        cursoRepo.deleteById(id);
    }

    private CursoResponseDTO convertirAResponseDTO(Curso curso){
        return new CursoResponseDTO(
            curso.getId(),
            curso.getNombre(),
            curso.getDuracionHoras()
        );
    }

}
