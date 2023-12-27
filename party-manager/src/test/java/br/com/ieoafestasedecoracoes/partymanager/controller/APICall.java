package br.com.ieoafestasedecoracoes.partymanager.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.ieoafestasedecoracoes.partymanager.to.DomainObjectInteface;

// TODO achar um jeito de identificar qual tipo de objeto quebrou o teste
@Component
class APICall {

	public MockHttpServletResponse executeById(DomainObjectInteface obj, String path, MockMvc mockMvc) throws Exception {
		return mockMvc
			.perform(
				get(path, obj.getId()))
			.andReturn()
				.getResponse();
	}
	
	public MockHttpServletResponse executeFindAll(String path, MockMvc mockMvc) throws Exception {
		return mockMvc //
			.perform( //
				get(path))
			.andReturn()
				.getResponse();
	}
	
	public MockHttpServletResponse executeDelete(DomainObjectInteface obj, String path, MockMvc mockMvc) throws Exception {

		Integer id = obj.getId();
		mockMvc
			.perform(
				delete(path, id));

		return mockMvc
			.perform(
				get(path, id))
			.andReturn()
				.getResponse();

	}
	
	public MockHttpServletResponse executeUpdate(String objectUpdatedJson, Integer id, String path, MockMvc mockMvc) throws Exception {
		return mockMvc
			.perform(
				put(path, id)
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectUpdatedJson))
			.andReturn()
				.getResponse();
	}
	
	
	public MockHttpServletResponse executeCreate(String objectToCreateJson, String path, MockMvc mockMvc) throws Exception {
		
		return mockMvc
			.perform(
				post(path)
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectToCreateJson))
			.andReturn()
				.getResponse();

	}
	
}
