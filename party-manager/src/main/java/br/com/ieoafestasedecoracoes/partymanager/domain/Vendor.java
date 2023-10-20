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
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vendor {

	@Id
	@SequenceGenerator(name = "vendor_seq", allocationSize = 1)
	@GeneratedValue(generator = "vendor_seq")
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
	@JoinColumn(name = "company_id")
	private Company company;

	public Vendor(VendorTO userTO) {
		if (userTO != null) {
			this.id = userTO.getId();
			this.firstName = userTO.getFirstName();
			this.lastName = userTO.getLastName();
			this.email = userTO.getEmail();
			this.password = userTO.getPassword();
		}
	}
}
