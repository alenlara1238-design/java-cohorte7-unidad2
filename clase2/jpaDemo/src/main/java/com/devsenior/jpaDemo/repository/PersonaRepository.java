package com.devsenior.jpaDemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.devsenior.jpaDemo.model.entity.*;


//Jpa repository ya trae un CRUD completo sin escribir nada (ni codigo java, ni codigo SQL)
public class PersonaRepository extends JpaRepository<Persona, Long> {
    
}
