package br.com.ieoafestasedecoracoes.partymanager.to;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonInclude(value = Include.NON_NULL)
public class VendorTO implements DomainObjectInteface {

	private Integer id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	
	@NotNull(message = "Deve haver uma empresa vinculada")
	private Integer companyId;
	
}
