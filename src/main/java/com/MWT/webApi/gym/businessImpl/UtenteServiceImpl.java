package com.MWT.webApi.gym.businessImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.MWT.webApi.gym.business.UtenteService;
import com.MWT.webApi.gym.exception.ResourceNotFoundException;
import com.MWT.webApi.gym.model.Dieta;
import com.MWT.webApi.gym.model.Esercizio;
import com.MWT.webApi.gym.model.MessageResponse;
import com.MWT.webApi.gym.model.Ruolo;
import com.MWT.webApi.gym.model.SchedaEsercizio;
import com.MWT.webApi.gym.model.SignupRequest;
import com.MWT.webApi.gym.model.TipiRuoli;
import com.MWT.webApi.gym.model.Utente;
import com.MWT.webApi.gym.repository.DietaRepository;
import com.MWT.webApi.gym.repository.RuoliRepository;
import com.MWT.webApi.gym.repository.SchedaEsercizioRepository;
import com.MWT.webApi.gym.repository.UtenteRepository;

@Service
public class UtenteServiceImpl implements UtenteService {

	@Autowired
	private UtenteRepository utenteRepository;

	@Autowired
	private DietaRepository dietaRepository;

	@Autowired
	private SchedaEsercizioRepository shedaEsercizioRepository;

	@Autowired
	private RuoliRepository ruoliRepository;

	@Autowired
	PasswordEncoder encoder;

	@Override
	public Utente getUtente(int id) {
		return utenteRepository.findById(id)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with id: " + id));

	}

	@Override
	public List<Utente> getAllUtente() {

		return utenteRepository.findAll();
	}

	@Override
	public List<Utente> getAllUtenteRole(TipiRuoli role) {

		return utenteRepository.findUserByRole(role);
	}

	
	@Override
	public List<Dieta> getDieteUtente(int id) {

		Utente utente = utenteRepository.findById(id)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with id: " + id));

		return dietaRepository.findByUtente(utente);
	}

	@Override
	public List<SchedaEsercizio> getSchedeUtente(int id) {

		Utente utente = utenteRepository.findById(id)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with id: " + id));
		List<SchedaEsercizio> schedaUtente = utente.getSchedaEsercizi();
		return schedaUtente;
	}

	@Override
	public Utente updateUser(SignupRequest signUpRequest, int id) {

		Utente user = utenteRepository.findById(id)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with id: " + id));
		if (user == null) {
			return null;
		}
		user.setNome(signUpRequest.getNome());
		user.setCognome(signUpRequest.getCognome());
		user.setEmail(signUpRequest.getEmail());
		user.setDataNascita(signUpRequest.getDataNascita());
		user.setUserName(signUpRequest.getUsername());
		user.setPassword(encoder.encode(signUpRequest.getPassword()));

		Set<String> strRoles = signUpRequest.getRole();
		List<Ruolo> roles = new ArrayList<>();

		if (strRoles == null) {
			Ruolo userRole = ruoliRepository.findByNome(TipiRuoli.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Ruolo adminRole = ruoliRepository.findByNome(TipiRuoli.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

					break;

				default:
					Ruolo userRole = ruoliRepository.findByNome(TipiRuoli.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}

		user.setRoles(roles);
		utenteRepository.save(user);

		return user;
	}

	@Override
	public boolean deleteUtente(int id) {
		Utente utente = new Utente();
		boolean delete = true;
		try {
			utente = utenteRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Utente not found :: " + id));
			utenteRepository.delete(utente);

		} catch (ResourceNotFoundException e) {
			e.printStackTrace();
			delete = false;
		}

		return delete;

	}

}
