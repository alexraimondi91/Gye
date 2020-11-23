package com.MWT.webApi.gym.business;

import java.util.List;

import com.MWT.webApi.gym.model.Alimento;

public interface AlimentoService {
	
	List<Alimento> getAllAlimenti();
	
	Alimento getAlimento(int id);	
	
	Alimento createAlimento(Alimento alimento);
	
	Alimento updateAlimento( int id, Alimento alimentoUpdate);
	
	void deleteAlimento(int id);




}
