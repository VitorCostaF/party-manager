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

import br.com.ieoafestasedecoracoes.partymanager.service.DecorationService;
import br.com.ieoafestasedecoracoes.partymanager.to.DecorationTO;

@RestController
@RequestMapping("decorations")
public class DecorationController {
	
	@Autowired
	private DecorationService service;
	
	@GetMapping("{id}")
	public ResponseEntity<DecorationTO> findById(@PathVariable Integer id) {
		return ResponseEntity.ok(service.findById(id));
	}
	
	@GetMapping
	public ResponseEntity<List<DecorationTO>> findAll() {
		return ResponseEntity.ok(service.findAll());
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("{id}")
	public ResponseEntity<DecorationTO> update(@RequestBody DecorationTO decorationTO, @PathVariable Integer id) {
		return ResponseEntity.ok(service.update(decorationTO, id));
	}
	
	@PostMapping
	public ResponseEntity<DecorationTO> create(@RequestBody DecorationTO decorationTO) {
		return ResponseEntity.ok(service.create(decorationTO));
	}
	
 }
