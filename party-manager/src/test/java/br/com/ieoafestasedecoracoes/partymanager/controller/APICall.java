package br.com.ieoafestasedecoracoes.partymanager.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;

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
	
	public MockHttpServletResponse executeDelete(Integer id, String path, MockMvc mockMvc) throws Exception {

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
	
	public MockHttpServletResponse executeGet(Object param, String path, MockMvc mockMvc) throws Exception {
		return mockMvc
			.perform(
				get(path, param))
			.andReturn()
				.getResponse();
	}
	
	
	
}
