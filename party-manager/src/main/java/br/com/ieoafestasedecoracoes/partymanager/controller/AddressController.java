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

import br.com.ieoafestasedecoracoes.partymanager.service.AddressService;
import br.com.ieoafestasedecoracoes.partymanager.to.AddressTO;
import jakarta.validation.Valid;

@RestController
@RequestMapping("addresses")
public class AddressController {

	@Autowired
	private AddressService service;

	@GetMapping()
	public ResponseEntity<List<AddressTO>> findAll() {
		return ResponseEntity.ok(service.findAll());
	}

	@GetMapping("{id}")
	public ResponseEntity<AddressTO> findById(@PathVariable Integer id) {
		return ResponseEntity.ok(service.findById(id));
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("{id}")
	public ResponseEntity<AddressTO> update(@PathVariable Integer id, @RequestBody AddressTO addressTO) {
		return ResponseEntity.ok(service.update(id, addressTO));
	}
	
	@PostMapping
	public ResponseEntity<AddressTO> create(@RequestBody @Valid AddressTO addressTO) {
		return ResponseEntity.ok(service.create(addressTO));
	}
	
}
