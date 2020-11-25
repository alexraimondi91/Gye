package com.MWT.webApi.gym.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.MWT.webApi.gym.model.Dieta;
import com.MWT.webApi.gym.model.Utente;


@Transactional
@Repository
public interface DietaRepository extends JpaRepository<Dieta,Integer>{
   List<Dieta> findByUtente (Utente utente);	
   
   
   @Query(value ="SELECT d FROM Dieta d WHERE d.active = TRUE")
   Dieta findByActive ();	


}
