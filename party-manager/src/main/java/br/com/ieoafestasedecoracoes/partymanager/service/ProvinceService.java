package br.com.ieoafestasedecoracoes.partymanager.service;

import java.lang.reflect.Type;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ieoafestasedecoracoes.partymanager.domain.Province;
import br.com.ieoafestasedecoracoes.partymanager.repository.ProvinceRepository;
import br.com.ieoafestasedecoracoes.partymanager.to.ProvinceTO;

@Service
public class ProvinceService {

	@Autowired
	private ProvinceRepository repository;
	
	@Autowired
	private ModelMapper mapper;

	public ProvinceTO findById(Integer id) {
		return mapper.map(repository.findById(id).orElse(new Province()), ProvinceTO.class);
	}

	public List<ProvinceTO> findAll() {
		List<Province> province = repository.findAll();
		Type typeToken =  new TypeToken<List<ProvinceTO>>() {}.getType();
		return mapper.map(province, typeToken);
	}

	public void delete(Integer id) {
		repository.deleteById(id);
	}

	public ProvinceTO create(ProvinceTO provinceTO) {
		Province province = mapper.map(provinceTO, Province.class);
		province.setId(null);
		repository.save(province);
		return mapper.map(province, ProvinceTO.class);
	}

	public ProvinceTO update(Integer id, ProvinceTO provinceTO) {
		Province province = repository.findById(id).orElse(null);
		if (province == null) {
			throw new RuntimeException("Province does not exist to be updated");
		}
		
		mapper.map(provinceTO, province);
		
		repository.save(province);

		return provinceTO;
	}

}
