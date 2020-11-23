package com.MWT.webApi.gym.business;

import java.util.List;

import com.MWT.webApi.gym.model.Alimento;
import com.MWT.webApi.gym.model.Utente;
import com.your_company.dieta.TipoDieta;

public interface DietaService {
	
	TipoDieta getDieta (Utente utente);

	List<Alimento> getAlimenti(Utente utente);

}
