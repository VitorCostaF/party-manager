package br.com.ieoafestasedecoracoes.partymanager.service;

import java.math.BigDecimal;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ieoafestasedecoracoes.partymanager.domain.Decoration;
import br.com.ieoafestasedecoracoes.partymanager.domain.Party;
import br.com.ieoafestasedecoracoes.partymanager.domain.RentDecoration;
import br.com.ieoafestasedecoracoes.partymanager.domain.id.RentDecorationId;
import br.com.ieoafestasedecoracoes.partymanager.repository.DecorationRepository;
import br.com.ieoafestasedecoracoes.partymanager.repository.PartyRepository;
import br.com.ieoafestasedecoracoes.partymanager.repository.RentDecorationRepository;
import br.com.ieoafestasedecoracoes.partymanager.to.RentDecorationTO;
import br.com.ieoafestasedecoracoes.partymanager.validation.EntityDependencyValidation;

@Service
public class RentDecorationService {

	@Autowired
	private RentDecorationRepository repository;
	
	@Autowired
	private PartyRepository partyRepository;
	
	@Autowired
	private DecorationRepository decorationRepository;
	
	@Autowired
	private ModelMapper mapper;
	
	public RentDecorationTO findById(RentDecorationId id) {
		RentDecoration decorationMaterial = repository.findById(id).orElse(null);
		if(decorationMaterial != null) {
			return mapper.map(decorationMaterial, RentDecorationTO.class);
		}
		return new RentDecorationTO();
	}
	
	public List<RentDecorationTO> findAll() {
		return toRentDecorationTOList(repository.findAll());
	}
	
	public void delete(RentDecorationId id) {
		repository.deleteById(id);
	}
	
	public RentDecorationTO update(RentDecorationTO decorationMaterial, RentDecorationId id) {
		RentDecoration decorationMaterialDB = repository.findById(id).orElse(null);
		
		if(decorationMaterialDB == null) {
			throw new RuntimeException("RentDecoration not found to be updated");
		}
		
		decorationMaterial.setDecorationId(id.getDecorationId());
		decorationMaterial.setPartyId(id.getPartyId());
		mapper.map(decorationMaterial, decorationMaterialDB);	
		
		repository.save(decorationMaterialDB);
		return decorationMaterial;
	}
	
	public RentDecorationTO create(RentDecorationTO rentDecoration) {
		RentDecoration rentDecorationDB = mapper.map(rentDecoration, RentDecoration.class);

		Decoration decoration = EntityDependencyValidation.validate(decorationRepository,
				rentDecoration.getDecorationId(), "Decoration", "RentDecoration");
		
		Party party = EntityDependencyValidation.validate(partyRepository, rentDecoration.getPartyId(), "Party",
				"RentDecoration");

		rentDecorationDB.setDecoration(decoration);
		rentDecorationDB.setParty(party);
		rentDecorationDB.setPrice(decoration.getPrice().multiply((BigDecimal.ONE.subtract(decoration.getDiscount()))));

		repository.save(rentDecorationDB);

		return rentDecoration;
	}
	
	public List<RentDecorationTO> findByDecoration(Integer decorationId) {
		return toRentDecorationTOList(repository.findByDecorationId(decorationId));
	}
	
	public List<RentDecorationTO> findByMaterial(Integer materialId) {
		return toRentDecorationTOList(repository.findByPartyId(materialId));
	}
	
	private List<RentDecorationTO> toRentDecorationTOList(List<RentDecoration> rentDecoration) {
		return rentDecoration.stream()
				.map(v -> 
					mapper.typeMap(RentDecoration.class, RentDecorationTO.class)
						.addMapping(RentDecoration::getDecorationId, RentDecorationTO::setDecorationId)
						.addMapping(RentDecoration::getPartyId, RentDecorationTO::setPartyId)
						.map(v)
				).toList();
	}

}
