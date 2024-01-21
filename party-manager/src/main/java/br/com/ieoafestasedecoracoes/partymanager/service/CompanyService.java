package br.com.ieoafestasedecoracoes.partymanager.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ieoafestasedecoracoes.partymanager.domain.Company;
import br.com.ieoafestasedecoracoes.partymanager.repository.CompanyRepository;
import br.com.ieoafestasedecoracoes.partymanager.to.CompanyTO;

@Service
public class CompanyService {

	@Autowired
	private CompanyRepository repository;
	
	@Autowired
	private ModelMapper mapper;

	public CompanyTO findById(Integer id) {
		Company company = repository.findById(id).orElse(null);
		if(company != null) {
			return mapper.map(company, CompanyTO.class);
		}
		return new CompanyTO();
	}

	public List<CompanyTO> findAll() {
		return repository.findAll().stream().map(c -> mapper.map(c, CompanyTO.class)).toList();
	}

	public void delete(Integer id) {
		repository.deleteById(id);
	}

	public CompanyTO create(CompanyTO companyTO) {
		Company company = mapper.map(companyTO, Company.class);
		company.setId(null);
		repository.save(company);
		return mapper.map(company, CompanyTO.class);
	}

	public CompanyTO update(Integer id, CompanyTO companyTO) {
		Company company = repository.findById(id).orElse(null);
		if (company == null) {
			throw new RuntimeException("Company does not exist to be updated");
		}

		company.setName(companyTO.getName());
		company.setDocument(companyTO.getDocument());

		return mapper.map(repository.save(company), CompanyTO.class);
	}

	public List<CompanyTO> findByName(String name) {
		return repository.findByNameContains(name).stream().map(company -> mapper.map(company, CompanyTO.class))
				.toList();
	}

}
