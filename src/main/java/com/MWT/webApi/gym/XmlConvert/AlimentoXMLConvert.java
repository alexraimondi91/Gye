package com.MWT.webApi.gym.XmlConvert;

import org.springframework.stereotype.Component;

import com.MWT.webApi.gym.model.Alimento;
import com.your_company.dieta.TipoAlimento;




@Component
public class AlimentoXMLConvert {
	
	public TipoAlimento convert(Alimento alimento) {
		
        TipoAlimento alimentoXml = new TipoAlimento();		
		alimentoXml.setCarboidrati(alimento.getCarboidrati());
		alimentoXml.setDescrizione(alimento.getDescrizione());
		alimentoXml.setGrassi(alimento.getGrassi());
		alimentoXml.setId(alimento.getId());
		alimentoXml.setNome(alimento.getNome());
		alimentoXml.setProteine(alimento.getProteine());
		
		return alimentoXml;
		
	}

}
