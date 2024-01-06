package br.com.ieoafestasedecoracoes.partymanager.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ieoafestasedecoracoes.partymanager.domain.DecorationMaterial;
import br.com.ieoafestasedecoracoes.partymanager.domain.id.DecorationMaterialId;
import br.com.ieoafestasedecoracoes.partymanager.repository.DecorationMaterialRepository;
import br.com.ieoafestasedecoracoes.partymanager.to.DecorationMaterialTO;

@Service
public class DecorationMaterialService {

	@Autowired
	private DecorationMaterialRepository repository;
	
	@Autowired
	private ModelMapper mapper;
	
	public DecorationMaterialTO findById(DecorationMaterialId id) {
		DecorationMaterial decorationMaterial = repository.findById(id).orElse(null);
		if(decorationMaterial != null) {
			return mapper.map(decorationMaterial, DecorationMaterialTO.class);
		}
		return new DecorationMaterialTO();
	}
	
	public List<DecorationMaterialTO> findAll() {
		return toDecorationMaterialTOList(repository.findAll());
	}
	
	public void delete(DecorationMaterialId id) {
		repository.deleteById(id);
	}
	
	public DecorationMaterialTO update(DecorationMaterialTO decorationMaterial, DecorationMaterialId id) {
		DecorationMaterial decorationMaterialDB = repository.findById(id).orElse(null);
		
		if(decorationMaterialDB == null) {
			throw new RuntimeException("DecorationMaterial not found to be updated");
		}
		
		decorationMaterial.setDecorationId(id.getDecorationId());
		decorationMaterial.setMaterialId(id.getMaterialId());
		mapper.map(decorationMaterial, decorationMaterialDB);	
		
		repository.save(decorationMaterialDB);
		return decorationMaterial;
	}
	
	public DecorationMaterialTO create(DecorationMaterialTO decorationMaterial) {
		DecorationMaterial decorationMaterialBD = mapper.map(decorationMaterial, DecorationMaterial.class);
		
		decorationMaterialBD.setDecorationId(null);
		decorationMaterialBD.setMaterialId(null);
		
		repository.save(decorationMaterialBD);
		
		decorationMaterial.setDecorationId(decorationMaterialBD.getDecorationId());
		decorationMaterial.setMaterialId(decorationMaterialBD.getMaterialId());
				
		return decorationMaterial;
	}
	
	public List<DecorationMaterialTO> findByDecoration(Integer decorationId) {
		return toDecorationMaterialTOList(repository.findByDecorationId(decorationId));
	}
	
	public List<DecorationMaterialTO> findByMaterial(Integer materialId) {
		return toDecorationMaterialTOList(repository.findByMaterialId(materialId));
	}
	
	private List<DecorationMaterialTO> toDecorationMaterialTOList(List<DecorationMaterial> decorationMaterials) {
		return decorationMaterials.stream().map(v -> mapper.map(v, DecorationMaterialTO.class)).toList();
	}

	
}
