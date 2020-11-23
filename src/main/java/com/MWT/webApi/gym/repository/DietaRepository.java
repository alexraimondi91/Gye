package com.MWT.webApi.gym.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.MWT.webApi.gym.model.Dieta;
import com.MWT.webApi.gym.model.Utente;

public interface DietaRepository extends JpaRepository<Dieta,Integer>{
	public Dieta findByUtente (Utente utente);
}
