package br.com.ieoafestasedecoracoes.partymanager.to;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.ieoafestasedecoracoes.partymanager.domain.ClientType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(value = Include.NON_NULL)
public class ClientTO implements DomainObjectInteface {

	private Integer id;
	private String document;
	private String firstName;
	private String lastName;
	private ClientType type;
	private String email;
	private String password;

}
