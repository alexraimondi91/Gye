package com.MWT.webApi.gym.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.MWT.webApi.gym.model.SchedaEsercizio;

@Transactional
@Repository
public interface SchedaEsercizioRepository extends JpaRepository<SchedaEsercizio, Integer>{

}
