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

import br.com.ieoafestasedecoracoes.partymanager.service.CityService;
import br.com.ieoafestasedecoracoes.partymanager.to.CityTO;
import jakarta.validation.Valid;

@RestController
@RequestMapping("cities")
public class CityController {

	@Autowired
	private CityService service;

	@GetMapping()
	public ResponseEntity<List<CityTO>> findAll() {
		return ResponseEntity.ok(service.findAll());
	}

	@GetMapping("{id}")
	public ResponseEntity<CityTO> findById(@PathVariable Integer id) {
		return ResponseEntity.ok(service.findById(id));
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("{id}")
	public ResponseEntity<CityTO> update(@PathVariable Integer id, @RequestBody CityTO cityTO) {
		return ResponseEntity.ok(service.update(id, cityTO));
	}
	
	@PostMapping
	public ResponseEntity<CityTO> create(@RequestBody @Valid CityTO cityTO) {
		return ResponseEntity.ok(service.create(cityTO));
	}
	
}
