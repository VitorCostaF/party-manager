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

import br.com.ieoafestasedecoracoes.partymanager.domain.Address;
import br.com.ieoafestasedecoracoes.partymanager.infra.DomainErrorsValidation;
import br.com.ieoafestasedecoracoes.partymanager.repository.AddressRepository;
import br.com.ieoafestasedecoracoes.partymanager.to.AddressTO;
import jakarta.servlet.ServletException;

// TODO adicionar os status code nas validações
@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(Lifecycle.PER_CLASS)
class AddressControllerTest {
	
	@Autowired
	private APICall basicControllerTest;
	
	@Autowired
	private AddressRepository repository;
	
	@Autowired
	private MockMvc mockMvc;
	
	private ObjectMapper mapper;
	
	@Autowired
	private ModelMapper modelMapper;
	
	private AddressTO addressById;
	private AddressTO addressToDelete;
	private AddressTO addressToUpdate;
	
	private String addressByIdJson;
	
	private String PATH = "/addresses";
	private String PATH_ID = PATH + "/{id}";
	
	@BeforeAll
	void setup() throws Exception {
		
		this.mapper = new ObjectMapper();
		this.mapper.setPropertyNamingStrategy(PropertyNamingStrategies.KEBAB_CASE);
		
		deleteAllAddresses();
		createAddresses();
	}
	
	@Test
	void shouldReturnById() throws Exception {
		MockHttpServletResponse response = basicControllerTest.executeById(addressById, PATH_ID, mockMvc);		
		assertThat(response.getContentAsString()).isEqualTo(addressByIdJson);		
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
		MockHttpServletResponse response = basicControllerTest.executeDelete(addressToDelete.getId(), PATH_ID, mockMvc);
		assertThat(response.getContentAsString()).isEqualTo("{}");
	}
	
	@Test
	void shouldUpdate() throws Exception {
		AddressTO addressUpdated = new AddressTO(1, "Street Updated", "City Updated", "20020020", "Complement Updated");
		addressUpdated.setId(addressToUpdate.getId());
		String objectUpdatedJson = mapper.writeValueAsString(addressUpdated);
		
		MockHttpServletResponse response = basicControllerTest.executeUpdate(objectUpdatedJson, addressUpdated.getId(), PATH_ID, mockMvc);
		assertThat(response.getContentAsString()).isEqualTo(objectUpdatedJson);
	}
	
	// TODO voltar o id de uma forma diferente
	@Test
	void shouldCreate() throws Exception {
		AddressTO addressToCreate = new AddressTO(1, "Street Address Created", "City Address Created", "12345678", "Complement Address Created");
		addressToCreate.setId(null);
		String addressToCreateJson = mapper.writeValueAsString(addressToCreate);
				
		MockHttpServletResponse response = basicControllerTest.executeCreate(addressToCreateJson, PATH, mockMvc);
		ObjectNode jsonResponse = ((ObjectNode)mapper.readTree(response.getContentAsString()));
		jsonResponse.remove("id");
		
		assertThat(jsonResponse.toString()).hasToString(addressToCreateJson);
	}
	
	@Test
	void shouldNotupdateAnInexistentId() throws Exception {
		String objectUpdatedJson = mapper.writeValueAsString(addressToUpdate);
		
		assertThatThrownBy(() -> basicControllerTest.executeUpdate(objectUpdatedJson, -1, PATH_ID, mockMvc))
			.isInstanceOf(ServletException.class);
	}
	
	@Test
	void mandatoryFieldsShouldBeFilled() throws Exception {
		AddressTO addressZipCode = new AddressTO(1, "", "", "", "");
		List<DomainErrorsValidation> errors = List.of(
				new DomainErrorsValidation("street", "rua deve ser preenchida"),
				new DomainErrorsValidation("city", "cidade deve ser preenchida"),
				new DomainErrorsValidation("zipCode", "CEP deve ser preenchido"),
				new DomainErrorsValidation("zipCode", "cep deve estar no formato 99999999 ou 99999-999")
				);
		
		MockHttpServletResponse response = basicControllerTest.executeCreate(mapper.writeValueAsString(addressZipCode), PATH, mockMvc);
		
		List<DomainErrorsValidation> errorsResponse = mapper.readValue(response.getContentAsString(), new TypeReference<List<DomainErrorsValidation>>() {});
		
		assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
		assertThat(errorsResponse).containsAll(errors);
		
	}
	
	@Test
	void zipCodeShouldBeFilledCorrectly() throws Exception {
		AddressTO addressZipCode = new AddressTO(1, "1234", "1243", "99999-99", "");
		List<DomainErrorsValidation> errors = List.of(new DomainErrorsValidation("zipCode", "cep deve estar no formato 99999999 ou 99999-999"));
		
		MockHttpServletResponse response = basicControllerTest.executeCreate(mapper.writeValueAsString(addressZipCode), PATH, mockMvc);
		
		List<DomainErrorsValidation> errorsResponse = mapper.readValue(response.getContentAsString(), new TypeReference<List<DomainErrorsValidation>>() {});
		
		assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
		assertThat(errorsResponse).containsAll(errors);
		
	}
	
	private void deleteAllAddresses() throws Exception {
		
		List<Address> addresses = repository.findAll();
		
		addresses.forEach(repository::delete);
		
	}
	
	private void createAddresses() throws Exception {
		
		addressById = new AddressTO(1, "By Id Street", "By Id City", "12345678", "Complement By Id");
		addressToDelete = new AddressTO(1, "Street To Delete", "City To Delete", "40440440", "Complement To Delete");
		addressToUpdate = new AddressTO(1, "Street To Update", "City To Update", "20020-020", "Complement To Update");
		
		createAddress(addressById);
		createAddress(addressToDelete);		
		createAddress(addressToUpdate);
		
		addressByIdJson = mapper.writeValueAsString(addressById);		
	}
	
	private void createAddress(AddressTO addressTO) throws Exception {
		Address address =  repository.save(modelMapper.map(addressTO, Address.class));
		addressTO.setId(address.getId());
	}
	
}
