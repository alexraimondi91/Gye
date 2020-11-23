package com.MWT.webApi.gym.business;

import java.util.List;

import com.MWT.webApi.gym.model.Alimento;
import com.your_company.dieta.TipoDieta;

public interface DietaService {
	
	public TipoDieta getDieta (int idUser);

	List<Alimento> getAlimenti(int idUser);

}
