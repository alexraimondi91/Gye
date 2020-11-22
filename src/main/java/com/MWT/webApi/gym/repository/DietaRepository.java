package com.MWT.webApi.gym.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.MWT.webApi.gym.model.Dieta;

public interface DietaRepository extends JpaRepository<Dieta,Integer>{
	public Dieta findByIdUtente (int idUtente);
}
