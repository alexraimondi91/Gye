package com.MWT.webApi.gym.business;

import java.util.List;

import org.springframework.stereotype.Service;

import com.MWT.webApi.gym.model.Alimento;

public interface AlimentoService {
	
	List<Alimento> getAllAlimenti();
	
	Alimento getAlimento(int id);	
	
	Alimento createAlimento(Alimento alimento);
	
	Alimento updateAlimento( int id, Alimento alimentoUpdate);
	
	boolean deleteAlimento(int id);




}
