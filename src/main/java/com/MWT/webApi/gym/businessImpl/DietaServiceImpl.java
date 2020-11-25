package com.MWT.webApi.gym.businessImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MWT.webApi.gym.XmlConvert.DietaXMLConvert;
import com.MWT.webApi.gym.business.DietaService;
import com.MWT.webApi.gym.exception.ResourceNotFoundException;
import com.MWT.webApi.gym.model.Alimento;
import com.MWT.webApi.gym.model.Dieta;
import com.MWT.webApi.gym.model.Utente;
import com.MWT.webApi.gym.repository.DietaRepository;
import com.your_company.dieta.TipoDieta;

@Service
public class DietaServiceImpl implements DietaService {

	@Autowired
	private DietaRepository dietaRepository;

	@Autowired
	private DietaXMLConvert dietaConvert;

	@Override
	public TipoDieta getDieta(Utente utente) {
		
		List<Dieta> diete = dietaRepository.findByUtente(utente);
		TipoDieta tipoDieta = new TipoDieta();
		
		Dieta dietaActive = new Dieta();
		for(Dieta dieta : diete) {
			if(dieta.active) {
				dietaActive = dieta;
				
			}
		}

		if (dietaActive != null) {		
			
			try {
				
				tipoDieta = dietaConvert.convert(dietaActive);
				
			}catch(Exception e){
				return null;
			}
			
		}

		return tipoDieta;
		
	}
	
	@Override
	public List<Alimento> getAlimenti(Utente utente) {
		
		List<Alimento> alimenti = new ArrayList<Alimento>();
		List<Dieta> diete = dietaRepository.findByUtente(utente);
		Dieta dietaActive = new Dieta();
		for(Dieta dieta : diete) {
			if(dieta.active) {
				dietaActive = dieta;
			}
		}

		if (dietaActive != null) {
			return alimenti = dietaActive.getAlimenti();
		}

		return null;
	}

	@Override
	public List<Dieta> getAllDieta() {
		
		return dietaRepository.findAll();

	}

	@Override
	public Dieta getDieta(int id) {
		
		Dieta dieta = new Dieta();
		try {
			dieta = dietaRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Dieta not found :: " + id));
		} catch (ResourceNotFoundException e) {
			e.printStackTrace();
		}
		
		return dieta;
		
	}

	@Override
	public Dieta createDieta(Dieta dieta) {
		
		return dietaRepository.save(dieta);
	}

	@Override
	public Dieta updateDieta(int id, Dieta dietaUpdate) {
		
		Dieta dieta = new Dieta();
		try {
			dieta = dietaRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Dieta not found :: " + id));
		} catch (ResourceNotFoundException e) {
			e.printStackTrace();
		}
		dieta.setId(id);
		dieta.setAlimenti(dietaUpdate.getAlimenti());
		dieta.setDataScadenza(dietaUpdate.getDataScadenza());
		dieta.setInfo(dietaUpdate.getInfo());
		dieta.setKcal(dietaUpdate.getKcal());
		dieta.setNome(dietaUpdate.getNome());
		dieta.setUtente(dietaUpdate.getUtente());
		
		final Dieta dietaSave = dietaRepository.save(dieta);
		
		return dietaSave;

	}

	@Override
	public boolean deleteDieta(int id) {
		
		Dieta dieta = new Dieta();
		boolean delete = true;
		try {
			dieta = dietaRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Dieta not found :: " + id));
			dietaRepository.delete(dieta);

		} catch (ResourceNotFoundException e) {
			e.printStackTrace();
			return delete = false;
		}	
		
		return delete;
		
	}
	
	

	
}
