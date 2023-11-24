package br.com.ieoafestasedecoracoes.partymanager.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.node.ObjectNode;

import br.com.ieoafestasedecoracoes.partymanager.to.CompanyTO;
import br.com.ieoafestasedecoracoes.partymanager.util.RequestUtil;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
public class CompanyControllerTest {
	
	@Autowired
	private WebApplicationContext context;
	
	private MockMvc mockMvc;
	
	private ObjectMapper mapper;
	
	private CompanyTO companyById;
	private CompanyTO companyToDelete;
	private CompanyTO companyToUpdate;
	
	private String companyByIdJson;

	
	@BeforeAll
	void setup() throws UnsupportedEncodingException, Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
		mapper = new ObjectMapper();
		mapper.setPropertyNamingStrategy(PropertyNamingStrategies.KEBAB_CASE);
		deleteCompanies();
		createCompanies();
	}
	
	@Test
	void shouldReturnAtLeast2Companies() throws UnsupportedEncodingException, Exception {
		
		String response = mockMvc
			.perform(
				get("/companies"))
			.andReturn()
				.getResponse()
				.getContentAsString();
		
		List<JsonNode> companies = mapper.readValue(response, new TypeReference<List<JsonNode>>() {});
		assertTrue(companies.size() >= 2);
		
	}
	
	private void deleteCompanies() throws UnsupportedEncodingException, Exception {
		RequestUtil.deleteAllEntities(mockMvc, "/companies", "/companies", mapper);
	}
	
	private void createCompanies() throws UnsupportedEncodingException, Exception {
		companyById = new CompanyTO(1, "Company By id", "1234");
		companyToDelete = new CompanyTO(1, "Company To Delete", "12345");
		companyToUpdate = new CompanyTO(1, "Company To Update", "123456");
		
		companyByIdJson = mapper.writeValueAsString(companyById);
		
		RequestUtil.createEntity(mockMvc, "/companies", companyById, mapper);
		RequestUtil.createEntity(mockMvc, "/companies", companyToDelete, mapper);
		RequestUtil.createEntity(mockMvc, "/companies", companyToUpdate, mapper);
		
		companyByIdJson = ((ObjectNode) mapper.readTree(companyByIdJson)).put("id", companyById.getId()).toString();
		
	}

}
