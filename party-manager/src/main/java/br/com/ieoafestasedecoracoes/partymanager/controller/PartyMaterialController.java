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
	
	@GetMapping("{id}")
	public ResponseEntity<PartyMaterialTO> findById(@PathVariable Integer id) {
		return ResponseEntity.ok(service.findById(id));
	}
	
	@GetMapping
	public ResponseEntity<List<PartyMaterialTO>> findAll() {
		return ResponseEntity.ok(service.findAll());
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("{id}")
	public ResponseEntity<PartyMaterialTO> update(@RequestBody PartyMaterialTO partyTO, @PathVariable Integer id) {
		return ResponseEntity.ok(service.update(partyTO, id));
	}
	
	@PostMapping
	public ResponseEntity<PartyMaterialTO> create(@RequestBody PartyMaterialTO partyTO) {
		return ResponseEntity.ok(service.create(partyTO));
	}

 }
