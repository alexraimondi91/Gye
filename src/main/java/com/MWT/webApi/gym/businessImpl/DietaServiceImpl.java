package com.MWT.webApi.gym.businessImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MWT.webApi.gym.XmlConvert.DietaXMLConvert;
import com.MWT.webApi.gym.business.DietaService;
import com.MWT.webApi.gym.model.Alimento;
import com.MWT.webApi.gym.model.Dieta;
import com.MWT.webApi.gym.repository.DietaRepository;
import com.your_company.dieta.TipoDieta;



@Service
public class DietaServiceImpl implements DietaService {

	@Autowired
	private DietaRepository dietaRepository;

	@Autowired
	private DietaXMLConvert dietaConvert;

	@Override
	public TipoDieta getDieta(int idUser) {

		Dieta dieta = dietaRepository.findByIdUtente(idUser);

		if (dieta != null) {
			return dietaConvert.convert(dieta);
		}

		return null;
	}
	
	@Override
	public List<Alimento> getAlimenti(int idUser) {
		
		List<Alimento> alimenti = new ArrayList<Alimento>();

		Dieta dieta = dietaRepository.findByIdUtente(idUser);

		if (dieta != null) {
			alimenti = dieta.getAlimenti();
			
			return alimenti;
		}

		return null;
	}
	
	

	
}
