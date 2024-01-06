package br.com.ieoafestasedecoracoes.partymanager.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ieoafestasedecoracoes.partymanager.domain.RentDecoration;
import br.com.ieoafestasedecoracoes.partymanager.domain.id.RentDecorationId;
import br.com.ieoafestasedecoracoes.partymanager.repository.RentDecorationRepository;
import br.com.ieoafestasedecoracoes.partymanager.to.RentDecorationTO;

@Service
public class RentDecorationService {

	@Autowired
	private RentDecorationRepository repository;
	
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
		RentDecoration decorationMaterialBD = mapper.map(rentDecoration, RentDecoration.class);
		
		decorationMaterialBD.setDecorationId(null);
		decorationMaterialBD.setPartyId(null);
		
		repository.save(decorationMaterialBD);
		
		rentDecoration.setDecorationId(decorationMaterialBD.getDecorationId());
		rentDecoration.setPartyId(decorationMaterialBD.getPartyId());
				
		return rentDecoration;
	}
	
	public List<RentDecorationTO> findByDecoration(Integer decorationId) {
		return toRentDecorationTOList(repository.findByDecorationId(decorationId));
	}
	
	public List<RentDecorationTO> findByMaterial(Integer materialId) {
		return toRentDecorationTOList(repository.findByPartyId(materialId));
	}
	
	private List<RentDecorationTO> toRentDecorationTOList(List<RentDecoration> rentDecoration) {
		return rentDecoration.stream().map(v -> mapper.map(v, RentDecorationTO.class)).toList();
	}

	
}
