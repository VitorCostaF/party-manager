package br.com.ieoafestasedecoracoes.partymanager.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GeneralConfiguration {

	@Bean
	public ModelMapper mapper() {
		return new ModelMapper();
	}
	
}
