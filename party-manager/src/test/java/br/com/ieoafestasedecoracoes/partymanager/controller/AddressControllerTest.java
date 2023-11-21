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
	
	AddressTO addressById;
	AddressTO address2;
	
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
		address2 = new AddressTO(2, "Street 2", "City 2", "123456", "Complement 2"); 
		
		addressByIdJson = mapper.writeValueAsString(addressById);
		String address2JsonStr = mapper.writeValueAsString(address2);
		
		String responseById = mockMvc
				.perform(
					post("/address")
						.content(addressByIdJson))
					.andReturn()
						.getResponse()
						.getContentAsString();
		
		Integer idById = mapper.readValue(responseById, JsonNode.class).get("id").asInt();
		
		addressByIdJson = ((ObjectNode) mapper.readTree(addressByIdJson)).put("id", idById).toString();
		
		addressById.setId(idById);
		
		String response2 = mockMvc
				.perform(
					post("/address")
						.content(address2JsonStr))
					.andReturn()
						.getResponse()
						.getContentAsString();
		
		Integer id2 = mapper.readValue(response2, JsonNode.class).get("id").asInt();
		address2.setId(id2);
		
	}

}
