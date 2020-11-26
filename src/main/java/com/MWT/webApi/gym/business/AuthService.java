package com.MWT.webApi.gym.business;

import org.springframework.http.ResponseEntity;

import com.MWT.webApi.gym.model.LoginRequest;
import com.MWT.webApi.gym.model.SignupRequest;

public interface AuthService {

	ResponseEntity<?> registerUser(SignupRequest signUpRequest);

	ResponseEntity<?> authenticateUser(LoginRequest loginRequest);

}
