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

import br.com.ieoafestasedecoracoes.partymanager.domain.Vendor;
import br.com.ieoafestasedecoracoes.partymanager.service.VendorService;
import br.com.ieoafestasedecoracoes.partymanager.to.VendorTO;
import jakarta.validation.Valid;

@RestController
@RequestMapping("vendors")
public class VendorController {

	@Autowired
	private VendorService service;

	@GetMapping
	public ResponseEntity<List<VendorTO>> allUsers() {
		return ResponseEntity.ok(service.findAll());
	}

	@GetMapping("{id}")
	public ResponseEntity<VendorTO> findById(@PathVariable Integer id) {
		return ResponseEntity.ok(service.findById(id));
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("{id}")
	public ResponseEntity<VendorTO> update(@Valid @RequestBody VendorTO vendorTO, @PathVariable Integer id) {
		return ResponseEntity.ok(service.update(vendorTO, id));
	}
	
	// TODO devolver created
	@PostMapping
	public ResponseEntity<VendorTO> create(@Valid @RequestBody VendorTO vendorTO) {
		return ResponseEntity.ok(service.create(vendorTO));
	}

	@GetMapping("/firstname/{firstName}")
	public ResponseEntity<List<VendorTO>> findByFirstName(@PathVariable String firstName) {
		return ResponseEntity.ok(service.findByFirstName(firstName));
	}
	
	
	@GetMapping("/email/{email}")
	public ResponseEntity<VendorTO> findByEmail(@PathVariable String email) {
		return ResponseEntity.ok(service.findByEmail(email));
	}

}
