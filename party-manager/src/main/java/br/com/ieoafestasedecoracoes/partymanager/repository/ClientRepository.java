package br.com.ieoafestasedecoracoes.partymanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ieoafestasedecoracoes.partymanager.domain.Client;


public interface ClientRepository extends JpaRepository<Client, Integer>{

	List<Client> findByFirstName(String firstName);
	
	Client findByEmail(String email);
}
