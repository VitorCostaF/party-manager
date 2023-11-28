package br.com.ieoafestasedecoracoes.partymanager.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.node.ObjectNode;

import br.com.ieoafestasedecoracoes.partymanager.to.CompanyTO;
import br.com.ieoafestasedecoracoes.partymanager.util.RequestUtil;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
class CompanyControllerTest {
	
	private final String PATH = "/companies";
	private final String PATH_ID = PATH + "/{id}"; 
	
	@Autowired
	private WebApplicationContext context;
	
	private MockMvc mockMvc;
	
	private ObjectMapper mapper;
	
	private CompanyTO companyById;
	private CompanyTO companyToDelete;
	private CompanyTO companyToUpdate;
	
	private String companyByIdJson;
	private CompanyTO companyByName1;
	private CompanyTO companyByName2;
	private CompanyTO companyByName3;

	
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
		
		String response = findAllCompanies();
		
		List<JsonNode> companies = mapper.readValue(response, new TypeReference<List<JsonNode>>() {});
		assertTrue(companies.size() >= 2);
		
	}
	
	@Test
	void shouldReturnById() throws Exception {
		mockMvc
			.perform(
				get(PATH_ID, companyById.getId()))
			.andExpect(content().json(companyByIdJson));
	}
	
	@Test
	void shouldDelete() throws Exception {
		mockMvc
			.perform(
				delete(PATH_ID, companyToDelete.getId()))
			.andExpect(status().isNoContent());
		
		mockMvc
			.perform(
				get(PATH_ID, companyToDelete.getId()))
			.andExpect(content().json("{}"));
		
	}
	
	@Test
	void shouldCreate() throws Exception {
		CompanyTO companyToCreate = new CompanyTO(1, "Company To Create", "1234567");
		companyToCreate.setId(null);
		String companyToCreateJson = mapper.writeValueAsString(companyToCreate);
		
		mockMvc
			.perform(
				post(PATH)
					.contentType(MediaType.APPLICATION_JSON)
					.content(companyToCreateJson))
			.andExpect(
				content().json(companyToCreateJson, false));
	}
	
	@Test
	void shouldUpdate() throws Exception {
		
		CompanyTO companyUpdated = new CompanyTO(1, "Company Updated", "12");
		companyUpdated.setId(companyToUpdate.getId());
		String companyUpdatedJson = mapper.writeValueAsString(companyUpdated);
		
		mockMvc
			.perform(
				put(PATH_ID, companyToUpdate.getId())
					.contentType(MediaType.APPLICATION_JSON)
					.content(companyUpdatedJson))
			.andExpect(content().json(companyUpdatedJson));
	}
	
	@Test
	void shouldReturnTwoCompaniesByNameWithoutSpace() throws Exception {
		
		String companyName = "Company";
		
		String allCompaniesJson = findAllCompanies();
		String companiesFiltered = filterCompanyByName(companyName, allCompaniesJson);
		
		mockMvc
			.perform(
				get(PATH + "/name/{name}", companyName))
			.andExpect(content().json(companiesFiltered, false));
	}
	
	@Test
	void shouldReturnTwoCompaniesByNameWithSpace() throws Exception {
		
		String companyName = "Company By Name";
		
		String allCompaniesJson = findAllCompanies();
		String companiesFiltered = filterCompanyByName(companyName, allCompaniesJson);
		
		mockMvc
			.perform(
				get(PATH + "/name/{name}", companyName))
			.andExpect(content().json(companiesFiltered, false));
	}
	
	private String filterCompanyByName(String name, String jsonStr) throws JsonMappingException, JsonProcessingException {
		List<CompanyTO> companies = mapper.readValue(jsonStr, new TypeReference<List<CompanyTO>>() {});
		companies = companies.stream().filter(c -> c.getName().contains(name)).toList();
		return mapper.writeValueAsString(companies);
	}
	
	private String findAllCompanies() throws UnsupportedEncodingException, Exception {
		return mockMvc
				.perform(
						get(PATH))
				.andReturn()
				.getResponse()
				.getContentAsString();
	}
	
	private void deleteCompanies() throws UnsupportedEncodingException, Exception {
		RequestUtil.deleteAllEntities(mockMvc, PATH, PATH, mapper);
	}
	
	
	private void createCompanies() throws UnsupportedEncodingException, Exception {
		companyById = new CompanyTO(1, "Company By id", "1234");
		companyToDelete = new CompanyTO(1, "Company To Delete", "12345");
		companyToUpdate = new CompanyTO(1, "Company To Update", "123456");
		companyByName1 = new CompanyTO(1, "Company By Name 1", "1234567");
		companyByName2 = new CompanyTO(1, "Company By Name 2", "1234568");
		companyByName3 = new CompanyTO(1, "By Name 3", "12345689");
		
		companyByIdJson = mapper.writeValueAsString(companyById);
		
		RequestUtil.createEntity(mockMvc, PATH, companyById, mapper);
		RequestUtil.createEntity(mockMvc, PATH, companyToDelete, mapper);
		RequestUtil.createEntity(mockMvc, PATH, companyToUpdate, mapper);
		RequestUtil.createEntity(mockMvc, PATH, companyByName1, mapper);
		RequestUtil.createEntity(mockMvc, PATH, companyByName2, mapper);
		RequestUtil.createEntity(mockMvc, PATH, companyByName3, mapper);
		
		companyByIdJson = ((ObjectNode) mapper.readTree(companyByIdJson)).put("id", companyById.getId()).toString();
		
	}

}
