package com.MWT.webApi.gym.soap;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.MWT.webApi.gym.businessImpl.DietaServiceImpl;

import webapi.mastermwt.org.dieta.TipoDieta;
import webapi.mastermwt.org.dieta.TipoidUtente;

@Endpoint
public class DietaEndPointSoap {
	
	private static final String NAMESPACE_URI = "http:/org.masterMWT.webAPI/Dieta.wsdl";
	 
    @Autowired
    private DietaServiceImpl dietaServiceImpl;
 
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetDietaPerUtente")
    @ResponsePayload
    public TipoDieta GetDietaPerUtenteRequest(@RequestPayload TipoidUtente idUtente) {
        
        TipoDieta response = dietaServiceImpl.getDieta(idUtente.getIdUtente());    
        return response;
    }

}
