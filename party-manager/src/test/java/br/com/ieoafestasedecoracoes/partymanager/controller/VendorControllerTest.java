package br.com.ieoafestasedecoracoes.partymanager.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.node.ObjectNode;

import br.com.ieoafestasedecoracoes.partymanager.testobjects.VendorObjects;
import br.com.ieoafestasedecoracoes.partymanager.to.VendorTO;
import br.com.ieoafestasedecoracoes.partymanager.util.RequestUtil;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
class VendorControllerTest {

	@Autowired
	private WebApplicationContext applicationContext;
	
	@Autowired
	private VendorObjects vendorObjects;
	
	private MockMvc mockMvc;	
	
	private ObjectMapper mapper;
	
	private VendorTO vendorById;
	private VendorTO vendor1;
	private VendorTO vendorToDelete;
	private VendorTO vendorToUpdate;
	
	private String vendorByIdJson;
	
	private String PATH = "/vendors";
	private String PATH_ID = PATH + "/{id}";

	private VendorTO vendor2;
	
	@BeforeAll
	void setup() throws Exception {
		
		this.mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext).build();
		this.mapper = new ObjectMapper().setPropertyNamingStrategy(PropertyNamingStrategies.KEBAB_CASE);
		
		deleteAllVendors();
		createVendors();
	}
	
	private void deleteAllVendors() throws Exception {
		RequestUtil.deleteAllEntities(mockMvc, PATH, PATH, mapper);
	}
	
	private void createVendors() throws Exception {
		
		vendorById = new VendorTO(1, "VendorId", "Byid", "vendor.by.email@email.com", "1234", 1);
		vendor1 = new VendorTO(1, "Vendor", "Last1", "vendor1@email.com", "12345", 1);
		vendor2 = new VendorTO(1, "Vendor", "Last2", "vendor2@email.com", "123", 1);
		vendorToDelete = new VendorTO(1, "VendorDelete", "ToDelete", "vendor.to.delete@email.com", "12346", 1);
		vendorToUpdate = new VendorTO(1, "VendorUpdate", "Byid", "vendor.to.update@email.com", "1234", 1);
		
		vendorByIdJson = mapper.writeValueAsString(vendorById);
				
		RequestUtil.createEntity(mockMvc, PATH, vendorById, mapper);
		RequestUtil.createEntity(mockMvc, PATH, vendor1, mapper);
		RequestUtil.createEntity(mockMvc, PATH, vendor2, mapper);
		RequestUtil.createEntity(mockMvc, PATH, vendorToDelete, mapper);
		RequestUtil.createEntity(mockMvc, PATH, vendorToUpdate, mapper);
		
		vendorByIdJson = ((ObjectNode) mapper.readTree(vendorByIdJson)).put("id", vendorById.getId()).toString();
		
	}
	
}
