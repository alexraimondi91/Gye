package com.MWT.webApi.gym.soap;

import com.your_company.dieta.ElementoErrore;

public class ServiceFaultException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private ElementoErrore elementoErrore;

	public ServiceFaultException(String message, ElementoErrore elementoErrore) {
		super(message);
		this.elementoErrore = elementoErrore;
	}

	public ServiceFaultException(String message, Throwable e, ElementoErrore elementoErrore) {
		super(message, e);
		this.elementoErrore = elementoErrore;
	}

	public ElementoErrore getServiceStatus() {
		return elementoErrore;
	}

	public void setServiceStatus(ElementoErrore serviceStatus) {
		this.elementoErrore = serviceStatus;
	}

}