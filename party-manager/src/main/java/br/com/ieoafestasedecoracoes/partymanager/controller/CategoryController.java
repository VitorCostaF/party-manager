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

import br.com.ieoafestasedecoracoes.partymanager.service.CategoryService;
import br.com.ieoafestasedecoracoes.partymanager.to.CategoryTO;
import jakarta.validation.Valid;

@RestController
@RequestMapping("categories")
public class CategoryController {

	@Autowired
	private CategoryService service;

	@GetMapping()
	public ResponseEntity<List<CategoryTO>> findAll() {
		return ResponseEntity.ok(service.findAll());
	}

	@GetMapping("{id}")
	public ResponseEntity<CategoryTO> findById(@PathVariable Integer id) {
		return ResponseEntity.ok(service.findById(id));
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("{id}")
	public ResponseEntity<CategoryTO> update(@PathVariable Integer id, @RequestBody CategoryTO categoryTO) {
		return ResponseEntity.ok(service.update(id, categoryTO));
	}
	
	@PostMapping
	public ResponseEntity<CategoryTO> create(@RequestBody @Valid CategoryTO categoryTO) {
		return ResponseEntity.ok(service.create(categoryTO));
	}
	
}
