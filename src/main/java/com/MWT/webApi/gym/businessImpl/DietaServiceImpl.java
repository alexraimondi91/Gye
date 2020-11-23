package com.MWT.webApi.gym.businessImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MWT.webApi.gym.XmlConvert.DietaXMLConvert;
import com.MWT.webApi.gym.business.DietaService;
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

		Dieta dieta = dietaRepository.findByUtente(utente);

		if (dieta != null) {
			return dietaConvert.convert(dieta);
		}

		return null;
	}
	
	@Override
	public List<Alimento> getAlimenti(Utente utente) {
		
		List<Alimento> alimenti = new ArrayList<Alimento>();
		Dieta dieta = dietaRepository.findByUtente(utente);

		if (dieta != null) {
			return alimenti = dieta.getAlimenti();
		}

		return null;
	}
	
	

	
}
