package br.com.ieoafestasedecoracoes.partymanager.service;

import java.lang.reflect.Type;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ieoafestasedecoracoes.partymanager.domain.City;
import br.com.ieoafestasedecoracoes.partymanager.repository.CityRepository;
import br.com.ieoafestasedecoracoes.partymanager.to.CityTO;

@Service
public class CityService {

	@Autowired
	private CityRepository repository;
	
	@Autowired
	private ModelMapper mapper;

	public CityTO findById(Integer id) {
		return mapper.map(repository.findById(id).orElse(new City()), CityTO.class);
	}

	public List<CityTO> findAll() {
		List<City> city = repository.findAll();
		Type typeToken =  new TypeToken<List<CityTO>>() {}.getType();
		return mapper.map(city, typeToken);
	}

	public void delete(Integer id) {
		repository.deleteById(id);
	}

	public CityTO create(CityTO cityTO) {
		City city = mapper.map(cityTO, City.class);
		city.setId(null);
		repository.save(city);
		return mapper.map(city, CityTO.class);
	}

	public CityTO update(Integer id, CityTO cityTO) {
		City city = repository.findById(id).orElse(null);
		if (city == null) {
			throw new RuntimeException("City does not exist to be updated");
		}
		
		mapper.map(cityTO, city);
		
		repository.save(city);

		return cityTO;
	}

}
