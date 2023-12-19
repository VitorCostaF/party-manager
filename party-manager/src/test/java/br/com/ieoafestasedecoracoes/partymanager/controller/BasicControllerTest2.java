package br.com.ieoafestasedecoracoes.partymanager.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import br.com.ieoafestasedecoracoes.partymanager.to.DomainObjectInteface;

// TODO achar um jeito de identificar qual tipo de objeto quebrou o teste
@Component
class BasicControllerTest2 {


	public ResultActions shouldReturnById(DomainObjectInteface obj, String path, MockMvc mockMvc) throws Exception {
		return mockMvc
			.perform(
				get(path, obj.getId()));
	}
	
}
