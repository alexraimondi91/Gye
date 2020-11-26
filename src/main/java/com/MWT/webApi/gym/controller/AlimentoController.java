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
import com.MWT.webApi.gym.exception.ResourceNotFoundException;
import com.MWT.webApi.gym.model.Alimento;
import com.MWT.webApi.gym.repository.AlimentoRepository;

@Component
@Path("/api/v1/alimenti")
public class AlimentoController {
	
	@Autowired
	private AlimentoServiceImpl alimentoServiceImpl;

	@GET
	@Path("/")
	@Produces("application/json")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")	
	public List<Alimento> getAllAlimenti() {
		return alimentoServiceImpl.getAllAlimenti();
	}

	@GET
	@Path("/{id}")
	@Produces("application/json")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")	
	public ResponseEntity<Alimento> getAlimento(@PathParam(value = "id") int id) {
		Alimento alimento = alimentoServiceImpl.getAlimento(id);
		return ResponseEntity.ok().body(alimento);
	}

	@POST
	@Consumes("application/json")
	@Path("/")
	@PostMapping("/")
	@PreAuthorize("hasRole('ADMIN')")	

	public Response createAlimento(Alimento alimento) {
		
		alimentoServiceImpl.createAlimento(alimento);
		return Response
			      .status(Response.Status.OK)
			      .build();
	}

	@PUT
	@Consumes("application/json")
	@Path("/{id}")
	@PreAuthorize("hasRole('ADMIN')")	
	public Response updateAlimento(@PathParam(value = "id") int id,
			@Valid @RequestBody Alimento alimentoUpdate) throws ResourceNotFoundException {
		
		alimentoServiceImpl.updateAlimento(id, alimentoUpdate);		
		return Response
			      .status(Response.Status.OK)
			      .build();
	}

	@DELETE
	@Path("/{id}")
	@Consumes("application/json")
	@PreAuthorize("hasRole('ADMIN')")	
	public Response deleteAlimento(@PathParam(value = "id") int id) throws ResourceNotFoundException {
		
		boolean deleted = alimentoServiceImpl.deleteAlimento(id);
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
