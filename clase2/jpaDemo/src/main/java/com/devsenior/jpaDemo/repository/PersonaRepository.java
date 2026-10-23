package com.devsenior.jpaDemo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.devsenior.jpaDemo.model.entity.*;


//Jpa repository ya trae un CRUD completo sin escribir nada (ni codigo java, ni codigo SQL)
public interface PersonaRepository extends JpaRepository<Persona, Long> {
    
    // Consultas derivadas. Documentación: https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html
    List<Persona> findByNombre(String nombre);

    List<Persona> findByNombreContaining(String nombre);

    Persona findByEmail(String email);

    List<Persona> findByTelefonoStartingWith(String telefono);
}
