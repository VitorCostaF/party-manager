package br.com.ieoafestasedecoracoes.partymanager.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.node.ObjectNode;

import br.com.ieoafestasedecoracoes.partymanager.testobjects.VendorObjects;
import br.com.ieoafestasedecoracoes.partymanager.to.CompanyTO;
import br.com.ieoafestasedecoracoes.partymanager.to.VendorTO;
import br.com.ieoafestasedecoracoes.partymanager.util.RequestUtil;
import jakarta.servlet.ServletException;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
class VendorControllerTest {

	@Autowired
	private WebApplicationContext applicationContext;
	
	@Autowired
	private VendorObjects vendorObjects;
	
	private MockMvc mockMvc;	
	
	private ObjectMapper mapper;
	
	private VendorTO vendorByEmail = new VendorTO(1, "VendorByEmail", "ByEmail", "vendor.by.email@email.com", "1235", 1);;	
	
	private CompanyTO company = new CompanyTO(1, "Company To Vendors", "12348");
	private VendorTO vendorByFirstName1 = new VendorTO(1, "VendorByFirstName", "ByFirstName", "vendor.by.firstname@email.com", "1235", 1);
	private VendorTO vendorByFirstName2 = new VendorTO(1, "VendorByFirstName", "ByFirstName2", "vendor.by.firstname2@email.com", "1235", 1);
	
	private String vendorByEmailJson;
	private String vendorByFirstName1Json;
	private String vendorByFirstName2Json;
	
	private String PATH = "/vendors";
	private String PATH_ID = PATH + "/{id}";



	@BeforeAll
	void setup() throws Exception {
		
		this.mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext).build();
		this.mapper = new ObjectMapper().setPropertyNamingStrategy(PropertyNamingStrategies.KEBAB_CASE);
		
		deleteAllVendors();
		createVendors();
	}
	
	@Test
	void shouldReturnByEmail() throws Exception {
		mockMvc
			.perform(
				get(PATH + "/email/{email}", vendorByEmail.getEmail()))
			.andExpect(
				content()
					.json(vendorByEmailJson));
	}
	
//	TODO implementar o retorno correto quando for tratada a excessão
	@Test
	void shouldNotCreateTheSameEmail() throws Exception {
		
		Assertions.assertThrows(ServletException.class, () ->
			mockMvc
				.perform(
					post(PATH)
						.contentType(MediaType.APPLICATION_JSON)
						.content(vendorByEmailJson)));
	}
	
	
	@Test
	void shouldReturnByFirstName() throws Exception {
		
		String vendors = mapper.writeValueAsString(Arrays.asList(vendorByFirstName1, vendorByFirstName2));
		
		mockMvc
			.perform(
				get(PATH + "/firstname/{firstname}", vendorByFirstName1.getFirstName()))
			.andExpect(
				content()
					.json(vendors));
		
	}
	
	private void deleteAllVendors() throws Exception {
		RequestUtil.deleteAllEntities(mockMvc, PATH, PATH, mapper);
	}
	
	
	private void createVendors() throws Exception {
		
		RequestUtil.createEntity(mockMvc, "/companies", company, mapper);
		
		vendorByEmailJson = createVendor(vendorByEmail, vendorByEmailJson);
		vendorByFirstName1Json = createVendor(vendorByFirstName1, vendorByFirstName1Json);
		vendorByFirstName2Json = createVendor(vendorByFirstName2, vendorByFirstName2Json);
		
	}
	
	private String createVendor(VendorTO vendor, String vendorJson) throws Exception {
		
		vendorJson = mapper.writeValueAsString(vendor);
		vendor.setCompanyId(company.getId());
		
		RequestUtil.createEntity(mockMvc, PATH, vendor, mapper);
		
		return ((ObjectNode) mapper.readTree(vendorJson))
				.put("id", vendor.getId())
				.put("company-id", company.getId())
				.toString();
	}
	
}
