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

import com.MWT.webApi.gym.businessImpl.SchedaEsercizioServiceImpl;
import com.MWT.webApi.gym.exception.ResourceNotFoundException;
import com.MWT.webApi.gym.model.SchedaEsercizio;

@Component
@Path("/api/v1/schede")
public class SchedaEsercizioController {
	
	@Autowired
	private SchedaEsercizioServiceImpl schedaEsercizioServiceImpl;
	
	@GET
	@Path("/")
	@Produces("application/json")
	@PreAuthorize("hasRole('ADMIN')")	
	public List<SchedaEsercizio> getAlSchedaEsercizi() {
		return schedaEsercizioServiceImpl.getAllSchedaEsercizi();
	}

	@GET
	@Path("/{id}")
	@Produces("application/json")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")	
	public ResponseEntity<SchedaEsercizio> getSchedaEsercizio(@PathParam(value = "id") int id) {
		SchedaEsercizio schedaEsercizio = schedaEsercizioServiceImpl.getSchedaEsercizio(id);
		return ResponseEntity.ok().body(schedaEsercizio);
	}

	@POST
	@Consumes("application/json")
	@Path("/")
	@PostMapping("/")
	@PreAuthorize("hasRole('ADMIN')")	

	public Response createSchedaEsercizio(SchedaEsercizio schedaEsercizio) {
		
		schedaEsercizioServiceImpl.createSchedaEsercizio(schedaEsercizio);

		return Response
			      .status(Response.Status.OK)
			      .build();
	}

	@PUT
	@Consumes("application/json")
	@Path("/{id}")
	@PreAuthorize("hasRole('ADMIN')")	
	public Response updateSchedaEsercizio(@PathParam(value = "id") int id,
			@Valid @RequestBody SchedaEsercizio schedaEsercizioUpdate) throws ResourceNotFoundException {
		
		 schedaEsercizioServiceImpl.updateSchedaEsercizio(id, schedaEsercizioUpdate);		
		 return Response
			      .status(Response.Status.OK)
			      .build();
	}

	@DELETE
	@Path("/{id}")
	@Consumes("application/json")
	@PreAuthorize("hasRole('ADMIN')")	
	public Response deleteSchedaEsercizio(@PathParam(value = "id") int id) throws ResourceNotFoundException {
		
		boolean deleted = schedaEsercizioServiceImpl.deleteSchedaEsercizio(id);		
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
