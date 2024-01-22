package br.com.ieoafestasedecoracoes.partymanager.service;

import java.lang.reflect.Type;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ieoafestasedecoracoes.partymanager.domain.Category;
import br.com.ieoafestasedecoracoes.partymanager.repository.CategoryRepository;
import br.com.ieoafestasedecoracoes.partymanager.to.CategoryTO;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repository;
	
	@Autowired
	private ModelMapper mapper;

	public CategoryTO findById(Integer id) {
		return mapper.map(repository.findById(id).orElse(new Category()), CategoryTO.class);
	}

	public List<CategoryTO> findAll() {
		List<Category> category = repository.findAll();
		Type typeToken =  new TypeToken<List<CategoryTO>>() {}.getType();
		return mapper.map(category, typeToken);
	}

	public void delete(Integer id) {
		repository.deleteById(id);
	}

	public CategoryTO create(CategoryTO categoryTO) {
		Category category = mapper.map(categoryTO, Category.class);
		
		
		
		category.setId(null);
		repository.save(category);
		return mapper.map(category, CategoryTO.class);
	}

	public CategoryTO update(Integer id, CategoryTO categoryTO) {
		Category category = repository.findById(id).orElse(null);
		if (category == null) {
			throw new RuntimeException("Category does not exist to be updated");
		}
		
		mapper.map(categoryTO, category);
		
		repository.save(category);

		return categoryTO;
	}

}
