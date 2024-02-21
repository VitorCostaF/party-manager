package br.com.ieoafestasedecoracoes.partymanager.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.com.ieoafestasedecoracoes.partymanager.domain.Decoration;
import br.com.ieoafestasedecoracoes.partymanager.repository.CategoryRepository;
import br.com.ieoafestasedecoracoes.partymanager.repository.CompanyRepository;
import br.com.ieoafestasedecoracoes.partymanager.repository.DecorationRepository;
import br.com.ieoafestasedecoracoes.partymanager.to.DecorationTO;
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
	
	public List<DecorationTO> findByDescription(String description, Map<String,String> params) {
		Specification<Decoration> specification = Specification.where(DecorationSpecification.decorationDescription(description));
		
		if( params.get("provinceId") != null && !params.get("provinceId").isEmpty()) {
			specification = specification.and(DecorationSpecification.hasProvince(Integer.valueOf(params.get("provinceId"))));
		}
		
		if( params.get("cityId") != null && !params.get("cityId").isEmpty()) {
			specification = specification.and(DecorationSpecification.hasCity(Integer.valueOf(params.get("cityId"))));
		}
		
		if( params.get("initialValue") != null && !params.get("initialValue").isEmpty()) {
			specification = specification.and(DecorationSpecification.hasInitialValue(new BigDecimal(params.get("initialValue"))));
		}
		
		if( params.get("finalValue") != null && !params.get("finalValue").isEmpty()) {
			specification = specification.and(DecorationSpecification.hasFinalValue(new BigDecimal(params.get("finalValue"))));
		}
		
		
		return toDecorationTOList(repository.findAll(specification));
		
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
	
	public List<DecorationTO> findByProvince(Integer provinceId) {
		return toDecorationTOList(repository.findByCompanyAddressProvinceId(provinceId));
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
	
	private class DecorationSpecification {
		
		public static Specification<Decoration> hasProvince(Integer provinceId) {
			return (root, query, criteriaBuilder) -> 
				criteriaBuilder.equal(root.get("company").get("address").get("province").get("id"), provinceId);
		}
		
		public static Specification<Decoration> hasCity(Integer cityId) {
			return (root, query, criteriaBuilder) -> 
				criteriaBuilder.equal(root.get("company").get("address").get("city").get("id"), cityId);
		}
		
		public static Specification<Decoration> hasInitialValue(BigDecimal initialValue) {
			return (root, query, criteriaBuilder) -> 
				criteriaBuilder.ge(root.get("price"), initialValue);
		}
		
		public static Specification<Decoration> hasFinalValue(BigDecimal finalValue) {
			return (root, query, criteriaBuilder) -> 
				criteriaBuilder.le(root.get("price"), finalValue);
		}
		
		public static Specification<Decoration> decorationDescription(String name) {
			return (root, query, criteriaBuilder) -> 
			criteriaBuilder.like(root.get("name"), name);
		}
		
	}

}
