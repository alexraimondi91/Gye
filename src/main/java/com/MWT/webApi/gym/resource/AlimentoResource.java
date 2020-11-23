package com.MWT.webApi.gym.resource;

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

import com.MWT.webApi.gym.exception.ResourceNotFoundException;
import com.MWT.webApi.gym.model.Alimento;
import com.MWT.webApi.gym.repository.AlimentoRepository;

@Component
@Path("/api/v1")
public class AlimentoResource {

	@Autowired
	private AlimentoRepository alimentoRepository;

	@GET
	@Path("/alimenti")
	@Produces("application/json")
	@PreAuthorize("hasRole('USER')")
	public List<Alimento> getAllAlimenti() {
		return alimentoRepository.findAll();
	}

	@GET
	@Path("/alimenti/{id}")
	@Produces("application/json")
	public ResponseEntity<Alimento> getAlimento(@PathParam(value = "id") int id) {
		Alimento alimento = new Alimento();
		try {
			alimento = alimentoRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Alimento not found :: " + id));
		} catch (ResourceNotFoundException e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok().body(alimento);
	}

	@POST
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/alimenti")
	@PostMapping("/alimenti")
	public Alimento createAlimento(Alimento alimento) {
		return alimentoRepository.save(alimento);
	}

	@PUT
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/alimenti/{id}")
	public ResponseEntity<Alimento> updateAlimento(@PathParam(value = "id") int id,
			@Valid @RequestBody Alimento alimentoUpdate) throws ResourceNotFoundException {
		Alimento alimento = new Alimento();
		try {
			alimento = alimentoRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Alimento not found :: " + id));
		} catch (ResourceNotFoundException e) {
			e.printStackTrace();
		}

		alimento.setNome(alimentoUpdate.getNome());
		alimento.setDescrizione(alimentoUpdate.getDescrizione());
		alimento.setGrassi(alimentoUpdate.getGrassi());
		alimento.setProteine(alimentoUpdate.getProteine());
		alimento.setCarboidrati(alimentoUpdate.getCarboidrati());
		final Alimento alimentoSave = alimentoRepository.save(alimento);
		return ResponseEntity.ok(alimentoSave);
	}

	@DELETE
	@Path("/alimenti/{id}")
	@Produces("application/json")
	@Consumes("application/json")
	public Map<String, Boolean> deleteAlimento(@PathParam(value = "id") int id) throws ResourceNotFoundException {
		Alimento alimento = new Alimento();
		try {
			alimento = alimentoRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Alimento not found :: " + id));
		} catch (ResourceNotFoundException e) {
			e.printStackTrace();
		}

		alimentoRepository.delete(alimento);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

}
