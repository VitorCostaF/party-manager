package br.com.ieoafestasedecoracoes.partymanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ieoafestasedecoracoes.partymanager.domain.Vendor;
import br.com.ieoafestasedecoracoes.partymanager.repository.VendorRepository;

@Service
public class VendorService {

	@Autowired
	private VendorRepository repository;

	public List<Vendor> findAll() {
		return repository.findAll();
	}

	public Vendor findById(Integer id) {
		return repository.findById(id).orElse(null);
	}

	public void delete(Integer id) {
		repository.deleteById(id);
	}

	public Vendor update(Vendor user) {

		Vendor userBD = findById(user.getId());
		if (userBD == null) {
			throw new RuntimeException("Usuário não existente para atualização");
		}

		return repository.save(user);

	}

	public Vendor create(Vendor user) {
		user.setId(null);
		return repository.save(user);
	}

	public Vendor findByEmail(String firstName) {
		return repository.findByEmail(firstName);
	}

	public Vendor findByFirstName(String firstName) {
		return repository.findByFirstName(firstName);
	}

}
