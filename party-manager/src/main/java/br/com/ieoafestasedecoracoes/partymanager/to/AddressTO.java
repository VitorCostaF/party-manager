package br.com.ieoafestasedecoracoes.partymanager.to;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class AddressTO {

	private Integer id;
	private String street;
	private String city;
	private String zipCode;
	private String complement;
	
}
