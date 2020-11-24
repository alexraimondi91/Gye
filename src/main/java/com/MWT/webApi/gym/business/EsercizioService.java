package com.MWT.webApi.gym.business;

import java.util.List;

import com.MWT.webApi.gym.model.Esercizio;

public interface EsercizioService {
	
	List<Esercizio> getAllEsercizi();
		
	Esercizio getEsercizio(int id);	
		
	Esercizio createEsercizio(Esercizio esercizio);
		
	Esercizio updateEsercizio( int id, Esercizio esercizioUpdate);
	
	boolean deleteEsercizio(int id);

}
