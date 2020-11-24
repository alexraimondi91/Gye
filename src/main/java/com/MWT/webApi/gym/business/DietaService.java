package com.MWT.webApi.gym.business;

import java.util.List;

import com.MWT.webApi.gym.model.Alimento;
import com.MWT.webApi.gym.model.Dieta;
import com.MWT.webApi.gym.model.Esercizio;
import com.MWT.webApi.gym.model.Utente;
import com.your_company.dieta.TipoDieta;

public interface DietaService {
	
	List<Dieta> getAllDieta();
	
	Dieta getDieta(int id);	
		
	Dieta createDieta(Dieta dieta);
		
	Dieta updateDieta( int id, Dieta dietaUpdate);
	
	void deleteDieta(int id);
	
	TipoDieta getDieta (Utente utente);

	List<Alimento> getAlimenti(Utente utente);

}
