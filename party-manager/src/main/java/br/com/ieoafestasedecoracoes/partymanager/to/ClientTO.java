package br.com.ieoafestasedecoracoes.partymanager.to;

import java.util.List;

import br.com.ieoafestasedecoracoes.partymanager.domain.Client;
import br.com.ieoafestasedecoracoes.partymanager.domain.ClientType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClientTO {

	private Integer id;
	private String document;
	private String firstName;
	private String lastName;
	private ClientType type;
	private String email;
	private String password;
	
	public ClientTO(Client client) {
		if(client != null) {			
			this.id = client.getId();
			this.document = client.getDocument();
			this.firstName = client.getFirstName();
			this.lastName = client.getLastName();	
			this.type = client.getType();
			this.email = client.getEmail();	
			this.password = client.getPassword();
		}
	}

	public static List<ClientTO> fromClientList(List<Client> list) {
		return list.stream().map(ClientTO::new).toList();
	}
}
