package com.MWT.webApi.gym.businessImpl;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MWT.webApi.gym.business.EsercizioService;
import com.MWT.webApi.gym.exception.ResourceNotFoundException;
import com.MWT.webApi.gym.model.Alimento;
import com.MWT.webApi.gym.model.Dieta;
import com.MWT.webApi.gym.model.Esercizio;
import com.MWT.webApi.gym.model.SchedaEsercizio;
import com.MWT.webApi.gym.repository.SchedaEsercizioRepository;

@Service
public class EsercizioServiceImpl implements EsercizioService{
	
	@Autowired
	private com.MWT.webApi.gym.repository.EsercizioRepository esercizioRepository;
	
	@Autowired
	private SchedaEsercizioRepository schedaEsercizioRepository;

	@Override
	public List<Esercizio> getAllEsercizi() {
		
		return esercizioRepository.findAll();
	}

	@Override
	public Esercizio getEsercizio(int id) {
		
		Esercizio esercizio = new Esercizio();
		try {
			esercizio = esercizioRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Esercizio not found :: " + id));
		} catch (ResourceNotFoundException e) {
			e.printStackTrace();
		}
		
		return esercizio;
	}
	

	@Override
	public Esercizio createEsercizio(Esercizio esercizio) {
		
		return esercizioRepository.save(esercizio);
	}

	@Override
	public Esercizio updateEsercizio(int id, Esercizio esercizioUpdate) {
		Esercizio esercizio = new Esercizio();
		try {
			esercizio = esercizioRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Esercizio not found :: " + id));
		} catch (ResourceNotFoundException e) {
			e.printStackTrace();
			return null;

		}
		esercizio.setId(id);
		esercizio.setDescrizione(esercizioUpdate.getDescrizione());
		esercizio.setKg(esercizioUpdate.getKg());
		esercizio.setNome(esercizioUpdate.getNome());
		esercizio.setRipetizioni(esercizioUpdate.getRipetizioni());
		esercizio.setUrl(esercizioUpdate.getUrl());		
		final Esercizio esercizioSave = esercizioRepository.save(esercizio);
		
		return esercizioSave;
	}

	@Override
	public boolean deleteEsercizio(int id) {
		
		Esercizio esercizio = new Esercizio();
		List<SchedaEsercizio> schedaEsercizi = new ArrayList<SchedaEsercizio>();

		boolean delete = true;
		try {
			esercizio = esercizioRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Esercizio not found :: " + id));
		} catch (ResourceNotFoundException e) {
			e.printStackTrace();
		}
		
		schedaEsercizi = schedaEsercizioRepository.findAll();

		for (SchedaEsercizio schedaEsercizio : schedaEsercizi) {

			for (Esercizio esercizioDiScheda : schedaEsercizio.getEsercizi()) {

				if (esercizioDiScheda.getId() == id) {

					delete = false;
				}

			}
		}

		if (delete) {

			esercizioRepository.delete(esercizio);

		}
		
		return delete;
		
	}	

}
