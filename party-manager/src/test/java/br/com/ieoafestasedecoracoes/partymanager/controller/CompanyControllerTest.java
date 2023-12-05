package br.com.ieoafestasedecoracoes.partymanager.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
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
//		deleteCompanies();
//		createCompanies();
	}
	
	@Test
	void shouldReturnTwoCompaniesByNameWithoutSpace() throws Exception {
		
		String companyName = "Company";
		
		String allCompaniesJson = findAllCompanies();
		String companiesFiltered = filterCompanyByName(companyName, allCompaniesJson);
		
		mockMvc
			.perform(
				get(PATH + "/name/{name}", companyName))
			.andExpect(
				content().json(companiesFiltered, false));
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
			.perform(get(PATH))
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
