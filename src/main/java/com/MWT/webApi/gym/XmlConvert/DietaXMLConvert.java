package com.MWT.webApi.gym.XmlConvert;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.MWT.webApi.gym.model.Alimento;
import com.MWT.webApi.gym.model.Dieta;
import com.your_company.dieta.TipoAlimento;
import com.your_company.dieta.TipoDieta;





@Component
public class DietaXMLConvert {
	
	@Autowired
	private AlimentoXMLConvert alimentoXMLConvert;
	
	public TipoDieta convert (Dieta dieta) {
		
		TipoDieta dietaXML = new TipoDieta();

		BeanUtils.copyProperties(dieta, dietaXML);
		GregorianCalendar gcalInserimento = new GregorianCalendar();
		GregorianCalendar gcalFine = new GregorianCalendar();
		gcalInserimento.setTime(dieta.getDataInserimento());
		gcalFine.setTime(dieta.getDataScadenza());
	    XMLGregorianCalendar xmlDataInserimento = null;
	    XMLGregorianCalendar xmlDataFine = null;

		try {
			xmlDataInserimento = DatatypeFactory.newInstance()
					.newXMLGregorianCalendar(gcalInserimento);
			xmlDataFine = DatatypeFactory.newInstance()
					.newXMLGregorianCalendar(gcalFine);
		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
		}
		dietaXML.setDataInserimento(xmlDataInserimento);
		dietaXML.setDataScadenza(xmlDataFine);
		dietaXML.setId(dieta.getId());
		dietaXML.setIdUtente((dieta.getId()));
		dietaXML.setInfo(dieta.getInfo());
		dietaXML.setNome(dieta.getNome());
		dietaXML.setKcal(dieta.getKcal());
		List<Alimento> setAlimenti = dieta.getAlimenti();
		List<TipoAlimento> listAlimenti = new ArrayList<TipoAlimento>();		
		for(Alimento alimento : setAlimenti) {
			listAlimenti.add(alimentoXMLConvert.convert(alimento));
		}
		  
		//dietaXML.setAlimenti(listAlimenti);		
		return dietaXML;
		
	}

}
