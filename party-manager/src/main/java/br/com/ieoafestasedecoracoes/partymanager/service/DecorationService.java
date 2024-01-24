package br.com.ieoafestasedecoracoes.partymanager.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ieoafestasedecoracoes.partymanager.domain.Company;
import br.com.ieoafestasedecoracoes.partymanager.domain.Decoration;
import br.com.ieoafestasedecoracoes.partymanager.repository.CategoryRepository;
import br.com.ieoafestasedecoracoes.partymanager.repository.CompanyRepository;
import br.com.ieoafestasedecoracoes.partymanager.repository.DecorationRepository;
import br.com.ieoafestasedecoracoes.partymanager.to.CategoryTO;
import br.com.ieoafestasedecoracoes.partymanager.to.DecorationTO;

@Service
public class DecorationService {

	@Autowired
	private DecorationRepository repository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private CompanyRepository companyRepository;
	
	@Autowired
	private ModelMapper mapper;
	
	public DecorationTO findById(Integer id) {
		Decoration decoration = repository.findById(id).orElse(null);
		if(decoration != null) {
			return mapper.map(decoration, DecorationTO.class);
		}
		return new DecorationTO();
	}
	
	public List<DecorationTO> findAll() {
		return toDecorationTOList(repository.findAll());
	}
	
	public List<DecorationTO> findByDescription(String description) {
		return toDecorationTOList(repository.findByNameContainingIgnoreCaseOrThemeContainingIgnoreCase(description, description));
	}
	
	public void delete(Integer id) {
		repository.deleteById(id);
	}
	
	public DecorationTO update(DecorationTO decoration, Integer id) {
		Decoration decorationDB = repository.findById(id).orElse(null);
				
		if(decorationDB == null) {
			throw new RuntimeException("Decoration not found to be updated");
		}
		
		validCategories(decoration.getCategories());
		
		decoration.setId(id);
		mapper.map(decoration, decorationDB);	
		
		repository.save(decorationDB);
		return decoration;
	}
	
	public DecorationTO create(DecorationTO decoration) {
		Decoration decorationBD = mapper.map(decoration, Decoration.class);
		
		validCategories(decoration.getCategories());
				
		Optional<Company> company = companyRepository.findById(decoration.getCompanyId());
		
		if(!company.isPresent()) {
			throw new RuntimeException("Company not found for Decoration");
		}
		
		decorationBD.setCompany(company.get());
		
		decorationBD.setId(null);
		repository.save(decorationBD);
		
		decoration.setId(decorationBD.getId());
		
		return decoration;
	}
	
	private void validCategories(List<CategoryTO> categoriesTO) {
		categoriesTO.stream().forEach(this::validCategory);
	}
	
	private void validCategory(CategoryTO categoryTO) {
		if(!categoryRepository.existsById(categoryTO.getId())) {
			throw new RuntimeException("Categories not found for Decoration");
		}
	}
	
	private List<DecorationTO> toDecorationTOList(List<Decoration> decorations) {
		return decorations.stream().map(v -> mapper.map(v, DecorationTO.class)).toList();
	}
	
}
