package com.MWT.webApi.gym.business;

import java.util.List;

import com.MWT.webApi.gym.model.Esercizio;
import com.MWT.webApi.gym.model.SchedaEsercizio;

public interface SchedaEsercizioService {
	
	List<SchedaEsercizio> getAllSchedaEsercizi();
	
	SchedaEsercizio getSchedaEsercizio(int id);	
		
	SchedaEsercizio createSchedaEsercizio(SchedaEsercizio schedaEsercizio);
		
	SchedaEsercizio updateSchedaEsercizio( int id, SchedaEsercizio schedaEsercizioUpdate);
	
	void deleteSchedaEsercizio(int id);

}
