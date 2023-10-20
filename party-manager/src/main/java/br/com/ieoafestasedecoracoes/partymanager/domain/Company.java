package br.com.ieoafestasedecoracoes.partymanager.domain;

import java.util.ArrayList;
import java.util.List;

import br.com.ieoafestasedecoracoes.partymanager.to.CompanyTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Company {

	@Id
	@GeneratedValue(generator = "company_seq")
	@SequenceGenerator(name = "company_seq", allocationSize = 1)
	private Integer id;

	@Column(nullable = false)
	private String name;

	@Column(unique = true)
	private String document;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "company")
	private List<Vendor> vendors = new ArrayList<>();

	public Company(CompanyTO companyTO) {
		if (companyTO != null) {
			this.id = companyTO.getId();
			this.name = companyTO.getName();
			this.document = companyTO.getDocument();
		}
	}

}
