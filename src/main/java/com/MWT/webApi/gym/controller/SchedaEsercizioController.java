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

import com.MWT.webApi.gym.businessImpl.SchedaEsercizioServiceImpl;
import com.MWT.webApi.gym.exception.ResourceNotFoundException;
import com.MWT.webApi.gym.model.SchedaEsercizio;

@Component
@Path("/api/v1")
public class SchedaEsercizioController {
	
	@Autowired
	private SchedaEsercizioServiceImpl schedaEsercizioServiceImpl;
	
	@GET
	@Path("/scheda-esercizi")
	@Produces("application/json")
	@PreAuthorize("hasRole('ADMIN')")	
	public List<SchedaEsercizio> getAlSchedaEsercizi() {
		return schedaEsercizioServiceImpl.getAllSchedaEsercizi();
	}

	@GET
	@Path("/scheda-esercizio/{id}")
	@Produces("application/json")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")	
	public ResponseEntity<SchedaEsercizio> getSchedaEsercizio(@PathParam(value = "id") int id) {
		SchedaEsercizio schedaEsercizio = schedaEsercizioServiceImpl.getSchedaEsercizio(id);
		return ResponseEntity.ok().body(schedaEsercizio);
	}

	@POST
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/scheda-esercizio")
	@PostMapping("/scheda-esercizi")
	@PreAuthorize("hasRole('ADMIN')")	

	public SchedaEsercizio createSchedaEsercizio(SchedaEsercizio schedaEsercizio) {
		
		return schedaEsercizioServiceImpl.createSchedaEsercizio(schedaEsercizio);
	}

	@PUT
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/scheda-esercizio/{id}")
	@PreAuthorize("hasRole('ADMIN')")	
	public ResponseEntity<SchedaEsercizio> updateSchedaEsercizio(@PathParam(value = "id") int id,
			@Valid @RequestBody SchedaEsercizio schedaEsercizioUpdate) throws ResourceNotFoundException {
		
		SchedaEsercizio schedaEsercizio = schedaEsercizioServiceImpl.updateSchedaEsercizio(id, schedaEsercizioUpdate);		
		return ResponseEntity.ok(schedaEsercizio);
	}

	@DELETE
	@Path("/scheda-esercizio/{id}")
	@Produces("application/json")
	@Consumes("application/json")
	@PreAuthorize("hasRole('ADMIN')")	
	public Map<String, Boolean> deleteSchedaEsercizio(@PathParam(value = "id") int id) throws ResourceNotFoundException {
		
		boolean deleted = schedaEsercizioServiceImpl.deleteSchedaEsercizio(id);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", deleted);
		return response;
	}

}
