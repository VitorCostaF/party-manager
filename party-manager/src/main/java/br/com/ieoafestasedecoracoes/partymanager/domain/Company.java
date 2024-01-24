package br.com.ieoafestasedecoracoes.partymanager.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(of = "id", callSuper = false)
public class Company implements DomainObjetctInterface {

	@Id
	@GeneratedValue(generator = "company_seq")
	@SequenceGenerator(name = "company_seq", allocationSize = 1)
	private Integer id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false, unique = true)
	private String document;

	@OneToMany(mappedBy = "company", cascade = CascadeType.REMOVE)
	private List<Vendor> vendors = new ArrayList<>();
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
		name = "company_client",
		joinColumns = 
			@JoinColumn(name = "company_id", referencedColumnName = "id"),
		inverseJoinColumns = 
			@JoinColumn(name = "client_id", referencedColumnName = "id")
	)
	private List<Client> clients = new ArrayList<>();
	
	@OneToMany(mappedBy = "company", cascade = CascadeType.REMOVE)
	private List<Material> materials;
	
	@OneToMany(mappedBy = "company", cascade = CascadeType.REMOVE)
	private List<Decoration> decorations;

}
