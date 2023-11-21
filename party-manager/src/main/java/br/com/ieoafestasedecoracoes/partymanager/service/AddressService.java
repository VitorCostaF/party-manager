package br.com.ieoafestasedecoracoes.partymanager.service;

import java.lang.reflect.Type;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ieoafestasedecoracoes.partymanager.domain.Address;
import br.com.ieoafestasedecoracoes.partymanager.repository.AddressRepository;
import br.com.ieoafestasedecoracoes.partymanager.to.AddressTO;

@Service
public class AddressService {

	@Autowired
	private AddressRepository repository;
	
	@Autowired
	private ModelMapper mapper;

	public AddressTO findById(Integer id) {
		return mapper.map(repository.findById(id).orElse(new Address()), AddressTO.class);
	}

	public List<AddressTO> findAll() {
		List<Address> address = repository.findAll();
		Type typeToken =  new TypeToken<List<AddressTO>>() {}.getType();
		return mapper.map(address, typeToken);
	}

	public void delete(Integer id) {
		repository.deleteById(id);
	}

	public AddressTO create(AddressTO addressTO) {
		Address address = mapper.map(addressTO, Address.class);
		address.setId(null);
		repository.save(address);
		return mapper.map(address, AddressTO.class);
	}

	public AddressTO update(Integer id, AddressTO addressTO) {
		Address address = repository.findById(id).orElse(null);
		if (address == null) {
			throw new RuntimeException("Company does not exist to be updated");
		}
		
		mapper.map(addressTO, address);
		
		repository.save(address);

		return addressTO;
	}

}
