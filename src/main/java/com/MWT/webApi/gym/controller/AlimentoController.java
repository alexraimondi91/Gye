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

import com.MWT.webApi.gym.businessImpl.AlimentoServiceImpl;
import com.MWT.webApi.gym.exception.ResourceNotFoundException;
import com.MWT.webApi.gym.model.Alimento;
import com.MWT.webApi.gym.repository.AlimentoRepository;

@Component
@Path("/api/v1")
public class AlimentoController {
	
	@Autowired
	private AlimentoServiceImpl alimentoServiceImpl;

	@GET
	@Path("/alimenti")
	@Produces("application/json")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")	
	public List<Alimento> getAllAlimenti() {
		return alimentoServiceImpl.getAllAlimenti();
	}

	@GET
	@Path("/alimento/{id}")
	@Produces("application/json")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")	
	public ResponseEntity<Alimento> getAlimento(@PathParam(value = "id") int id) {
		Alimento alimento = alimentoServiceImpl.getAlimento(id);
		return ResponseEntity.ok().body(alimento);
	}

	@POST
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/alimento")
	@PostMapping("/alimento")
	@PreAuthorize("hasRole('ADMIN')")	

	public Alimento createAlimento(Alimento alimento) {
		
		return alimentoServiceImpl.createAlimento(alimento);
	}

	@PUT
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/alimento/{id}")
	@PreAuthorize("hasRole('ADMIN')")	
	public ResponseEntity<Alimento> updateAlimento(@PathParam(value = "id") int id,
			@Valid @RequestBody Alimento alimentoUpdate) throws ResourceNotFoundException {
		
		Alimento alimento = alimentoServiceImpl.updateAlimento(id, alimentoUpdate);		
		return ResponseEntity.ok(alimento);
	}

	@DELETE
	@Path("/alimento/{id}")
	@Produces("application/json")
	@Consumes("application/json")
	@PreAuthorize("hasRole('ADMIN')")	
	public Map<String, Boolean> deleteAlimento(@PathParam(value = "id") int id) throws ResourceNotFoundException {
		
		boolean deleted = alimentoServiceImpl.deleteAlimento(id);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", deleted);
		return response;
	}

}
