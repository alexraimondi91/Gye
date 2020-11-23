package com.MWT.webApi.gym.soap;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.MWT.webApi.gym.XmlConvert.AlimentoXMLConvert;
import com.MWT.webApi.gym.businessImpl.DietaServiceImpl;
import com.MWT.webApi.gym.businessImpl.UtenteServiceImpl;
import com.MWT.webApi.gym.model.Alimento;
import com.MWT.webApi.gym.model.Utente;
import com.your_company.dieta.GetDietaPerUtenteRequest;
import com.your_company.dieta.GetDietaPerUtenteResponse;

import com.your_company.dieta.ObjectFactory;
import com.your_company.dieta.TipoAlimento;
import com.your_company.dieta.TipoDieta;



@Endpoint
public class DietaEndPointSoap {

	private static final String NAMESPACE_URI = "http://www.your-company.com/dieta.xsd1";

	@Autowired
	private DietaServiceImpl dietaServiceImpl;
	
	@Autowired
	private UtenteServiceImpl utenteServiceImpl;
	
	@Autowired
	private AlimentoXMLConvert alimentoXMLConvert;


	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetDietaPerUtenteRequest")

	@ResponsePayload
	public GetDietaPerUtenteResponse GetDietaPerUtenteRequest(@RequestPayload GetDietaPerUtenteRequest idUtente) {
		Utente utente = utenteServiceImpl.getUtente(idUtente.getIdUtente()) ;
		ObjectFactory factory = new ObjectFactory();
		GetDietaPerUtenteResponse response = factory.createGetDietaPerUtenteResponse();
		TipoDieta found = dietaServiceImpl.getDieta(utente);
		List<Alimento> listalimenti = dietaServiceImpl.getAlimenti(utente);

		for (Alimento alimento : listalimenti) {
			found.getAlimenti().add(alimentoXMLConvert.convert(alimento));
		}
		
		response.setDieta(found);

		return response;
	}

}
