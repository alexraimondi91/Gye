package com.MWT.webApi.gym.soap;

import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;

@SoapFault(faultCode = FaultCode.CUSTOM,
        customFaultCode = "{" + DietaNotFoundException.NAMESPACE_URI + "}custom_fault",
        faultStringOrReason = "non è stato trovata nessuna dieta attiva per questo utente!")
public class DietaNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;
	public static final String NAMESPACE_URI = "http://www.your-company.com/dieta.xsd1";

    public DietaNotFoundException(String message) {
        super(message);
    }
}