package br.com.ieoafestasedecoracoes.partymanager.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
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
	
	@Column(name = "client_type", nullable = false)
	@Enumerated(EnumType.STRING)
	private ClientType type;
	
	@Column(unique = true, nullable = false)
	private String email;
	
	@Column(nullable = false)
	private String password;
	
	@ManyToMany(mappedBy = "clients")
	private List<Company> companies = new ArrayList<>(); 
	
	@OneToMany(mappedBy = "client")
	private List<Party> parties;

}
