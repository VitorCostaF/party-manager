package br.com.ieoafestasedecoracoes.partymanager.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ieoafestasedecoracoes.partymanager.domain.Vendor;
import br.com.ieoafestasedecoracoes.partymanager.repository.VendorRepository;
import br.com.ieoafestasedecoracoes.partymanager.to.VendorTO;

@Service
public class VendorService {

	@Autowired
	private VendorRepository repository;
	
	@Autowired
	private ModelMapper mapper;

	public List<VendorTO> findAll() {
		return toVendorTOList(repository.findAll());
	}

	public VendorTO findById(Integer id) {
		
		Vendor vendor = repository.findById(id).orElse(null);
		if(vendor != null) {
			return mapper.map(vendor, VendorTO.class);
		}
		return new VendorTO();
	}

	public void delete(Integer id) {
		repository.deleteById(id);
	}

	public VendorTO update(VendorTO vendor, Integer id) {

		VendorTO userBD = findById(id);
		if (userBD == null) {
			throw new RuntimeException("Usuário não existente para atualização");
		}
		
		repository.save(mapper.map(vendor, Vendor.class));
		return vendor;

	}

	public VendorTO create(Vendor user) {
		user.setId(null);
		return mapper.map(repository.save(user), VendorTO.class);
	}

	public VendorTO findByEmail(String firstName) {
		
		Vendor vendor = repository.findByEmail(firstName);
		if(vendor != null) {
			return mapper.map(vendor, VendorTO.class);
		}
		return new VendorTO();
	}

	public List<VendorTO> findByFirstName(String firstName) {
		return toVendorTOList(repository.findByFirstName(firstName));
	}
	
	private List<VendorTO> toVendorTOList(List<Vendor> vendors) {
		return vendors.stream().map(v -> mapper.map(v, VendorTO.class)).toList();
	}

}
