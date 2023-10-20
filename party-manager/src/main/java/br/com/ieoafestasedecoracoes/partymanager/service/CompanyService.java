package br.com.ieoafestasedecoracoes.partymanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ieoafestasedecoracoes.partymanager.domain.Company;
import br.com.ieoafestasedecoracoes.partymanager.repository.CompanyRepository;
import br.com.ieoafestasedecoracoes.partymanager.to.CompanyTO;

@Service
public class CompanyService {

	@Autowired
	private CompanyRepository repository;

	public CompanyTO findById(Integer id) {
		return new CompanyTO(repository.findById(id).orElse(null));
	}

	public List<CompanyTO> findAll() {
		return CompanyTO.fromCompanyList(repository.findAll());
	}

	public void delete(Integer id) {
		repository.deleteById(id);
	}

	public CompanyTO create(CompanyTO companyTO) {
		Company company = new Company(companyTO);
		company.setId(null);
		repository.save(company);
		companyTO.setId(company.getId());
		return companyTO;
	}

	public CompanyTO update(Integer id, CompanyTO companyTO) {
		Company company = repository.findById(id).orElse(null);
		if (company == null) {
			throw new RuntimeException("Company does not exist to be updated");
		}

		company.setName(companyTO.getName());
		company.setDocument(companyTO.getDocument());

		return new CompanyTO(repository.save(company));
	}

	public List<CompanyTO> findByName(String name) {
		return repository.findByName(name);

	}

}
