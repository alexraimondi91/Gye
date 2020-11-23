package com.MWT.webApi.gym.controller;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MWT.webApi.gym.businessImpl.UserDetailsImpl;
import com.MWT.webApi.gym.jwt.JwtUtils;
import com.MWT.webApi.gym.model.JwtResponse;
import com.MWT.webApi.gym.model.LoginRequest;
import com.MWT.webApi.gym.model.MessageResponse;
import com.MWT.webApi.gym.model.Ruoli;
import com.MWT.webApi.gym.model.SignupRequest;
import com.MWT.webApi.gym.model.TipiRuoli;
import com.MWT.webApi.gym.model.Utente;
import com.MWT.webApi.gym.repository.RuoliRepository;
import com.MWT.webApi.gym.repository.UtenteRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UtenteRepository utenteRepository;

	@Autowired
	RuoliRepository ruoliRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt, 
												 userDetails.getId(), 
												 userDetails.getUsername(), 
												 userDetails.getEmail(), 
												 roles));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (utenteRepository.existsByUserName(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}

		if (utenteRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}

		// Create new user's account
		Utente user = new Utente(0,signUpRequest.getNome(),signUpRequest.getCognome(),signUpRequest.getEmail(), signUpRequest.getDataNascita(), signUpRequest.getUsername(), 
							 
							 encoder.encode(signUpRequest.getPassword()), null, null, null);

		Set<String> strRoles = signUpRequest.getRole();
		List<Ruoli> roles = new ArrayList<>();

		if (strRoles == null) {
			Ruoli userRole = ruoliRepository.findByNome(TipiRuoli.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Ruoli adminRole = ruoliRepository.findByNome(TipiRuoli.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

					break;
				
				default:
					Ruoli userRole = ruoliRepository.findByNome(TipiRuoli.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}

		user.setRoles(roles);
		utenteRepository.save(user);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}

}
