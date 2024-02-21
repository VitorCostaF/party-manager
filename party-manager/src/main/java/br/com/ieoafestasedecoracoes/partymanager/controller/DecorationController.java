package br.com.ieoafestasedecoracoes.partymanager.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@GetMapping("/description/{description}")
	public ResponseEntity<List<DecorationTO>> findByDescription(@PathVariable String description, @RequestParam Map<String,String> params) {
		return ResponseEntity.ok(service.findByDescription(description, params));
	}
	
	@GetMapping("/category/{categoryId}")
	public ResponseEntity<List<DecorationTO>> findByCategory(@PathVariable Integer categoryId) {
		return ResponseEntity.ok(service.findByCategory(categoryId));
	}
	
	@GetMapping("/company/{companyId}")
	public ResponseEntity<List<DecorationTO>> findByCompany(@PathVariable Integer companyId) {
		return ResponseEntity.ok(service.findByCompany(companyId));
	}
	
	@GetMapping("/hot")
	public ResponseEntity<List<DecorationTO>> findHotDecorations() {
		return ResponseEntity.ok(service.findHotDecorations());
	}
	
	@GetMapping("/province/{provinceId}")
	public ResponseEntity<List<DecorationTO>> findByProvince(@PathVariable Integer provinceId) {
		return ResponseEntity.ok(service.findByProvince(provinceId));
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
