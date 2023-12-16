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

import br.com.ieoafestasedecoracoes.partymanager.service.PartyService;
import br.com.ieoafestasedecoracoes.partymanager.to.PartyTO;

@RestController
@RequestMapping("parties")
public class PartyController {
	
	@Autowired
	private PartyService service;
	
	@GetMapping("{id}")
	public ResponseEntity<PartyTO> findById(@PathVariable Integer id) {
		return ResponseEntity.ok(service.findById(id));
	}
	
	@GetMapping
	public ResponseEntity<List<PartyTO>> findAll() {
		return ResponseEntity.ok(service.findAll());
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("{id}")
	public ResponseEntity<PartyTO> update(@RequestBody PartyTO partyTO, @PathVariable Integer id) {
		return ResponseEntity.ok(service.update(partyTO, id));
	}
	
	@PostMapping
	public ResponseEntity<PartyTO> create(@RequestBody PartyTO partyTO) {
		return ResponseEntity.ok(service.create(partyTO));
	}

 }
