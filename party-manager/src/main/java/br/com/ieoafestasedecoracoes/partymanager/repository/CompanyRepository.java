package br.com.ieoafestasedecoracoes.partymanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ieoafestasedecoracoes.partymanager.domain.Company;
import br.com.ieoafestasedecoracoes.partymanager.to.CompanyTO;

public interface CompanyRepository extends JpaRepository<Company, Integer>{

	List<CompanyTO> findByNameLike(String name);

}
