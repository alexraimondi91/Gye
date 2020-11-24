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
import com.MWT.webApi.gym.businessImpl.DietaServiceImpl;
import com.MWT.webApi.gym.exception.ResourceNotFoundException;
import com.MWT.webApi.gym.model.Alimento;
import com.MWT.webApi.gym.model.Dieta;

@Component
@Path("/api/v1")
public class DietaController {
	
	@Autowired
	private DietaServiceImpl dietaServiceImpl;

	@GET
	@Path("/diete")
	@Produces("application/json")
	@PreAuthorize("hasRole('ADMIN')")	
	public List<Dieta> getAllDiete() {
		return dietaServiceImpl.getAllDieta();
	}

	@GET
	@Path("/diete/{id}")
	@Produces("application/json")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")	
	public ResponseEntity<Dieta> getDieta(@PathParam(value = "id") int id) {
		Dieta dieta = dietaServiceImpl.getDieta(id);
		return ResponseEntity.ok().body(dieta);
	}

	@POST
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/diete")
	@PostMapping("/diete")
	@PreAuthorize("hasRole('ADMIN')")	

	public Dieta createDieta(Dieta dieta) {
		
		return dietaServiceImpl.createDieta(dieta);
	}

	@PUT
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/diete/{id}")
	@PreAuthorize("hasRole('ADMIN')")	
	public ResponseEntity<Dieta> updateDieta(@PathParam(value = "id") int id,
			@Valid @RequestBody Dieta dietaUpdate) throws ResourceNotFoundException {
		
		Dieta dieta = dietaServiceImpl.updateDieta(id, dietaUpdate);		
		return ResponseEntity.ok(dieta);
	}

	@DELETE
	@Path("/diete/{id}")
	@Produces("application/json")
	@Consumes("application/json")
	@PreAuthorize("hasRole('ADMIN')")	
	public Map<String, Boolean> deleteDieta(@PathParam(value = "id") int id) throws ResourceNotFoundException {
		
		boolean deleted = dietaServiceImpl.deleteDieta(id);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", deleted);
		return response;
	}

}
