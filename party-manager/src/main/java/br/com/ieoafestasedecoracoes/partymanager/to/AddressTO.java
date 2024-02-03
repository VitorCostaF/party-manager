package br.com.ieoafestasedecoracoes.partymanager.to;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(value = Include.NON_NULL)
public class AddressTO implements DomainObjectInteface {

	private Integer id;
	
	@NotBlank(message = "rua deve ser preenchida")
	private String street;
	
	@NotBlank(message = "cidade deve ser preenchida")
	private String city;
	
	@NotBlank(message = "CEP deve ser preenchido")
	@Pattern(regexp = "(\\d{8}|\\d{5}-\\d{3})", message = "cep deve estar no formato 99999999 ou 99999-999")
	private String zipCode;
	
	private String complement;
	
	private ProvinceTO province;
	
}
