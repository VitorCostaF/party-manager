package br.com.ieoafestasedecoracoes.partymanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ieoafestasedecoracoes.partymanager.domain.Company;

public interface CompanyRepository extends JpaRepository<Company, Integer>{

	List<Company> findByNameContains(String name);
	List<Company> findByNameLike(String name);

}
