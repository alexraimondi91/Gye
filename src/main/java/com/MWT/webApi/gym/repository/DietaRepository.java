package com.MWT.webApi.gym.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.MWT.webApi.gym.model.Dieta;
import com.MWT.webApi.gym.model.Utente;


@Transactional
@Repository
public interface DietaRepository extends JpaRepository<Dieta,Integer>{
	public Dieta findByUtente (Utente utente);
}
