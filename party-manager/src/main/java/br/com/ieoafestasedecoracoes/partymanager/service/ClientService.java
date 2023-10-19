package br.com.ieoafestasedecoracoes.partymanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ieoafestasedecoracoes.partymanager.domain.Client;
import br.com.ieoafestasedecoracoes.partymanager.repository.ClientRepository;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repository;
	
	public Client findById(Integer id) {
		return repository.findById(id).orElse(null);
	}
	
	public List<Client> findAll() {
		return repository.findAll();
	}
	
	public void delete(Integer id) {
		repository.deleteById(id);
	}
	
	public Client update(Client client) {
		Client clientDB = findById(client.getId());
		
		if(clientDB == null) {
			throw new RuntimeException("Client not found to be updated");
		}
		
		return repository.save(client);
	}
	
	public Client create(Client client) {
		client.setId(null);
		return repository.save(client);
	}
	
	public List<Client> findByFirstName(String firstName) {
		return repository.findByFirstName(firstName);
	}
	
	public Client findByEmail(String email) {
		return repository.findByEmail(email);
	}
	
}
