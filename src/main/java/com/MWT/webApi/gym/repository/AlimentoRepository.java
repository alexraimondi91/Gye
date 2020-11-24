package com.MWT.webApi.gym.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.MWT.webApi.gym.model.Alimento;
import com.MWT.webApi.gym.model.Dieta;


@Transactional
@Repository
public interface AlimentoRepository extends JpaRepository<Alimento,Integer>{

}
