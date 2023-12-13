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

import br.com.ieoafestasedecoracoes.partymanager.service.MaterialService;
import br.com.ieoafestasedecoracoes.partymanager.to.MaterialTO;
import jakarta.validation.Valid;

@RestController
@RequestMapping("materials")
public class MaterialController {

	@Autowired
	private MaterialService service;

	@GetMapping
	public ResponseEntity<List<MaterialTO>> allUsers() {
		return ResponseEntity.ok(service.findAll());
	}

	@GetMapping("{id}")
	public ResponseEntity<MaterialTO> findById(@PathVariable Integer id) {
		return ResponseEntity.ok(service.findById(id));
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("{id}")
	public ResponseEntity<MaterialTO> update(@Valid @RequestBody MaterialTO vendorTO, @PathVariable Integer id) {
		return ResponseEntity.ok(service.update(vendorTO, id));
	}
	
	// TODO devolver created
	@PostMapping
	public ResponseEntity<MaterialTO> create(@Valid @RequestBody MaterialTO vendorTO) {
		return ResponseEntity.ok(service.create(vendorTO));
	}

}
