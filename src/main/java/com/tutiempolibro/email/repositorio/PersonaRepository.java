package com.tutiempolibro.email.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tutiempolibro.email.model.Persona;

public interface PersonaRepository extends JpaRepository<Persona, Integer> {
	
}
