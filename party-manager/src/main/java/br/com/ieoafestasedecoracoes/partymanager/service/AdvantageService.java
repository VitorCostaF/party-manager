package br.com.ieoafestasedecoracoes.partymanager.service;

import java.lang.reflect.Type;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ieoafestasedecoracoes.partymanager.domain.Advantage;
import br.com.ieoafestasedecoracoes.partymanager.repository.AdvantageRepository;
import br.com.ieoafestasedecoracoes.partymanager.to.AdvantageTO;

@Service
public class AdvantageService {

	@Autowired
	private AdvantageRepository repository;
	
	@Autowired
	private ModelMapper mapper;

	public AdvantageTO findById(Integer id) {
		return mapper.map(repository.findById(id).orElse(new Advantage()), AdvantageTO.class);
	}

	public List<AdvantageTO> findAll() {
		List<Advantage> advantage = repository.findAll();
		Type typeToken =  new TypeToken<List<AdvantageTO>>() {}.getType();
		return mapper.map(advantage, typeToken);
	}

	public void delete(Integer id) {
		repository.deleteById(id);
	}

	public AdvantageTO create(AdvantageTO advantageTO) {
		Advantage advantage = mapper.map(advantageTO, Advantage.class);
		advantage.setId(null);
		repository.save(advantage);
		return mapper.map(advantage, AdvantageTO.class);
	}

	public AdvantageTO update(Integer id, AdvantageTO advantageTO) {
		Advantage advantage = repository.findById(id).orElse(null);
		if (advantage == null) {
			throw new RuntimeException("Advantage does not exist to be updated");
		}
		
		mapper.map(advantageTO, advantage);
		
		repository.save(advantage);

		return advantageTO;
	}

}
