package com.MWT.webApi.gym.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.MWT.webApi.gym.model.SchedaEsercizio;

@Transactional
@Repository
public interface SchedaEsercizioRepository extends JpaRepository<SchedaEsercizio, Integer>{
	


}
