package br.com.ieoafestasedecoracoes.partymanager.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ieoafestasedecoracoes.partymanager.domain.PartyMaterial;
import br.com.ieoafestasedecoracoes.partymanager.domain.PartyMaterialId;
import br.com.ieoafestasedecoracoes.partymanager.repository.PartyMaterialRepository;
import br.com.ieoafestasedecoracoes.partymanager.to.PartyMaterialTO;

@Service
public class PartyMaterialService {

	@Autowired
	private PartyMaterialRepository repository;

	@Autowired
	private ModelMapper mapper;

	
	public List<PartyMaterialTO> findByMaterial(Integer materialId) {
		List<PartyMaterial> party = repository.findByMaterialId(materialId);
		return toPartyMaterialTOList(party);
	}
	
	public List<PartyMaterialTO> findByParty(Integer partyId) {
		List<PartyMaterial> party = repository.findByPartyId(partyId);
		return toPartyMaterialTOList(party);
	}
		
	public void delete(Integer partyId, Integer materiald) {
		repository.deleteById(new PartyMaterialId(materiald, partyId));
	}
	
	public PartyMaterialTO update(PartyMaterialTO partyMaterial, Integer partyId, Integer materiald) {
		PartyMaterial partyDB = repository.findById(new PartyMaterialId(materiald, partyId)).orElse(null);
		
		if(partyDB == null) {
			throw new RuntimeException("PartyMaterial not found to be updated");
		}
		
		mapper.map(partyMaterial, partyDB);	
		
		repository.save(partyDB);
		return partyMaterial;
	}
	
	public PartyMaterialTO create(PartyMaterialTO partyMaterial) {
		PartyMaterial partyBD = mapper.map(partyMaterial, PartyMaterial.class);
		
		repository.save(partyBD);
		
		return partyMaterial;
	}
	
	private List<PartyMaterialTO> toPartyMaterialTOList(List<PartyMaterial> parties) {
		return parties.stream().map(v -> mapper.map(v, PartyMaterialTO.class)).toList();
	}
	
}
