package br.com.ieoafestasedecoracoes.partymanager.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
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

import br.com.ieoafestasedecoracoes.partymanager.domain.Company;
import br.com.ieoafestasedecoracoes.partymanager.domain.Vendor;
import br.com.ieoafestasedecoracoes.partymanager.repository.CompanyRepository;
import br.com.ieoafestasedecoracoes.partymanager.repository.VendorRepository;
import br.com.ieoafestasedecoracoes.partymanager.to.CompanyTO;
import br.com.ieoafestasedecoracoes.partymanager.to.VendorTO;
import jakarta.servlet.ServletException;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(Lifecycle.PER_CLASS)
class VendorControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private APICall basicControllerTest;

	@Autowired
	private ModelMapper modelMapper;
	
	private ObjectMapper mapper;
	
	@Autowired
	private VendorRepository repository;
	
	@Autowired
	private CompanyRepository companyRepository;
	
	private CompanyTO company = new CompanyTO(null, "Company To Vendors", "12348");
	
	private VendorTO vendorByEmail = new VendorTO(1, "VendorByEmail", "ByEmail", "vendor.by.email@email.com", "1235", 1);;	
	private VendorTO vendorByFirstName1 = new VendorTO(1, "VendorByFirstName", "ByFirstName", "vendor.by.firstname@email.com", "1235", 1);
	private VendorTO vendorByFirstName2 = new VendorTO(1, "VendorByFirstName", "ByFirstName2", "vendor.by.firstname2@email.com", "1235", 1);	
	private VendorTO vendorById = new VendorTO(1, "VendorId", "Byid", "vendor.by.id@email.com", "1234", 1);
	private VendorTO vendorToDelete = new VendorTO(1, "VendorDelete", "ToDelete", "vendor.to.delete@email.com", "12346", 1);
	private VendorTO vendorToUpdate = new VendorTO(1, "VendorUpdate", "Byid", "vendor.to.update@email.com", "1234", 1);
	
	private String vendorByIdJson;
	private String vendorByEmailJson;
	
	private String PATH = "/vendors";
	private String PATH_ID = PATH + "/{id}";

	@BeforeAll
	void setup() throws Exception {
		
		this.mapper = new ObjectMapper().setPropertyNamingStrategy(PropertyNamingStrategies.KEBAB_CASE);
		this.mapper.setPropertyNamingStrategy(PropertyNamingStrategies.KEBAB_CASE);
		
		deleteAllVendors();
		createVendors();
	}
	
	@Test
	void shouldReturnById() throws Exception {
		MockHttpServletResponse response = basicControllerTest.executeById(vendorById, PATH_ID, mockMvc);		
		assertThat(response.getContentAsString()).isEqualTo(vendorByIdJson);		
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
		MockHttpServletResponse response = basicControllerTest.executeDelete(vendorToDelete.getId(), PATH_ID, mockMvc);
		assertThat(response.getContentAsString()).isEqualTo("{}");
	}
	
	@Test
	void shouldUpdate() throws Exception {
		VendorTO vendorUpdated = new VendorTO(1, "VendorUpdated", "Updated", "vendor.updated@email.com", "1234", company.getId());
		vendorUpdated.setId(vendorToUpdate.getId());
		String objectUpdatedJson = mapper.writeValueAsString(vendorUpdated);
		
		MockHttpServletResponse response = basicControllerTest.executeUpdate(objectUpdatedJson, vendorUpdated.getId(), PATH_ID, mockMvc);
		assertThat(response.getContentAsString()).isEqualTo(objectUpdatedJson);
	}
	
	// TODO voltar o id de uma forma diferente
	@Test
	void shouldCreate() throws Exception {
		VendorTO vendorToCreate = new VendorTO(1, "VendorCreated", "Created", "vendor.created@email.com", "13245", company.getId());
		vendorToCreate.setId(null);
		String vendorToCreateJson = mapper.writeValueAsString(vendorToCreate);
				
		MockHttpServletResponse response = basicControllerTest.executeCreate(vendorToCreateJson, PATH, mockMvc);
		ObjectNode jsonResponse = ((ObjectNode)mapper.readTree(response.getContentAsString()));
		jsonResponse.remove("id");
		
		assertThat(jsonResponse.toString()).hasToString(vendorToCreateJson);
	}
	
	@Test
	void shouldNotupdateAnInexistentId() throws Exception {
		String objectUpdatedJson = mapper.writeValueAsString(vendorToUpdate);
		
		assertThatThrownBy(() -> basicControllerTest.executeUpdate(objectUpdatedJson, -1, PATH_ID, mockMvc))
			.isInstanceOf(ServletException.class);
	}
	
	@Test
	void shouldReturnByEmail() throws Exception {
		MockHttpServletResponse response = basicControllerTest.executeGet(vendorByEmail.getEmail(), PATH + "/email/{email}", mockMvc);
		assertThat(response.getContentAsString()).hasToString(vendorByEmailJson);
	}
	
//	TODO implementar o retorno correto quando for tratada a excessão
	@Test
	void shouldNotCreateTheSameEmail() throws Exception {
		Assertions.assertThrows(ServletException.class, () ->
			basicControllerTest.executeCreate(vendorByEmailJson, PATH, mockMvc)
		);
	}
	
	
	@Test
	void shouldReturnByFirstName() throws Exception {
		String vendors = mapper.writeValueAsString(Arrays.asList(vendorByFirstName1, vendorByFirstName2));
		MockHttpServletResponse response = basicControllerTest.executeGet(vendorByFirstName1.getFirstName(), PATH + "/firstname/{firstname}", mockMvc);
		assertThat(response.getContentAsString()).hasToString(vendors);
	}
	
	private void deleteAllVendors() throws Exception {
		List<Vendor> addresses = repository.findAll();
		addresses.forEach(repository::delete);		
	}
	
	
	private void createVendors() throws Exception {
		
		Company companyBD = companyRepository.save(modelMapper.map(company, Company.class));
		company.setId(companyBD.getId());
		
		createVendor(vendorById, company.getId());
		createVendor(vendorByEmail, company.getId());
		createVendor(vendorByFirstName1, company.getId());
		createVendor(vendorByFirstName2, company.getId());
		createVendor(vendorToDelete, company.getId());
		createVendor(vendorToUpdate, company.getId());
		
		vendorByIdJson = mapper.writeValueAsString(vendorById);
		vendorByEmailJson = mapper.writeValueAsString(vendorByEmail);
		
	}
	
	private void createVendor(VendorTO vendorTO, Integer companyId) throws Exception {
		vendorTO.setCompanyId(companyId);
		Vendor vendor =  repository.save(modelMapper.map(vendorTO, Vendor.class));
		vendorTO.setId(vendor.getId());
	}
	
}
