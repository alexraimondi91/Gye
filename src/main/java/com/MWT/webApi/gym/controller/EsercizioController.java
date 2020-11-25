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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.MWT.webApi.gym.businessImpl.EsercizioServiceImpl;
import com.MWT.webApi.gym.exception.ResourceNotFoundException;
import com.MWT.webApi.gym.model.Esercizio;

@Component
@Path("/api/v1")
public class EsercizioController {
	
	@Autowired
	private EsercizioServiceImpl esercizioServiceImpl;

	@GET
	@Path("/esercizi")
	@Produces("application/json")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")	
	public List<Esercizio> getAllEsercizi() {
		return esercizioServiceImpl.getAllEsercizi();
	}

	@GET
	@Path("/esercizio/{id}")
	@Produces("application/json")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")	
	public ResponseEntity<Esercizio> getEsercizio(@PathParam(value = "id") int id) {
		Esercizio esercizio = esercizioServiceImpl.getEsercizio(id);
		return ResponseEntity.ok().body(esercizio);
	}

	@POST
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/esercizio")
	@PostMapping("/esercizio")
	@PreAuthorize("hasRole('ADMIN')")	

	public Esercizio createEsercizio(Esercizio esercizio) {
		
		return esercizioServiceImpl.createEsercizio(esercizio);
	}

	@PUT
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/esercizio/{id}")
	@PreAuthorize("hasRole('ADMIN')")	
	public ResponseEntity<Esercizio> updateAlimento(@PathParam(value = "id") int id,
			@Valid @RequestBody Esercizio esercizioUpdate) throws ResourceNotFoundException {
		
		Esercizio esercizio = esercizioServiceImpl.updateEsercizio(id, esercizioUpdate);		
		return ResponseEntity.ok(esercizio);
	}

	@DELETE
	@Path("/esercizio/{id}")
	@Produces("application/json")
	@Consumes("application/json")
	@PreAuthorize("hasRole('ADMIN')")	
	public Map<String, Boolean> deleteEsercizio(@PathParam(value = "id") int id) throws ResourceNotFoundException {
		
		boolean delete = esercizioServiceImpl.deleteEsercizio(id);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", delete);
		return response;
	}

}
