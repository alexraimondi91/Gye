package com.MWT.webApi.gym.business;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.MWT.webApi.gym.model.Dieta;
import com.MWT.webApi.gym.model.Esercizio;
import com.MWT.webApi.gym.model.SchedaEsercizio;
import com.MWT.webApi.gym.model.SignupRequest;
import com.MWT.webApi.gym.model.TipiRuoli;
import com.MWT.webApi.gym.model.Utente;

public interface UtenteService {
	
	List<Utente> getAllUtente();		

	boolean deleteUtente(int id);
	
	Utente getUtente(int id);

	Utente updateUser(SignupRequest signUpRequest, int id);

	List<Dieta> getDieteUtente(int id);

	List<SchedaEsercizio> getSchedeUtente(int id);

	List<Utente> getAllUtenteRole(TipiRuoli role);

}
