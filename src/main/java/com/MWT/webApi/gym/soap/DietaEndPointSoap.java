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
import com.MWT.webApi.gym.model.Alimento;
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
	private AlimentoXMLConvert alimentoXMLConvert;


	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetDietaPerUtenteRequest")

	@ResponsePayload
	public GetDietaPerUtenteResponse GetDietaPerUtenteRequest(@RequestPayload GetDietaPerUtenteRequest idUtente) {
		ObjectFactory factory = new ObjectFactory();
		GetDietaPerUtenteResponse response = factory.createGetDietaPerUtenteResponse();
		TipoDieta found = dietaServiceImpl.getDieta(idUtente.getIdUtente());
		List<TipoAlimento> tipoAlimenti = found.getAlimenti();
		List<Alimento> listalimenti = dietaServiceImpl.getAlimenti(idUtente.getIdUtente());

		for (Alimento alimento : listalimenti) {
			found.getAlimenti().add(alimentoXMLConvert.convert(alimento));
		}
		
		response.setDieta(found);
		
		System.out.println("\n\n\n" + tipoAlimenti + "\n\n\n");
		return response;
	}

}
