package br.com.ieoafestasedecoracoes.partymanager.domain;

import br.com.ieoafestasedecoracoes.partymanager.to.ClientTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Client {

	@Id
	@GeneratedValue(generator = "client_seq")
	@SequenceGenerator(name = "client_seq", allocationSize = 1)
	private Integer id;

	@Column(unique = true, nullable = false)
	private String document;
	
	@Column(nullable = false)
	private String firstName;
	
	private String lastName;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private ClientType type;
	
	@Column(unique = true, nullable = false)
	private String email;
	
	@Column(nullable = false)
	private String password;
	
	public Client(ClientTO clientTO) {
		if (clientTO != null) {
			this.id = clientTO.getId();
			this.document = clientTO.getDocument();
			this.firstName = clientTO.getFirstName();
			this.lastName = clientTO.getLastName();
			this.type = clientTO.getType();
			this.email = clientTO.getEmail();
			this.password = clientTO.getPassword();
		}
	}

}
