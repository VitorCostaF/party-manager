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

import br.com.ieoafestasedecoracoes.partymanager.service.ClientService;
import br.com.ieoafestasedecoracoes.partymanager.to.ClientTO;

@RestController
@RequestMapping("clients")
public class ClientController {
	
	@Autowired
	private ClientService service;
	
	@GetMapping("{id}")
	public ResponseEntity<ClientTO> findById(@PathVariable Integer id) {
		return ResponseEntity.ok(service.findById(id));
	}
	
	@GetMapping
	public ResponseEntity<List<ClientTO>> findAll() {
		return ResponseEntity.ok(service.findAll());
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("{id}")
	public ResponseEntity<ClientTO> update(@RequestBody ClientTO clientTO, @PathVariable Integer id) {
		return ResponseEntity.ok(service.update(clientTO, id));
	}
	
	@PostMapping
	public ResponseEntity<ClientTO> create(@RequestBody ClientTO clientTO) {
		return ResponseEntity.ok(service.create(clientTO));
	}
	
	@GetMapping("email/{email}")
	public ResponseEntity<ClientTO> findByEmail(@PathVariable String email) {
		return ResponseEntity.ok(service.findByEmail(email));
	}
	
	@GetMapping("first-name/{firstName}")
	public ResponseEntity<ClientTO> findByFirstName(@PathVariable String firstName) {
		return ResponseEntity.ok(service.findByEmail(firstName));
	}

 }
