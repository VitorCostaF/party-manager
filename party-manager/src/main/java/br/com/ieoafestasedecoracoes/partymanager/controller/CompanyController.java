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

import br.com.ieoafestasedecoracoes.partymanager.service.CompanyService;
import br.com.ieoafestasedecoracoes.partymanager.to.CompanyTO;

@RestController
@RequestMapping("companies")
public class CompanyController {

	@Autowired
	private CompanyService service;

	@GetMapping
	public ResponseEntity<List<CompanyTO>> findAll() {
		return ResponseEntity.ok(service.findAll());
	}

	@GetMapping("{id}")
	
	public ResponseEntity<CompanyTO> findById(@PathVariable Integer id) {
		return ResponseEntity.ok(service.findById(id));
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("{id}")
	public ResponseEntity<CompanyTO> update(@PathVariable Integer id, @RequestBody CompanyTO companyTO) {
		return ResponseEntity.ok(service.update(id, companyTO));
	}
	
	// TODO retornar created e criar a url
	@PostMapping
	public ResponseEntity<CompanyTO> create(@RequestBody CompanyTO companyTO) {
		return ResponseEntity.ok(service.create(companyTO));
	}
	
	@GetMapping("name/{name}")
	public ResponseEntity<List<CompanyTO>> findByName(@PathVariable String name) {
		return ResponseEntity.ok(service.findByName(name));
	}

}
