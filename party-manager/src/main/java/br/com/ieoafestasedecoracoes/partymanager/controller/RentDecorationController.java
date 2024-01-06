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

import br.com.ieoafestasedecoracoes.partymanager.domain.id.RentDecorationId;
import br.com.ieoafestasedecoracoes.partymanager.service.RentDecorationService;
import br.com.ieoafestasedecoracoes.partymanager.to.RentDecorationTO;

@RestController
@RequestMapping("rent-decoration")
public class RentDecorationController {
	
	@Autowired
	private RentDecorationService service;
	
	@GetMapping("decoration/{decorationId}")
	public ResponseEntity<List<RentDecorationTO>> findByDecorationId(@PathVariable Integer decorationId) {
		return ResponseEntity.ok(service.findByDecoration(decorationId));
	}
	
	@GetMapping("party/{partyId}")
	public ResponseEntity<List<RentDecorationTO>> findByPartyId(@PathVariable Integer partyId) {
		return ResponseEntity.ok(service.findByMaterial(partyId));
	}
	
	@DeleteMapping("decoration/{decorationId}/party/{partyId}")
	public ResponseEntity<Void> delete(@PathVariable Integer decorationId, @PathVariable Integer partyId) {
		service.delete(new RentDecorationId(decorationId, partyId));
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("decoration/{decorationId}/party/{partyId}")
	public ResponseEntity<RentDecorationTO> update(@RequestBody RentDecorationTO decorationTO, @PathVariable Integer decorationId, @PathVariable Integer partyId) {
		return ResponseEntity.ok(service.update(decorationTO, new RentDecorationId(decorationId, partyId)));
	}
	
	@PostMapping
	public ResponseEntity<RentDecorationTO> create(@RequestBody RentDecorationTO decorationTO) {
		return ResponseEntity.ok(service.create(decorationTO));
	}

 }
