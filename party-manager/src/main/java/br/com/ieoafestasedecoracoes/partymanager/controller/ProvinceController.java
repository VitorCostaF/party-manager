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

import br.com.ieoafestasedecoracoes.partymanager.service.ProvinceService;
import br.com.ieoafestasedecoracoes.partymanager.to.ProvinceTO;
import jakarta.validation.Valid;

@RestController
@RequestMapping("provinces")
public class ProvinceController {

	@Autowired
	private ProvinceService service;

	@GetMapping()
	public ResponseEntity<List<ProvinceTO>> findAll() {
		return ResponseEntity.ok(service.findAll());
	}

	@GetMapping("{id}")
	public ResponseEntity<ProvinceTO> findById(@PathVariable Integer id) {
		return ResponseEntity.ok(service.findById(id));
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("{id}")
	public ResponseEntity<ProvinceTO> update(@PathVariable Integer id, @RequestBody ProvinceTO provinceTO) {
		return ResponseEntity.ok(service.update(id, provinceTO));
	}
	
	@PostMapping
	public ResponseEntity<ProvinceTO> create(@RequestBody @Valid ProvinceTO provinceTO) {
		return ResponseEntity.ok(service.create(provinceTO));
	}
	
}
