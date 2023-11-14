package br.com.ieoafestasedecoracoes.partymanager.to;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class AddressTO {

	private String street;
	private String city;
	private String zipCode;
	private String complement;
	
}
