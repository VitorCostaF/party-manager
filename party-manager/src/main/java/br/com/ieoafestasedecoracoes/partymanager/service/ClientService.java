package br.com.ieoafestasedecoracoes.partymanager.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ieoafestasedecoracoes.partymanager.domain.Client;
import br.com.ieoafestasedecoracoes.partymanager.repository.ClientRepository;
import br.com.ieoafestasedecoracoes.partymanager.to.ClientTO;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repository;

	@Autowired
	private ModelMapper mapper;

	
	public ClientTO findById(Integer id) {
		Client client = repository.findById(id).orElse(null);
		if(client != null) {
			return mapper.map(client, ClientTO.class);
		}
		return new ClientTO();
	}
	
	public List<ClientTO> findAll() {
		return toClientTOList(repository.findAll());
	}
	
	public void delete(Integer id) {
		repository.deleteById(id);
	}
	
	public ClientTO update(ClientTO client, Integer id) {
		Client clientDB = repository.findById(id).orElse(null);
		
		if(clientDB == null) {
			throw new RuntimeException("Client not found to be updated");
		}
		
		client.setId(id);
		mapper.map(client, clientDB);	
		
		repository.save(clientDB);
		return client;
	}
	
	public ClientTO create(ClientTO client) {
		Client clientBD = mapper.map(client, Client.class);
		
		clientBD.setId(null);
		repository.save(clientBD);
		
		client.setId(clientBD.getId());
		
		return client;
	}
	
	public List<Client> findByFirstName(String firstName) {
		return repository.findByFirstName(firstName);
	}
	
	public ClientTO findByEmail(String email) {
		Client client = repository.findByEmail(email);
		if(client != null) {
			return mapper.map(client, ClientTO.class);
		}
		return new ClientTO();
	}
	
	private List<ClientTO> toClientTOList(List<Client> clients) {
		return clients.stream().map(v -> mapper.map(v, ClientTO.class)).toList();
	}
	
}
