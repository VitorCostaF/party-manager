package br.com.ieoafestasedecoracoes.partymanager.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ieoafestasedecoracoes.partymanager.domain.Decoration;
import br.com.ieoafestasedecoracoes.partymanager.domain.Party;
import br.com.ieoafestasedecoracoes.partymanager.repository.CategoryRepository;
import br.com.ieoafestasedecoracoes.partymanager.repository.CompanyRepository;
import br.com.ieoafestasedecoracoes.partymanager.repository.DecorationRepository;
import br.com.ieoafestasedecoracoes.partymanager.repository.PartyRepository;
import br.com.ieoafestasedecoracoes.partymanager.to.DecorationTO;
import br.com.ieoafestasedecoracoes.partymanager.to.RentDecorationTO;
import br.com.ieoafestasedecoracoes.partymanager.validation.EntityDependencyValidation;

@Service
public class DecorationService {

	@Autowired
	private DecorationRepository repository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private CompanyRepository companyRepository;
	
	@Autowired
	private PartyRepository partyRepository;
	
	@Autowired
	private RentDecorationService rentDecorationService;
	
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
	
	public List<DecorationTO> findByCategory(Integer categoryId) {
		return toDecorationTOList(repository.findByCategoriesId(categoryId));
	}
	
	public List<DecorationTO> findByCompany(Integer companyId) {
		return toDecorationTOList(repository.findByCompanyId(companyId));
	}
	
	public List<DecorationTO> findHotDecorations() {
		return toDecorationTOList(repository.findHotDecorations());
	}
	
	public void delete(Integer id) {
		repository.deleteById(id);
	}
	
	public DecorationTO update(DecorationTO decoration, Integer id) {
		Decoration decorationDB = repository.findById(id).orElse(null);
				
		if(decorationDB == null) {
			throw new RuntimeException("Decoration not found to be updated");
		}
		
		EntityDependencyValidation.validate(companyRepository, decoration.getCompanyId(), "Company", "Decoration");
		decoration.getCategories().forEach(c -> EntityDependencyValidation.validate(categoryRepository, c.getId(), "Category", "Decoration"));
		
		decoration.setId(id);
		mapper.map(decoration, decorationDB);	
		
		repository.save(decorationDB);
		return decoration;
	}
	
	public DecorationTO create(DecorationTO decoration) {
		Decoration decorationBD = mapper.map(decoration, Decoration.class);
		
		EntityDependencyValidation.validate(companyRepository, decoration.getCompanyId(), "Company", "Decoration");
		decoration.getCategories().forEach(c -> EntityDependencyValidation.validate(categoryRepository, c.getId(), "Category", "Decoration"));
		
		decorationBD.setId(null);
		repository.save(decorationBD);
		
		decoration.setId(decorationBD.getId());
		
		return decoration;
	}
	
	private List<DecorationTO> toDecorationTOList(List<Decoration> decorations) {
		return decorations.stream().map(v -> mapper.map(v, DecorationTO.class)).toList();
	}
	
}
