package com.MWT.webApi.gym.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.MWT.webApi.gym.model.Ruolo;
import com.MWT.webApi.gym.model.TipiRuoli;
import com.MWT.webApi.gym.model.Utente;


@Transactional
@Repository
public interface UtenteRepository extends JpaRepository<Utente, Integer>{
	
	Optional<Utente> findByUserName(String userName);
	
	@Query("SELECT u FROM Utente u JOIN u.roles r WHERE r.nome = :nome")
	List<Utente> findUserByRole(@Param("nome") TipiRuoli nome);


	Boolean existsByUserName(String userName);

	Boolean existsByEmail(String email);

}
