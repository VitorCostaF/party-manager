package br.com.ieoafestasedecoracoes.partymanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ieoafestasedecoracoes.partymanager.service.PartyMaterialService;
import br.com.ieoafestasedecoracoes.partymanager.to.PartyMaterialTO;

@RestController
@RequestMapping("partymaterials")
public class PartyMaterialController {
	
	@Autowired
	private PartyMaterialService service;
	
	@GetMapping("party/{partyId}")
	public ResponseEntity<List<PartyMaterialTO>> findByPartyId(@PathVariable Integer partyId) {
		return ResponseEntity.ok(service.findByParty(partyId));
	}
	
	@GetMapping("material/{materialId}")
	public ResponseEntity<List<PartyMaterialTO>> findByMaterialId(@PathVariable Integer materialId) {
		return ResponseEntity.ok(service.findByMaterial(materialId));
	}
	
	@DeleteMapping("{partyId}/{materialId}")
	public ResponseEntity<Void> delete(@PathVariable Integer partyId, @PathVariable Integer materialId) {
		service.delete(partyId, materialId);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("party/{partyId}/material/{materialId}")
	public ResponseEntity<PartyMaterialTO> update(@RequestBody PartyMaterialTO partyTO, @PathVariable Integer partyId, @PathVariable Integer materialId) {
		return ResponseEntity.ok(service.update(partyTO, partyId, materialId));
	}
	
	@PostMapping
	public ResponseEntity<PartyMaterialTO> create(@RequestBody PartyMaterialTO partyTO) {
		return ResponseEntity.ok(service.create(partyTO));
	}

 }
