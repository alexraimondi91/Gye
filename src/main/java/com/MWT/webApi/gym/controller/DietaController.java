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
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.MWT.webApi.gym.businessImpl.AlimentoServiceImpl;
import com.MWT.webApi.gym.businessImpl.DietaServiceImpl;
import com.MWT.webApi.gym.exception.ResourceNotFoundException;
import com.MWT.webApi.gym.model.Alimento;
import com.MWT.webApi.gym.model.Dieta;
import com.MWT.webApi.gym.model.Utente;

@Component
@Path("/api/v1/diete")
public class DietaController {
	
	@Autowired
	private DietaServiceImpl dietaServiceImpl;

	@GET
	@Path("/")
	@Produces("application/json")
	@PreAuthorize("hasRole('ADMIN')")	
	public List<Dieta> getAllDiete() {
		return dietaServiceImpl.getAllDieta();
	}
	
	@GET
	@Path("/{id}/utente")
	@Produces("application/json")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")	
	public ResponseEntity<Utente> getUtenteScheda(@PathParam(value = "id") int id) {
		Utente utente = dietaServiceImpl.getUtenteDieta(id);
		return ResponseEntity.ok().body(utente);
	}

	@GET
	@Path("/{id}")
	@Produces("application/json")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")	
	public ResponseEntity<Dieta> getDieta(@PathParam(value = "id") int id) {
		
		Dieta dieta = dietaServiceImpl.getDieta(id);
		return ResponseEntity.ok().body(dieta);
	}

	@POST
	@Consumes("application/json")
	@Path("/")
	@PostMapping("/")
	@PreAuthorize("hasRole('ADMIN')")	

	public Response createDieta(Dieta dieta) {
		
		dietaServiceImpl.createDieta(dieta);		
		return Response
			      .status(Response.Status.OK)
			      .build();
	}

	@PUT
	@Consumes("application/json")
	@Path("/{id}")
	@PreAuthorize("hasRole('ADMIN')")	
	public Response updateDieta(@PathParam(value = "id") int id,
			@Valid @RequestBody Dieta dietaUpdate) throws ResourceNotFoundException {
		
		Dieta update = dietaServiceImpl.updateDieta(id, dietaUpdate);	
		
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
	public Response deleteDieta(@PathParam(value = "id") int id) throws ResourceNotFoundException {
		
		boolean deleted = dietaServiceImpl.deleteDieta(id);
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
