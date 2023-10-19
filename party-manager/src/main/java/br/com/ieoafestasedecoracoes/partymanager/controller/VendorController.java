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

@RestController
@RequestMapping("vendors")
public class VendorController {

	@Autowired
	private VendorService service;

	@GetMapping
	public ResponseEntity<List<VendorTO>> allUsers() {
		return ResponseEntity.ok(VendorTO.fromUserList(service.findAll()));
	}

	@GetMapping("{id}")
	public ResponseEntity<VendorTO> findById(@PathVariable Integer id) {
		return ResponseEntity.ok(new VendorTO(service.findById(id)));
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping
	public ResponseEntity<VendorTO> update(@RequestBody VendorTO vendorTO) {
		return ResponseEntity.ok(new VendorTO(service.update(new Vendor(vendorTO))));
	}
	
	@PostMapping
	public ResponseEntity<VendorTO> create(@RequestBody VendorTO vendorTO) {
		return ResponseEntity.ok(new VendorTO(service.create(new Vendor(vendorTO))));
	}

	@GetMapping("/first-name/{firstName}")
	public ResponseEntity<VendorTO> findByFirstName(@PathVariable String firstName) {
		return ResponseEntity.ok(new VendorTO(service.findByFirstName(firstName)));
	}
	
	
	@GetMapping("/email/{email}")
	public ResponseEntity<VendorTO> findByEmail(@PathVariable String email) {
		return ResponseEntity.ok(new VendorTO(service.findByEmail(email)));
	}

}
