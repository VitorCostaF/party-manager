package br.com.ieoafestasedecoracoes.partymanager.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class Party {

	@Id
	@GeneratedValue(generator = "party_seq")
	@SequenceGenerator(name = "party_seq", allocationSize = 1)
	private Integer id;
	
	private LocalDate date;
	
	@ManyToOne
	private Client client;  
	
	@ManyToOne
	private Address address;
	
	@OneToMany
	private List<PartyMaterial> partyMaterials = new ArrayList<>();
	
}