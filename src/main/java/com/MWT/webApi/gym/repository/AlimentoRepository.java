package com.MWT.webApi.gym.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.MWT.webApi.gym.model.Alimento;
import com.MWT.webApi.gym.model.Dieta;

@Transactional
public interface AlimentoRepository extends JpaRepository<Alimento,Integer>{

}
