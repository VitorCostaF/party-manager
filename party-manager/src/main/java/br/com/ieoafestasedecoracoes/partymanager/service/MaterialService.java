package br.com.ieoafestasedecoracoes.partymanager.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ieoafestasedecoracoes.partymanager.domain.Material;
import br.com.ieoafestasedecoracoes.partymanager.repository.CompanyRepository;
import br.com.ieoafestasedecoracoes.partymanager.repository.MaterialRepository;
import br.com.ieoafestasedecoracoes.partymanager.to.MaterialTO;

@Service
public class MaterialService {

	@Autowired
	private MaterialRepository repository;
	
	@Autowired
	private CompanyRepository companyRepository;
	
	@Autowired
	private ModelMapper mapper;

	public List<MaterialTO> findAll() {
		return toMaterialTOList(repository.findAll());
	}

	public MaterialTO findById(Integer id) {
		
		Material material = repository.findById(id).orElse(null);
		if(material != null) {
			return mapper.map(material, MaterialTO.class);
		}
		return new MaterialTO();
	}

	public void delete(Integer id) {
		repository.deleteById(id);
	}

	public MaterialTO update(MaterialTO material, Integer id) {

		Material userBD = repository.findById(id).orElse(null);
		if (userBD == null) {
			throw new RuntimeException("Usuário não existente para atualização");
		}
		
		material.setId(id);
		mapper.map(material, userBD);
		repository.save(userBD);
		return material;

	}

	public MaterialTO create(MaterialTO materialTO) {
		
		if(!companyRepository.existsById(materialTO.getCompanyId())) {
			throw new RuntimeException("Empresa vinculada ao usuário não existe");
		}
		
		Material material = mapper.map(materialTO, Material.class);
		material.setId(null);
		
		repository.save(material);
		
		materialTO.setId(material.getId());
		return materialTO;
	}

	private List<MaterialTO> toMaterialTOList(List<Material> materials) {
		return materials.stream().map(v -> mapper.map(v, MaterialTO.class)).toList();
	}

}
