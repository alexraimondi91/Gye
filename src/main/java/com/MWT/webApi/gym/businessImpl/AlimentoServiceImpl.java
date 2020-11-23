package com.MWT.webApi.gym.businessImpl;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.ws.rs.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.MWT.webApi.gym.business.AlimentoService;
import com.MWT.webApi.gym.exception.ResourceNotFoundException;
import com.MWT.webApi.gym.model.Alimento;
import com.MWT.webApi.gym.model.Utente;
import com.MWT.webApi.gym.repository.AlimentoRepository;
import com.MWT.webApi.gym.repository.UtenteRepository;
import com.your_company.dieta.TipoAlimento;


@Service
public class AlimentoServiceImpl implements AlimentoService{
	
	@Autowired
	private AlimentoRepository alimentoRepository;
	
	public List<Alimento> getAllAlimenti() {
		return alimentoRepository.findAll();
	}
	
	@Override
	public Alimento getAlimento(int id) {
		Alimento alimento = new Alimento();
		try {
			alimento = alimentoRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Alimento not found :: " + id));
		} catch (ResourceNotFoundException e) {
			e.printStackTrace();
		}
		
		return alimento;
	}
	
	@Override
	public Alimento createAlimento(Alimento alimento) {
		return alimentoRepository.save(alimento);
	}
	
	@Override
	public Alimento updateAlimento( int id, Alimento alimentoUpdate) {
		Alimento alimento = new Alimento();
		try {
			alimento = alimentoRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Alimento not found :: " + id));
		} catch (ResourceNotFoundException e) {
			e.printStackTrace();
		}
		alimento.setNome(alimentoUpdate.getNome());
		alimento.setDescrizione(alimentoUpdate.getDescrizione());
		alimento.setGrassi(alimentoUpdate.getGrassi());
		alimento.setProteine(alimentoUpdate.getProteine());
		alimento.setCarboidrati(alimentoUpdate.getCarboidrati());
		final Alimento alimentoSave = alimentoRepository.save(alimento);
		
		return alimentoSave;
	}
	
	@Override
	public void deleteAlimento(int id){
		
		Alimento alimento = new Alimento();
		try {
			alimento = alimentoRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Alimento not found :: " + id));
		} catch (ResourceNotFoundException e) {
			e.printStackTrace();
		}

		alimentoRepository.delete(alimento);
		
	}


}
