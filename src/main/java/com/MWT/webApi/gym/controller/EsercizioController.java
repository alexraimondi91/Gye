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

import com.MWT.webApi.gym.businessImpl.EsercizioServiceImpl;
import com.MWT.webApi.gym.exception.ResourceNotFoundException;
import com.MWT.webApi.gym.model.Esercizio;

@Component
@Path("/api/v1/esercizi")
public class EsercizioController {
	
	@Autowired
	private EsercizioServiceImpl esercizioServiceImpl;

	@GET
	@Path("/")
	@Produces("application/json")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")	
	public List<Esercizio> getAllEsercizi() {
		return esercizioServiceImpl.getAllEsercizi();
	}

	@GET
	@Path("/{id}")
	@Produces("application/json")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")	
	public ResponseEntity<Esercizio> getEsercizio(@PathParam(value = "id") int id) {
		Esercizio esercizio = esercizioServiceImpl.getEsercizio(id);
		return ResponseEntity.ok().body(esercizio);
	}

	@POST
	@Consumes("application/json")
	@Path("/")
	@PostMapping("/")
	@PreAuthorize("hasRole('ADMIN')")	

	public Response createEsercizio(Esercizio esercizio) {
		
		esercizioServiceImpl.createEsercizio(esercizio);
		
		return Response
	      .status(Response.Status.OK)
	      .build();

	}

	@PUT
	@Consumes("application/json")
	@Path("/{id}")
	@PreAuthorize("hasRole('ADMIN')")	
	public Response updateAlimento(@PathParam(value = "id") int id,
			@Valid @RequestBody Esercizio esercizioUpdate) throws ResourceNotFoundException {
		
		Esercizio update = esercizioServiceImpl.updateEsercizio(id, esercizioUpdate);	
		
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
	public Response deleteEsercizio(@PathParam(value = "id") int id) throws ResourceNotFoundException {
		
		boolean delete = esercizioServiceImpl.deleteEsercizio(id);
		if(delete) {
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
