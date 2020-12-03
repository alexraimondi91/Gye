package com.MWT.webApi.gym.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.MWT.webApi.gym.exception.ResourceNotFoundException;
import com.MWT.webApi.gym.model.Dieta;
import com.MWT.webApi.gym.model.SchedaEsercizio;
import com.MWT.webApi.gym.model.SignupRequest;
import com.MWT.webApi.gym.model.TipiRuoli;
import com.MWT.webApi.gym.model.Utente;

@Component
@Path("/api/v1/utenti")
public class UtenteController {
	
	@Autowired
	private com.MWT.webApi.gym.businessImpl.UtenteServiceImpl utenteServiceImpl;
	
	@GET
	@Path("/")
	@Produces("application/json")
	@PreAuthorize("hasRole('ADMIN')")	
	public List<Utente> getAllUtenti() {
		return utenteServiceImpl.getAllUtente();
	}

	
	@GET
	@Path("/user")
	@Produces("application/json")
	@PreAuthorize("hasRole('ADMIN')")	
	public List<Utente> getAllUtentiRuoloUser() {
		return utenteServiceImpl.getAllUtenteRole(TipiRuoli.ROLE_USER);
	}
	
	@GET
	@Path("/admin")
	@Produces("application/json")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")	
	public List<Utente> getAllUtentiRuoloAdmin() {
		return utenteServiceImpl.getAllUtenteRole(TipiRuoli.ROLE_ADMIN);
	}
	
	@GET
	@Path("/{id}/diete")
	@Produces("application/json")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")	
	public List<Dieta> getDietePerUtente(@PathParam(value = "id") int id) {
		return utenteServiceImpl.getDieteUtente(id);
	}
	
	@GET
	@Path("/{id}/schede/")
	@Produces("application/json")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")	
	public List<SchedaEsercizio> getSchedaPerUtente(@PathParam(value = "id") int id) {
		return utenteServiceImpl.getSchedeUtente(id);
	}

	@GET
	@Path("/{id}")
	@Produces("application/json")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")	
	public ResponseEntity<Utente> getUtente(@PathParam(value = "id") int id) {
		Utente utente = utenteServiceImpl.getUtente(id);
		return ResponseEntity.ok().body(utente);
	}
	
	@PUT
	@Consumes("application/json")
	@Path("/{id}")
	@PreAuthorize("hasRole('ADMIN')")	
	public Response updateUtente(@PathParam(value = "id") int id,
			@Valid @RequestBody SignupRequest signupRequest) throws ResourceNotFoundException {
		
		Utente update = utenteServiceImpl.updateUser(signupRequest,id);		
		
		if(update == null) {
			return Response
				      .status(Response.Status.BAD_REQUEST)
				      .build();
		}
		return Response
	      .status(Response.Status.OK)
	      .build();
	}


	@DELETE
	@Path("/{id}")
	@Consumes("application/json")
	@PreAuthorize("hasRole('ADMIN')")	
	public Response deleteSchedaEsercizio(@PathParam(value = "id") int id) throws ResourceNotFoundException {
		
		boolean deleted = utenteServiceImpl.deleteUtente(id);
		if(deleted) {
			return Response
				      .status(Response.Status.OK)
				      .build();
		}
		else {
			return Response
				      .status(Response.Status.INTERNAL_SERVER_ERROR)
				      .build();
		}
	}
	
	

}
