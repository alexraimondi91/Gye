package com.MWT.webApi.gym.businessImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MWT.webApi.gym.model.Dieta;
import com.MWT.webApi.gym.repository.DietaRepository;
import com.MWT.webApi.gym.service.DietaXMLConvert;

import webapi.mastermwt.org.dieta.TipoDieta;

@Service
public class DietaServiceImpl {

	@Autowired
	private DietaRepository dietaRepository;

	@Autowired
	private DietaXMLConvert dietaConvert;

	public TipoDieta getDieta(int idUser) {

		Dieta dieta = dietaRepository.findByIdUtente(idUser);

		if (dieta != null) {
			return dietaConvert.convert(dieta);
		}

		return null;
	}

}
