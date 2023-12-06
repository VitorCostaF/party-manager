package br.com.ieoafestasedecoracoes.partymanager.domain;

import br.com.ieoafestasedecoracoes.partymanager.to.VendorTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Vendor {

	@Id
	@GeneratedValue(generator = "vendor_seq")
	@SequenceGenerator(name = "vendor_seq", allocationSize = 1)
	private Integer id;

	@Column(name = "first_name", nullable = false)
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(nullable = false, unique = true)
	private String email;
	
	@Column(nullable = false)
	private String password;
	
	@ManyToOne
	@JoinColumn(name = "company_id", nullable = false)
	private Company company;

}
