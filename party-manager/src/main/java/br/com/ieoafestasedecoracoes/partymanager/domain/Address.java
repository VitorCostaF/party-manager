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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class Address {

	@Id
	@GeneratedValue(generator = "address_seq")
	@SequenceGenerator(name = "address_seq", allocationSize = 1)
	private Integer id;
	
	@Column(nullable = false)
	private String street;
	
	@Column(nullable = false)
	private String city;
	
	@Column(name = "zip_code", nullable = false)
	private String zipCode;
	
	private String complement;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Province province;
	
	@OneToMany(mappedBy = "address", cascade = CascadeType.REMOVE)
	private List<Party> parties = new ArrayList<>();
	
	@OneToOne(mappedBy = "address", fetch = FetchType.LAZY)
	private Company company;
}
