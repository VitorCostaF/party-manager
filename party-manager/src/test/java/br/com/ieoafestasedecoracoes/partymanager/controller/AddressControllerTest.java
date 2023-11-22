package br.com.ieoafestasedecoracoes.partymanager.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.node.ObjectNode;

import br.com.ieoafestasedecoracoes.partymanager.to.AddressTO;
import lombok.extern.log4j.Log4j2;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(Lifecycle.PER_CLASS)
@Log4j2
class AddressControllerTest {
	
	@Autowired
	private WebApplicationContext applicationContext;
	
	@Autowired
	private MockMvc mockMvc;
	
	private ObjectMapper mapper;
	
	private AddressTO addressById;
	private AddressTO address1;
	private AddressTO addressToDelete;
	
	String addressByIdJson;

	
	@BeforeAll
	void setup() throws Exception {
		
		this.mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext)
				.defaultRequest(
					post("/address")
					.contentType(MediaType.APPLICATION_JSON))
			.build();
		
		this.mapper = new ObjectMapper();
		this.mapper.setPropertyNamingStrategy(PropertyNamingStrategies.KEBAB_CASE);
		
		deleteAllAddresses();
		createAddresses();
	}
	
	@Test
	void findAllShouldReturnOk() throws Exception {
		mockMvc.perform(get("/address")).andExpect(status().isOk());
	}
	
	@Test
	void findAllShoudlReturnAtLeastTwoAddresses() throws UnsupportedEncodingException, Exception {
		
		String responseJson = mockMvc
			.perform(
				get("/address"))
			.andReturn()
				.getResponse()
				.getContentAsString();
		
		Integer size =  mapper.readValue(responseJson, new TypeReference<List<AddressTO>>() {}).size();
		assertTrue(size >= 2);
		
	}
	
	@Test
	void shouldReturnAnAddressById() throws Exception {
		
		mockMvc
			.perform(
				get("/address/{id}", addressById.getId()))
			.andExpect(content().json(addressByIdJson));
		
	}
	
	@Test
	void shouldDeleteById() throws Exception {
		mockMvc
			.perform(
				delete("/address/{id}", addressToDelete.getId()))
			.andExpect(status().isNoContent());
		
		 mockMvc
			.perform(
				get("/address/{id}", addressToDelete.getId()))
			.andExpect(content().json("{}"));
	}
	
	@Test
	void shouldCreateAnAddress() throws Exception {
		
		AddressTO addressToCreate = new AddressTO(1, "Street Address Created", "City Address Created", "1234",
				"Complement Address Created");
		
		JsonNode addressToCreateJson = mapper.valueToTree(addressToCreate);
		((ObjectNode)addressToCreateJson).remove("id");
		
		 mockMvc
			.perform(
				post("/address")
					.content(addressToCreateJson.toString())
					.contentType(MediaType.APPLICATION_JSON))
				.andExpect(
					content()
						.json(addressToCreateJson.toString(), false));
	}
	
	private void deleteAllAddresses() throws Exception {
		
		String addressesToDeleteStr =  mockMvc
			.perform(
				get("/address"))
			.andReturn()
				.getResponse().getContentAsString();
		
		List<AddressTO> addressesToDelete = mapper.readValue(addressesToDeleteStr, new TypeReference<List<AddressTO>>() {});
		
		addressesToDelete.forEach(a -> {
			try {
				mockMvc.perform(delete("/address/{id}",a.getId()));
			} catch (Exception e) {
				log.info("problema ao deleter address {}", a.getId(), e);
			}
		});
		
	}
	
	private void createAddresses() throws Exception {
		
		addressById = new AddressTO(1, "By Id Street", "By Id City", "123456", "Complement By Id");
		address1 = new AddressTO(1, "Street 1", "City 1", "123456", "Complement 1");
		addressToDelete = new AddressTO(1, "Street To Delete", "City To Delete", "404", "Complement To Delete");
				
		createAddress(addressById, mapper);
		createAddress(address1, mapper);
		createAddress(addressToDelete, mapper);
		
		addressByIdJson = mapper.writeValueAsString(addressById);
		addressByIdJson = ((ObjectNode) mapper.readTree(addressByIdJson)).put("id", addressById.getId()).toString();
		
	}
	
	private void createAddress(AddressTO address, ObjectMapper mapper) throws UnsupportedEncodingException, Exception {
		
		String addressJsonStr = mapper.writeValueAsString(address);
		
		String response =  mockMvc
		.perform(
			post("/address")
				.content(addressJsonStr))
			.andReturn()
				.getResponse()
				.getContentAsString();
		
		Integer id = mapper.readValue(response, JsonNode.class).get("id").asInt();
		address.setId(id);
		 
	}

}
