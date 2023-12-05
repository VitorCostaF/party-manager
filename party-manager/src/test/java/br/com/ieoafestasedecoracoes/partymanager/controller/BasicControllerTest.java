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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.node.ObjectNode;

import br.com.ieoafestasedecoracoes.partymanager.to.DomainObjectInteface;
import br.com.ieoafestasedecoracoes.partymanager.util.RequestUtil;
import jakarta.servlet.ServletException;
import lombok.extern.slf4j.Slf4j;

// TODO achar um jeito de identificar qual tipo de objeto quebrou o teste
@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
@Slf4j
class BasicControllerTest {

	private MockMvc mockMvc;
	private ObjectMapper mapper;
	
	@Autowired
	List<DomainObjectsToTest<? extends DomainObjectInteface>> domainObjectsToTest;
	
	@Autowired
	private WebApplicationContext context;
	
	@BeforeAll
	void setup() throws UnsupportedEncodingException, Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
		mapper = new ObjectMapper();
		mapper.setPropertyNamingStrategy(PropertyNamingStrategies.KEBAB_CASE);
		
		deleteAllObjects();
		createObjects();
	}
	
	@Test
	void findAllShouldReturnOk() {
		domainObjectsToTest.forEach(obj -> {			
			try {
				mockMvc.perform(get(obj.getPath())).andExpect(status().isOk());
			} catch (Exception e) {
				log.error("Problema no findAll do objeto {}", obj.getObjectName(), e);
			}
		});
	}
	
	@Test
	void shouldReturnAtLeast2Objects() {
		
		domainObjectsToTest.forEach(obj -> {			
			List<JsonNode> objsJson;
			try {
				String response = findAllObjects(obj.getPath());
				objsJson = mapper.readValue(response, new TypeReference<List<JsonNode>>() {});
				assertTrue(objsJson.size() >= 2, "Menos de dois objetos para " + obj.getObjectName());
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
	
	@Test
	void shouldReturnById() {
		
		domainObjectsToTest.forEach(obj -> {
			try {
				mockMvc
				.perform(
					get(obj.getPathId(), obj.getObjectById().getId()))
				.andExpect(content().json(obj.getObjectByIdJson()));
			} catch (Exception e) {
				log.error("Problema ao buscar por id o objeto {}", obj.getObjectName(), e);
			}
		});
	}
	
	@Test
	void shouldDelete() {
		
		domainObjectsToTest.forEach(obj -> {			
			Integer id = obj.getObjectToDelete().getId();
			try {
				mockMvc
				.perform(
					delete(obj.getPathId(), id))
				.andExpect(status().isNoContent());
				
				mockMvc
				.perform(
					get(obj.getPathId(), id))
				.andExpect(content().json("{}"));
			} catch (Exception e) {
				log.error("Erro ao deletar objeto {} com id {}", obj.getObjectName(), id, e);
			}
		});
		
	}
	
	@Test
	void shouldUpdate() {
		
		domainObjectsToTest.forEach(obj -> {
			
			try {
				obj.getObjectUpdated().setId(obj.getObjectToUpdate().getId());
				String objectUpdatedJson = mapper.writeValueAsString(obj.getObjectUpdated());		
				mockMvc
				.perform(
					put(obj.getPathId(), obj.getObjectToUpdate().getId())
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectUpdatedJson))
				.andExpect(content().json(objectUpdatedJson));
			} catch (Exception e) {
				log.error("Erro ao fazer update do objeto {} de id {}", obj.getObjectName(), obj.getObjectToUpdate().getId(), e);
			}			
		});
	}
	
//	TODO implementar o retorno correto quando for tratada a excessão
	@Test
	void shouldNotupdateAnInexistentObject() throws Exception {
		
		domainObjectsToTest.forEach(obj -> {			
			String objectUpdatedJson;
			
			try {
				objectUpdatedJson = mapper.writeValueAsString(obj.getObjectToUpdate());
				Assertions.assertThrows(ServletException.class, () -> 
				mockMvc
				.perform(
						put(obj.getPathId(), -1)
						.content(objectUpdatedJson)
						.contentType(MediaType.APPLICATION_JSON)));
				
			} catch (JsonProcessingException e) {
				log.error("Problema ao buscar por id o objeto {}", obj.getObjectName(), e);
			}
			
		});
	}
	
	@Test
	void shouldCreate() {
		
		domainObjectsToTest.forEach(obj -> {
			try {
				obj.getObjectToCreate().setId(null);
				String objectToCreateJson = mapper.writeValueAsString(obj.getObjectToCreate());
				
				mockMvc
				.perform(
					post(obj.getPath())
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectToCreateJson))
				.andExpect(content().json(objectToCreateJson, false));
				
			} catch (Exception e) {
				log.error("Erro ao criar objeto {} de id {}", obj.getObjectName(), obj.getObjectToUpdate().getId(), e);
			}			
		});
		
	}
	
	private String findAllObjects(String path) throws UnsupportedEncodingException, Exception {
		return mockMvc
			.perform(get(path))
			.andReturn()
				.getResponse()
					.getContentAsString();
		
	}
	
	private void deleteAllObjects() throws Exception {
		
		domainObjectsToTest.forEach(obj -> {
			try {
				RequestUtil.deleteAllEntities(mockMvc, obj.getPath(), obj.getPath(), mapper);
			} catch (Exception e) {
				log.error("Problema deletar objetos para {}", obj.getObjectName(), e);
			}
		});
		
	}
	
	private void createObjects() throws UnsupportedEncodingException, Exception {
		
		domainObjectsToTest.forEach(obj -> {
			
			String path = obj.getPath();
			
			try {
				obj.setObjectByIdJson(mapper.writeValueAsString(obj.getObjectById()));

				RequestUtil.createEntity(mockMvc, path, obj.getObjectById(), mapper);
				RequestUtil.createEntity(mockMvc, path, obj.getObjectToDelete(), mapper);
				RequestUtil.createEntity(mockMvc, path, obj.getObjectToUpdate(), mapper);
				
				obj.setObjectByIdJson(((ObjectNode) mapper.readTree(obj.getObjectByIdJson())).put("id", obj.getObjectById().getId()).toString());
			} catch (Exception e) {
				log.error("problema ao criar objetos {}", obj.getObjectName(), e);
			}
		});
	}
	
}
