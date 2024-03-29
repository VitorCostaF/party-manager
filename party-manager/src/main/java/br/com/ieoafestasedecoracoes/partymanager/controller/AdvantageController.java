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

import br.com.ieoafestasedecoracoes.partymanager.service.AdvantageService;
import br.com.ieoafestasedecoracoes.partymanager.to.AdvantageTO;
import jakarta.validation.Valid;

@RestController
@RequestMapping("advantages")
public class AdvantageController {

	@Autowired
	private AdvantageService service;

	@GetMapping()
	public ResponseEntity<List<AdvantageTO>> findAll() {
		return ResponseEntity.ok(service.findAll());
	}

	@GetMapping("{id}")
	public ResponseEntity<AdvantageTO> findById(@PathVariable Integer id) {
		return ResponseEntity.ok(service.findById(id));
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("{id}")
	public ResponseEntity<AdvantageTO> update(@PathVariable Integer id, @RequestBody AdvantageTO advantageTO) {
		return ResponseEntity.ok(service.update(id, advantageTO));
	}
	
	@PostMapping
	public ResponseEntity<AdvantageTO> create(@RequestBody @Valid AdvantageTO advantageTO) {
		return ResponseEntity.ok(service.create(advantageTO));
	}
	
}
