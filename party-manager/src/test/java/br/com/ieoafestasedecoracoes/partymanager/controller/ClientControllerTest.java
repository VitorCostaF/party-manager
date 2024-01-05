package br.com.ieoafestasedecoracoes.partymanager.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.node.ObjectNode;

import br.com.ieoafestasedecoracoes.partymanager.domain.Client;
import br.com.ieoafestasedecoracoes.partymanager.domain.ClientType;
import br.com.ieoafestasedecoracoes.partymanager.infra.DomainErrorsValidation;
import br.com.ieoafestasedecoracoes.partymanager.repository.ClientRepository;
import br.com.ieoafestasedecoracoes.partymanager.to.ClientTO;
import jakarta.servlet.ServletException;

// TODO adicionar os status code nas validações
@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(Lifecycle.PER_CLASS)
class ClientControllerTest {
	
	@Autowired
	private APICall basicControllerTest;
	
	@Autowired
	private ClientRepository repository;
	
	@Autowired
	private MockMvc mockMvc;
	
	private ObjectMapper mapper;
	
	@Autowired
	private ModelMapper modelMapper;
	
	private ClientTO clientById;
	private ClientTO clientToDelete;
	private ClientTO clientToUpdate;
	
	private String clientByIdJson;
	
	private String PATH = "/clients";
	private String PATH_ID = PATH + "/{id}";
	
	@BeforeAll
	void setup() throws Exception {
		
		this.mapper = new ObjectMapper();
		this.mapper.setPropertyNamingStrategy(PropertyNamingStrategies.KEBAB_CASE);
		
		deleteAllClientes();
		createClientes();
	}
	
	@Test
	void shouldReturnById() throws Exception {
		MockHttpServletResponse response = basicControllerTest.executeById(clientById, PATH_ID, mockMvc);		
		assertThat(response.getContentAsString()).isEqualTo(clientByIdJson);		
	}
	
	@Test
	void shouldReturnAtLeast2Objects() throws Exception {
		List<JsonNode> objsJson;
		MockHttpServletResponse response = basicControllerTest.executeFindAll(PATH, mockMvc);
		
		String jsonResponse = response.getContentAsString();
		objsJson = mapper.readValue(jsonResponse, new TypeReference<List<JsonNode>>() {});
		
		assertThat(objsJson).hasSizeGreaterThanOrEqualTo(2);
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		
	}
	
	@Test
	void shouldDelete() throws Exception {
		MockHttpServletResponse response = basicControllerTest.executeDelete(clientToDelete.getId(), PATH_ID, mockMvc);
		assertThat(response.getContentAsString()).isEqualTo("{}");
	}
	
	@Test
	void shouldUpdate() throws Exception {
		ClientTO clientUpdated = new ClientTO(1, "123456", "clientUpdated", "Updated", ClientType.LEGAL_PERSON, "client.updated@email.com", "updated");
		clientUpdated.setId(clientToUpdate.getId());
		String objectUpdatedJson = mapper.writeValueAsString(clientUpdated);
		
		MockHttpServletResponse response = basicControllerTest.executeUpdate(objectUpdatedJson, clientUpdated.getId(), PATH_ID, mockMvc);
		assertThat(response.getContentAsString()).isEqualTo(objectUpdatedJson);
	}
	
	// TODO voltar o id de uma forma diferente
	@Test
	void shouldCreate() throws Exception {
		ClientTO clientToCreate = new ClientTO(1, "123678", "clientToCreate", "To Create", ClientType.LEGAL_PERSON, "client.to.create@email.com", "toCreate");
		clientToCreate.setId(null);
		String clientToCreateJson = mapper.writeValueAsString(clientToCreate);
				
		MockHttpServletResponse response = basicControllerTest.executeCreate(clientToCreateJson, PATH, mockMvc);
		ObjectNode jsonResponse = ((ObjectNode)mapper.readTree(response.getContentAsString()));
		jsonResponse.remove("id");
		
		assertThat(jsonResponse.toString()).hasToString(clientToCreateJson);
	}
	
	@Test
	void shouldNotupdateAnInexistentId() throws Exception {
		String objectUpdatedJson = mapper.writeValueAsString(clientToUpdate);
		
		assertThatThrownBy(() -> basicControllerTest.executeUpdate(objectUpdatedJson, -1, PATH_ID, mockMvc))
			.isInstanceOf(ServletException.class);
	}
	
	
	private void deleteAllClientes() throws Exception {
		
		List<Client> clientes = repository.findAll();
		
		clientes.forEach(repository::delete);
		
	}
	
	private void createClientes() throws Exception {
		
		clientById = new ClientTO(1, "123", "clientById", "By Id", ClientType.LEGAL_PERSON, "client.by.id@email.com", "byid");
		clientToDelete = new ClientTO(1, "1234", "clientToDelete", "To Delete", ClientType.PHYSICAL_PERSON, "client.to.delete@email.com", "todelete");
		clientToUpdate = new ClientTO(1, "12345", "clientToUpdate", "To Update", ClientType.LEGAL_PERSON, "client.to.update@email.com", "toupdate");
		
		createClient(clientById);
		createClient(clientToDelete);		
		createClient(clientToUpdate);
		
		clientByIdJson = mapper.writeValueAsString(clientById);		
	}
	
	private void createClient(ClientTO clientTO) throws Exception {
		Client client =  repository.save(modelMapper.map(clientTO, Client.class));
		clientTO.setId(client.getId());
	}
	
}
