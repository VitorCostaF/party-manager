package br.com.ieoafestasedecoracoes.partymanager.to;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.ieoafestasedecoracoes.partymanager.domain.Company;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(value = Include.NON_EMPTY)
public class CompanyTO {

	private Integer id;
	private String name;
	private String document;
	
	List<ClientTO> clients = new ArrayList<>();
	
	List<VendorTO> vendors = new ArrayList<>();
	
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
