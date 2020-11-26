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

import com.MWT.webApi.gym.businessImpl.AuthServiceImpl;
import com.MWT.webApi.gym.businessImpl.UserDetailsImpl;
import com.MWT.webApi.gym.jwt.JwtUtils;
import com.MWT.webApi.gym.model.JwtResponse;
import com.MWT.webApi.gym.model.LoginRequest;
import com.MWT.webApi.gym.model.MessageResponse;
import com.MWT.webApi.gym.model.Ruolo;
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
	AuthServiceImpl authServiceImpl;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		
		return authServiceImpl.authenticateUser(loginRequest);
	}

	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		return authServiceImpl.registerUser(signUpRequest);
	}

}
