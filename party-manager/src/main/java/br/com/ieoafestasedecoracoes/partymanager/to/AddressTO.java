package br.com.ieoafestasedecoracoes.partymanager.to;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
@JsonInclude(value = Include.NON_NULL)
public class AddressTO {

	private Integer id;
	private String street;
	private String city;
	private String zipCode;
	private String complement;
	
}
