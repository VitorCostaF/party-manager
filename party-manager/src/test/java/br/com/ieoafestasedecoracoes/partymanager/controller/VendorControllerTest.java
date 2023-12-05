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

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.node.ObjectNode;

import br.com.ieoafestasedecoracoes.partymanager.to.VendorTO;
import br.com.ieoafestasedecoracoes.partymanager.util.RequestUtil;
import jakarta.servlet.ServletException;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
class VendorControllerTest {

	@Autowired
	private WebApplicationContext applicationContext;
	
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
	
	@Test
	void findAllShouldReturnOk() throws Exception {
		mockMvc.perform(get(PATH)).andExpect(status().isOk());
		
	}
	
	@Test
	void findAllShoudlReturnAtLeastTwoVendors() throws UnsupportedEncodingException, Exception {
		
		String responseJson = mockMvc
			.perform(
				get(PATH))
			.andReturn()
				.getResponse()
				.getContentAsString();
		
		Integer size =  mapper.readValue(responseJson, new TypeReference<List<VendorTO>>() {}).size();
		assertTrue(size >= 2);
		
	}
	
	@Test
	void shouldReturnAnVendorById() throws Exception {
		
		mockMvc
			.perform(
				get(PATH_ID, vendorById.getId()))
			.andExpect(content().json(vendorByIdJson));
		
	}
	
	@Test
	void shouldDeleteById() throws Exception {
		mockMvc
			.perform(
				delete(PATH_ID, vendorToDelete.getId()))
			.andExpect(status().isNoContent());
		
		 mockMvc
			.perform(
				get(PATH_ID, vendorToDelete.getId()))
			.andExpect(content().json("{}"));
	}
	
	@Test
	void shouldCreateAnVendor() throws Exception {
		
		VendorTO vendorToCreate = new VendorTO(1, "Street Vendor Created", "City Vendor Created", "1234",
				"Complement Vendor Created");
		
		vendorToCreate.setId(null);
		
		JsonNode addressToCreateJson = mapper.valueToTree(vendorToCreate);		
		mockMvc
			.perform(
				post(PATH)
					.content(addressToCreateJson.toString())
					.contentType(MediaType.APPLICATION_JSON))
				.andExpect(
					content()
						.json(addressToCreateJson.toString(), false));
	}
	
	@Test
	void shouldUpdateVendor() throws Exception {
		
		VendorTO vendorUpdated = new VendorTO(vendorToUpdate.getId(), "Street Updated", "City Updated", "200", "Complement Updated");
		String vendorUpdatedJson = mapper.writeValueAsString(vendorUpdated); 
		
		mockMvc
			.perform(
				put(PATH_ID, vendorToUpdate.getId())
				.content(vendorUpdatedJson)
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(content().json(vendorUpdatedJson));
	}
	
//	TODO implementar o retorno correto quando for tratada a excessão
	@Test()
	void shouldNotupdateAnInexistentVendor() throws Exception {
		
		String vendorUpdatedJson = mapper.writeValueAsString(vendorToUpdate);
		
		Assertions.assertThrows(ServletException.class, () -> 
			mockMvc
				.perform(
					put(PATH_ID, -1)
						.content(vendorUpdatedJson)
						.contentType(MediaType.APPLICATION_JSON)));
	}
	
	private void deleteAllVendors() throws Exception {
		RequestUtil.deleteAllEntities(mockMvc, PATH, PATH, mapper);
	}
	
	private void createVendors() throws Exception {
		
		vendorById = new VendorTO(1, "VendorId", "Byid", "vendor.by.email@email.com", "1234");
		vendor1 = new VendorTO(1, "Vendor", "Last1", "vendor1@email.com", "12345");
		vendor2 = new VendorTO(1, "Vendor", "Last2", "vendor2@email.com", "123");
		vendorToDelete = new VendorTO(1, "VendorDelete", "ToDelete", "vendor.to.delete@email.com", "12346");
		vendorToUpdate = new VendorTO(1, "VendorUpdate", "Byid", "vendor.to.update@email.com", "1234");
		
		vendorByIdJson = mapper.writeValueAsString(vendorById);
				
		RequestUtil.createEntity(mockMvc, PATH, vendorById, mapper);
		RequestUtil.createEntity(mockMvc, PATH, vendor1, mapper);
		RequestUtil.createEntity(mockMvc, PATH, vendor2, mapper);
		RequestUtil.createEntity(mockMvc, PATH, vendorToDelete, mapper);
		RequestUtil.createEntity(mockMvc, PATH, vendorToUpdate, mapper);
		
		vendorByIdJson = ((ObjectNode) mapper.readTree(vendorByIdJson)).put("id", vendorById.getId()).toString();
		
	}
	
}
