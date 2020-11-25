package com.MWT.webApi.gym.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.MWT.webApi.gym.model.Ruolo;
import com.MWT.webApi.gym.model.TipiRuoli;


@Transactional
@Repository
public interface RuoliRepository extends JpaRepository<Ruolo, Integer>{
	
	Optional<Ruolo> findByNome(TipiRuoli nome);

}
