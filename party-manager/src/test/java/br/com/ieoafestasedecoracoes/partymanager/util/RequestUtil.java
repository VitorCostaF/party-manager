package br.com.ieoafestasedecoracoes.partymanager.util;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.ieoafestasedecoracoes.partymanager.to.DomainObjectInteface;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RequestUtil {
	
	public static <T extends DomainObjectInteface> void createEntity(MockMvc mockMvc, String path, T domainObject,
			ObjectMapper mapper) throws UnsupportedEncodingException, Exception {
		String domainJsonStr = mapper.writeValueAsString(domainObject);
		
		String response =  mockMvc
		.perform(
			post(path)
				.content(domainJsonStr)
				.contentType(MediaType.APPLICATION_JSON))
			.andReturn()
				.getResponse()
				.getContentAsString();
		
		Integer id = mapper.readValue(response, JsonNode.class).get("id").asInt();
		domainObject.setId(id);
	}
	
	public static void deleteAllEntities(MockMvc mockMvc, String allEntitiesPath, String deletePath, ObjectMapper mapper) throws UnsupportedEncodingException, Exception {
		
		String entitiesToDelete =  mockMvc
				.perform(
					get(allEntitiesPath))
				.andReturn()
					.getResponse().getContentAsString();
			
			List<JsonNode> jsonsToDelete = mapper.readValue(entitiesToDelete, new TypeReference<List<JsonNode>>() {});
			List<Integer> idsToDelete =  jsonsToDelete.stream().map(node -> node.findPath("id").asInt()).toList();
			
			
			idsToDelete.forEach(id -> {
				try {
					mockMvc.perform(delete(deletePath + "/{id}",id));
				} catch (Exception e) {
					log.info("problema ao deleter address {}", id, e);
				}
			});
	}

}
