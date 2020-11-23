package com.MWT.webApi.gym.businessImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.MWT.webApi.gym.business.UtenteService;
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

}
