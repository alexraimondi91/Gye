package com.MWT.webApi.gym.business;

import java.util.List;

import com.MWT.webApi.gym.model.Esercizio;
import com.MWT.webApi.gym.model.Utente;

public interface UtenteService {
	
	List<Utente> getAllUtente();		

	Utente updateUtente( int id, Utente esercizioUtente);
	
	void deleteUtente(int id);
	
	Utente getUtente(int id);

}
