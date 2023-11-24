package br.com.ieoafestasedecoracoes.partymanager.to;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.ieoafestasedecoracoes.partymanager.domain.Company;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(value = Include.NON_EMPTY)
public class CompanyTO extends DomainObjectTO {

	private Integer id;
	private String name;
	private String document;
	
	public CompanyTO(Company company) {
		if(company != null) {
			this.id = company.getId();
			this.name = company.getName();
			this.document = company.getDocument();			
		}
	}
	
	public static List<CompanyTO> fromCompanyList(List<Company> list) {
		return list.stream().map(CompanyTO::new).toList();
	}
	
}
