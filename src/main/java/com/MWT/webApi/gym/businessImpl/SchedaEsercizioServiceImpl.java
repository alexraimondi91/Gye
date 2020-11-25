package com.MWT.webApi.gym.businessImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.MWT.webApi.gym.business.SchedaEsercizioService;
import com.MWT.webApi.gym.exception.ResourceNotFoundException;
import com.MWT.webApi.gym.model.SchedaEsercizio;
import com.MWT.webApi.gym.repository.SchedaEsercizioRepository;

@Service
public class SchedaEsercizioServiceImpl implements SchedaEsercizioService{
	
	@Autowired
	private SchedaEsercizioRepository schedaEsercizioRepository;

	@Override
	public List<SchedaEsercizio> getAllSchedaEsercizi() {
		
		return schedaEsercizioRepository.findAll();
	}

	@Override
	public SchedaEsercizio getSchedaEsercizio(int id) {
		SchedaEsercizio schedaEsercizio = new SchedaEsercizio();
		try {
			schedaEsercizio = schedaEsercizioRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("SchedaEsercizio not found :: " + id));
		} catch (ResourceNotFoundException e) {
			e.printStackTrace();
		}
		
		return schedaEsercizio;
	}

	@Override
	public SchedaEsercizio createSchedaEsercizio(SchedaEsercizio schedaEsercizio) {
		
		return schedaEsercizioRepository.save(schedaEsercizio);
	}

	@Override
	public SchedaEsercizio updateSchedaEsercizio(int id, SchedaEsercizio schedaEsercizioUpdate) {
		SchedaEsercizio schedaEsercizio = new SchedaEsercizio();
		try {
			schedaEsercizio = schedaEsercizioRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("SchedaEsercizio not found :: " + id));
		} catch (ResourceNotFoundException e) {
			e.printStackTrace();
		}
		
		schedaEsercizio.setId(id);
		schedaEsercizio.setDescrizione(schedaEsercizioUpdate.getDescrizione());
		schedaEsercizio.setEsercizi(schedaEsercizioUpdate.getEsercizi());
		schedaEsercizio.setNome(schedaEsercizioUpdate.getNome());
		
        final SchedaEsercizio schedaEsercizioSave = schedaEsercizioRepository.save(schedaEsercizio);
		
		return schedaEsercizioSave;
	}

	@Override
	public boolean deleteSchedaEsercizio(int id) {
		
		SchedaEsercizio schedaEsercizio = new SchedaEsercizio();
		boolean delete = true;
		try {
			schedaEsercizio = schedaEsercizioRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("SchedaEsercizio not found :: " + id));
			
			schedaEsercizioRepository.delete(schedaEsercizio);

		} catch (ResourceNotFoundException e) {
			e.printStackTrace();
			delete = false;
		}
		
		return delete;
		
		
	}

}
