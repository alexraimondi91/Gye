package com.MWT.webApi.gym.soap;

import javax.xml.namespace.QName;

import org.springframework.ws.soap.SoapFault;
import org.springframework.ws.soap.SoapFaultDetail;
import org.springframework.ws.soap.server.endpoint.SoapFaultMappingExceptionResolver;

import com.your_company.dieta.ElementoErrore;


public class DetailSoapFaultDefinitionExceptionResolver extends SoapFaultMappingExceptionResolver {

	private static final QName CODE = new QName("statusCode");
	private static final QName MESSAGE = new QName("message");

	@Override
	protected void customizeFault(Object endpoint, Exception ex, SoapFault fault) {
		logger.warn("Exception processed ", ex);
		if (ex instanceof ServiceFaultException) {
			ElementoErrore status = ((ServiceFaultException) ex).getServiceStatus();
			SoapFaultDetail detail = fault.addFaultDetail();
			detail.addFaultDetailElement(CODE).addText((status.getErrore().getCodice()));
			detail.addFaultDetailElement(MESSAGE).addText(status.getErrore().getDescrizione());
		}
	}

}
