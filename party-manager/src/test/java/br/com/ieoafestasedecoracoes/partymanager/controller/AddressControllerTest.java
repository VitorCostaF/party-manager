package br.com.ieoafestasedecoracoes.partymanager.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

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
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.node.ObjectNode;

import br.com.ieoafestasedecoracoes.partymanager.testobjects.AddressObjects;
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
	private BasicControllerTest2 basicControllerTest;
	
	private AddressObjects addressObjects;
	
	@Autowired
	private MockMvc mockMvc;
	
	private ObjectMapper mapper;
	
	private AddressTO addressById;
	private AddressTO address1;
	private AddressTO addressToDelete;
	private AddressTO addressToUpdate;
	
	private String addressByIdJson;
	
	private String PATH = "/addresses";
	private String PATH_ID = PATH + "/{id}";
	
	@BeforeAll
	void setup() throws Exception {
		
		this.mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext)
				.defaultRequest(
					post(PATH)
					.contentType(MediaType.APPLICATION_JSON))
			.build();
		
		this.mapper = new ObjectMapper();
		this.mapper.setPropertyNamingStrategy(PropertyNamingStrategies.KEBAB_CASE);
		
		deleteAllAddresses();
		createAddresses();
	}
	
	@Test
	void shouldReturnById() throws Exception {
		ResultActions response = basicControllerTest.shouldReturnById(addressById, PATH_ID, mockMvc);
		response.andExpect(content().json(addressByIdJson));
	}
	
	private void deleteAllAddresses() throws Exception {
		
		String addressesToDeleteStr =  mockMvc
			.perform(
				get(PATH))
			.andReturn()
				.getResponse().getContentAsString();
		
		List<AddressTO> addressesToDelete = mapper.readValue(addressesToDeleteStr, new TypeReference<List<AddressTO>>() {});
		
		addressesToDelete.forEach(a -> {
			try {
				mockMvc.perform(delete(PATH_ID,a.getId()));
			} catch (Exception e) {
				log.info("problema ao deleter address {}", a.getId(), e);
			}
		});
		
	}
	
	private void createAddresses() throws Exception {
		
		addressById = new AddressTO(1, "By Id Street", "By Id City", "123456", "Complement By Id");
		address1 = new AddressTO(1, "Street 1", "City 1", "123456", "Complement 1");
		addressToDelete = new AddressTO(1, "Street To Delete", "City To Delete", "404", "Complement To Delete");
		addressToUpdate = new AddressTO(1, "Street To Update", "City To Update", "200", "Complement To Update");
		
		addressByIdJson = mapper.writeValueAsString(addressById);
				
		createAddress(addressById, mapper);
		createAddress(address1, mapper);
		createAddress(addressToDelete, mapper);
		createAddress(addressToUpdate, mapper);
		
		addressByIdJson = ((ObjectNode) mapper.readTree(addressByIdJson)).put("id", addressById.getId()).toString();
		
	}
	
	private void createAddress(AddressTO address, ObjectMapper mapper) throws UnsupportedEncodingException, Exception {
		
		String addressJsonStr = mapper.writeValueAsString(address);
		
		String response =  mockMvc
		.perform(
			post(PATH)
				.content(addressJsonStr))
			.andReturn()
				.getResponse()
				.getContentAsString();
		
		Integer id = mapper.readValue(response, JsonNode.class).get("id").asInt();
		address.setId(id);
		 
	}

}
