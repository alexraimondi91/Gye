package com.MWT.webApi.gym.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.MWT.webApi.gym.model.Utente;


@Transactional
@Repository
public interface UtenteRepository extends JpaRepository<Utente, Integer>{
	
	Optional<Utente> findByUserName(String userName);

	Boolean existsByUserName(String userName);

	Boolean existsByEmail(String email);

}
