package br.com.ieoafestasedecoracoes.partymanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ieoafestasedecoracoes.partymanager.domain.Company;

public interface CompanyRepository extends JpaRepository<Company, Integer>{

}
