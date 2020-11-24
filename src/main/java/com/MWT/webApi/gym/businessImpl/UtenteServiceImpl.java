package com.MWT.webApi.gym.businessImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.MWT.webApi.gym.business.UtenteService;
import com.MWT.webApi.gym.exception.ResourceNotFoundException;
import com.MWT.webApi.gym.model.Esercizio;
import com.MWT.webApi.gym.model.Utente;
import com.MWT.webApi.gym.repository.UtenteRepository;

@Service
public class UtenteServiceImpl implements UtenteService{
	
	@Autowired
	private UtenteRepository utenteRepository;
	
	@Override
	public Utente getUtente(int id) {
		return utenteRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User Not Found with id: " + id));

	}

	@Override
	public List<Utente> getAllUtente() {
		
		return utenteRepository.findAll();
	}

	@Override
	public Utente updateUtente(int id, Utente utenteUpdate) {
		Utente utente = new Utente();
		try {
			utente = utenteRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Utente not found :: " + id));
		} catch (ResourceNotFoundException e) {
			e.printStackTrace();
		}
		
        final Utente utenteSave = utenteRepository.save(utente);
		
		return utenteSave;
	}

	@Override
	public void deleteUtente(int id) {
		Utente utente = new Utente();
		try {
			utente = utenteRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Utente not found :: " + id));
		} catch (ResourceNotFoundException e) {
			e.printStackTrace();
		}
		
		utenteRepository.delete(utente);
		
	}

}
