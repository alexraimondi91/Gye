package com.MWT.webApi.gym.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.MWT.webApi.gym.model.Ruoli;
import com.MWT.webApi.gym.model.TipiRuoli;

public interface RuoliRepository extends JpaRepository<Ruoli, Integer>{
	
	Optional<Ruoli> findByNome(TipiRuoli nome);

}
