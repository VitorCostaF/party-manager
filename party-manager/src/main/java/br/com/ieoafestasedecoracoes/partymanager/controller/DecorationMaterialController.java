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

import br.com.ieoafestasedecoracoes.partymanager.domain.id.DecorationMaterialId;
import br.com.ieoafestasedecoracoes.partymanager.service.DecorationMaterialService;
import br.com.ieoafestasedecoracoes.partymanager.to.DecorationMaterialTO;

@RestController
@RequestMapping("decorationmaterials")
public class DecorationMaterialController {
	
	@Autowired
	private DecorationMaterialService service;
	
	@GetMapping("decoration/{decorationId}")
	public ResponseEntity<List<DecorationMaterialTO>> findByDecorationId(@PathVariable Integer decorationId) {
		return ResponseEntity.ok(service.findByDecoration(decorationId));
	}
	
	@GetMapping("material/{materialId}")
	public ResponseEntity<List<DecorationMaterialTO>> findByMaterialId(@PathVariable Integer materialId) {
		return ResponseEntity.ok(service.findByMaterial(materialId));
	}
	
	@DeleteMapping("decoration/{decorationId}/material/{materialId}")
	public ResponseEntity<Void> delete(@PathVariable Integer decorationId, @PathVariable Integer materialId) {
		service.delete(new DecorationMaterialId(decorationId, materialId));
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("decoration/{decorationId}/material/{materialId}")
	public ResponseEntity<DecorationMaterialTO> update(@RequestBody DecorationMaterialTO decorationTO, @PathVariable Integer decorationId, @PathVariable Integer materialId) {
		return ResponseEntity.ok(service.update(decorationTO, new DecorationMaterialId(decorationId, materialId)));
	}
	
	@PostMapping
	public ResponseEntity<DecorationMaterialTO> create(@RequestBody DecorationMaterialTO decorationTO) {
		return ResponseEntity.ok(service.create(decorationTO));
	}

 }
