package br.com.ieoafestasedecoracoes.partymanager.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ieoafestasedecoracoes.partymanager.domain.PartyMaterial;
import br.com.ieoafestasedecoracoes.partymanager.repository.PartyMaterialRepository;
import br.com.ieoafestasedecoracoes.partymanager.to.PartyMaterialTO;

@Service
public class PartyMaterialService {

	@Autowired
	private PartyMaterialRepository repository;

	@Autowired
	private ModelMapper mapper;

	
	public PartyMaterialTO findById(Integer id) {
		PartyMaterial party = repository.findById(id).orElse(null);
		if(party != null) {
			return mapper.map(party, PartyMaterialTO.class);
		}
		return new PartyMaterialTO();
	}
	
	public List<PartyMaterialTO> findAll() {
		return toPartyMaterialTOList(repository.findAll());
	}
	
	public void delete(Integer id) {
		repository.deleteById(id);
	}
	
	public PartyMaterialTO update(PartyMaterialTO party, Integer id) {
		PartyMaterial partyDB = repository.findById(id).orElse(null);
		
		if(partyDB == null) {
			throw new RuntimeException("PartyMaterial not found to be updated");
		}
		
		party.setId(id);
		mapper.map(party, partyDB);	
		
		repository.save(partyDB);
		return party;
	}
	
	public PartyMaterialTO create(PartyMaterialTO party) {
		PartyMaterial partyBD = mapper.map(party, PartyMaterial.class);
		
		partyBD.setId(null);
		repository.save(partyBD);
		
		party.setId(partyBD.getId());
		
		return party;
	}
	
	private List<PartyMaterialTO> toPartyMaterialTOList(List<PartyMaterial> parties) {
		return parties.stream().map(v -> mapper.map(v, PartyMaterialTO.class)).toList();
	}
	
}
