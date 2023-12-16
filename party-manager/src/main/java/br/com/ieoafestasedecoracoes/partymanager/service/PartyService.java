package br.com.ieoafestasedecoracoes.partymanager.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ieoafestasedecoracoes.partymanager.domain.Party;
import br.com.ieoafestasedecoracoes.partymanager.repository.PartyRepository;
import br.com.ieoafestasedecoracoes.partymanager.to.PartyTO;

@Service
public class PartyService {

	@Autowired
	private PartyRepository repository;

	@Autowired
	private ModelMapper mapper;

	
	public PartyTO findById(Integer id) {
		Party party = repository.findById(id).orElse(null);
		if(party != null) {
			return mapper.map(party, PartyTO.class);
		}
		return new PartyTO();
	}
	
	public List<PartyTO> findAll() {
		return toPartyTOList(repository.findAll());
	}
	
	public void delete(Integer id) {
		repository.deleteById(id);
	}
	
	public PartyTO update(PartyTO party, Integer id) {
		Party partyDB = repository.findById(id).orElse(null);
		
		if(partyDB == null) {
			throw new RuntimeException("Party not found to be updated");
		}
		
		party.setId(id);
		mapper.map(party, partyDB);	
		
		repository.save(partyDB);
		return party;
	}
	
	public PartyTO create(PartyTO party) {
		Party partyBD = mapper.map(party, Party.class);
		
		partyBD.setId(null);
		repository.save(partyBD);
		
		party.setId(partyBD.getId());
		
		return party;
	}
	
	private List<PartyTO> toPartyTOList(List<Party> parties) {
		return parties.stream().map(v -> mapper.map(v, PartyTO.class)).toList();
	}
	
}
